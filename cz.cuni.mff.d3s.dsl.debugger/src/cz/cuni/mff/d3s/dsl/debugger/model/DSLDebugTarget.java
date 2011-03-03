/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.model;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.acceleo.traceability.TraceabilityModel;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.debug.core.IJavaDebugTarget;
import org.eclipse.jdt.debug.core.IJavaThread;

import cz.cuni.mff.d3s.dsl.debugger.IDSLDebuggerConstants;
import cz.cuni.mff.d3s.dsl.debugger.breakpoints.DSLLineBreakpoint;
import cz.cuni.mff.d3s.dsl.debugger.traceability.SimpleMapping;
import cz.cuni.mff.d3s.dsl.debugger.traceability.TraceabilityModelHelper;

/**
 * @author Michal Malohlava
 *
 */
public class DSLDebugTarget implements IDebugTarget, IDebugEventSetListener {
	
	private static final IThread[] NO_THREADS = new IThread[] {};
	
	private ILaunch launch;
	
	// underlying JDIDebugTarget
	private IJavaDebugTarget jDebugTarget;
	
	// associated JVM process
	private IProcess jProcess;

	// associated Thread of JDI debugger
	private DSLDebugThread[] dslDebugThreads;
	
	private TraceabilityModel traceabilityModel;

	private EObject dslProgramModel;
	
	private Collection<DSLLineBreakpoint> dslLineBps = new HashSet<DSLLineBreakpoint>(10);
		
	public DSLDebugTarget(ILaunch launch, TraceabilityModel traceabilityModel, EObject dslProgramModel) {
		this.launch = launch;
		
		setTraceabilityModel(traceabilityModel);
		setDslProgramModel(dslProgramModel);
		
		initialize();
		
		DebugPlugin.getDefault().addDebugEventListener(this);
	}
				
	private void initialize() {
		initBreakpoints();		
		initThreads();
	}

	private void initBreakpoints() {
		IBreakpointManager manager= DebugPlugin.getDefault().getBreakpointManager();
		manager.addBreakpointListener(this);
		
		IBreakpoint[] bps = manager.getBreakpoints(IDSLDebuggerConstants.DSL_DEBUG_MODEL_ID);
		for(IBreakpoint bp : bps) {
			if (bp instanceof DSLLineBreakpoint) {
				breakpointAdded(bp);
			}
		}
	}
	
	private void initThreads() {
		dslDebugThreads = new DSLDebugThread[1];
		dslDebugThreads[0] = new DSLDebugThread(this, null);
	}

	@Override
	public String getName() throws DebugException {		
		return "DSL debug target";
	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		if (breakpoint instanceof DSLLineBreakpoint) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void handleDebugEvents(DebugEvent[] events) {
		for(DebugEvent e : events) {
			handleDebugEvent(e);
		}
	}
	
	protected void handleDebugEvent(DebugEvent event) {
		Object eventSource = event.getSource();
		if (DebugEvent.CREATE == event.getKind()) {
			if (eventSource instanceof IProcess) {
				this.jProcess = (IProcess) eventSource;
			} else if (eventSource instanceof IJavaThread) {
				IJavaThread javaThread = (IJavaThread) eventSource;
				try {
					if (!javaThread.isSystemThread()) {					
						this.dslDebugThreads[0].setTargetJavaThread((IJavaThread) eventSource);						
					}
				} catch (DebugException e) {
					e.printStackTrace();
				}
			} else if (eventSource instanceof IJavaDebugTarget) {
				this.jDebugTarget = (IJavaDebugTarget) eventSource;
			}			
		} else if (DebugEvent.BREAKPOINT == event.getKind()) {
			if (eventSource instanceof IJavaThread) {
				IJavaThread javaThread = (IJavaThread) eventSource;
				IBreakpoint[] jbps = javaThread.getBreakpoints();
				
				for (DSLLineBreakpoint bp : dslLineBps) {
					for (IBreakpoint jbp : jbps) {
						if (bp.supportJavaBreakpoint(jbp)) {
							bp.hit();
						}
					}
				}
			}
		}
	}
	
	@Override
	public String getModelIdentifier() {		
		return IDSLDebuggerConstants.DSL_DEBUG_MODEL_ID;
	}


	@Override
	public IDebugTarget getDebugTarget() {		
		return this;
	}


	@Override
	public ILaunch getLaunch() {
		return this.launch;
	}


	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean canTerminate() {		
		return jDebugTarget != null ? jDebugTarget.canTerminate() : false;
	}


	@Override
	public boolean isTerminated() {		
		return jDebugTarget != null ? jDebugTarget.isTerminated() : true;
	}


	@Override
	public void terminate() throws DebugException {
		if (jDebugTarget != null)
			jDebugTarget.terminate();
	}


	@Override
	public boolean canResume() {		
		return jDebugTarget != null ? jDebugTarget.canResume() : false;
	}


	@Override
	public boolean canSuspend() {
		return jDebugTarget != null ? jDebugTarget.canSuspend() : false;
	}


	@Override
	public boolean isSuspended() {
		return jDebugTarget != null ? jDebugTarget.isSuspended() : false;
	}


	@Override
	public void resume() throws DebugException {
		if (jDebugTarget!=null) 
			jDebugTarget.resume();		
	}


	@Override
	public void suspend() throws DebugException {
		if (jDebugTarget != null) 
			jDebugTarget.suspend();		
	}


	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {
		// check if the breakpoint is supported
		
		if (supportsBreakpoint(breakpoint)) {
			if (getBreakpointManager().isEnabled()) {
				DSLLineBreakpoint dslLineBreakpoint = (DSLLineBreakpoint) breakpoint;
				dslLineBreakpoint.install(this);
				
				this.dslLineBps.add(dslLineBreakpoint);
			}			
		}
	}


	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean canDisconnect() {
		return jDebugTarget != null ? jDebugTarget.canDisconnect() : false;
	}


	@Override
	public void disconnect() throws DebugException {
		if (jDebugTarget != null) 
			jDebugTarget.disconnect();		
	}


	@Override
	public boolean isDisconnected() {
		return jDebugTarget != null ? jDebugTarget.isDisconnected() : true;
	}


	@Override
	public boolean supportsStorageRetrieval() {
		return false;
	}


	@Override
	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		return null;
	}

	@Override
	public IProcess getProcess() {		
		return this.jProcess;
	}
	
	public IJavaDebugTarget getJavaDebugTarget() {
		return this.jDebugTarget;
	}

	@Override
	public IThread[] getThreads() throws DebugException {		
		return hasThreads() ? this.dslDebugThreads : NO_THREADS;
	}

	@Override
	public boolean hasThreads() throws DebugException {
		return this.dslDebugThreads[0].getTargetJavaThread() != null;
	}
	
	public void fireEvent(DebugEvent event) {
		DebugPlugin.getDefault().fireDebugEventSet(new DebugEvent[] {event});
	}
	
	public IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();		
	}

	public void setTraceabilityModel(TraceabilityModel traceabilityModel) {
		this.traceabilityModel = traceabilityModel;
	}

	public TraceabilityModel getTraceabilityModel() {
		return traceabilityModel;
	}

	public void setDslProgramModel(EObject dslProgramModel) {
		this.dslProgramModel = dslProgramModel;
	}

	public EObject getDslProgramModel() {
		return dslProgramModel;
	}
	
	public SimpleMapping getMapping(DSLLineBreakpoint bp) {		
		try {
			return TraceabilityModelHelper.getMappingToJava(bp.getMarker().getResource(), bp.getLineNumber(), this.traceabilityModel, this.dslProgramModel);
		} catch (CoreException e) {			
			e.printStackTrace();
			return null;
		}		
	}
	
	protected DSLDebugThread getThread() {
		return this.dslDebugThreads[0];
	}
}
