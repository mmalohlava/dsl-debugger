/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.breakpoints;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.LineBreakpoint;
import org.eclipse.jdt.debug.core.IJavaDebugTarget;
import org.eclipse.jdt.debug.core.IJavaLineBreakpoint;
import org.eclipse.jdt.debug.core.JDIDebugModel;

import cz.cuni.mff.d3s.dsl.debugger.IDSLDebuggerConstants;
import cz.cuni.mff.d3s.dsl.debugger.model.DSLDebugModel;
import cz.cuni.mff.d3s.dsl.debugger.model.DSLDebugTarget;
import cz.cuni.mff.d3s.dsl.debugger.model.DSLDebugThread;
import cz.cuni.mff.d3s.dsl.debugger.traceability.SimpleMapping;

/**
 * @author Michal Malohlava
 *
 */
public class DSLLineBreakpoint extends LineBreakpoint {
	
	/**
	 * Associated java line breakpoint.
	 */
	private IJavaLineBreakpoint javaLineBreakpoint;	
	
	private DSLDebugTarget debugTarget;
	
	/**
	 * 
	 */
	public DSLLineBreakpoint(final IResource file, final int lineNumber) throws CoreException {
		
		IWorkspaceRunnable job = new IWorkspaceRunnable() {
			
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				IMarker marker = file.createMarker(IDSLDebuggerConstants.DSL_LINE_BREAKPOINT_MARKER_ID);
				setMarker(marker);
				marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				marker.setAttribute(IMarker.LINE_NUMBER, lineNumber+1);
				marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
				marker.setAttribute(IMarker.MESSAGE, "Line breakpoint: " + file.getName() + " [line: " + lineNumber+1 + "]");
			}
		};
		
		run(getMarkerRule(file), job);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IBreakpoint#getModelIdentifier()
	 */
	@Override
	public String getModelIdentifier() {
		return IDSLDebuggerConstants.DSL_DEBUG_MODEL_ID;
	}	
	
	public void install(DSLDebugTarget debugTarget) {
		this.debugTarget = debugTarget;
		
		SimpleMapping sMap = debugTarget.getMapping(this);
		if (sMap!=null) {
			try {
				// FIXME this is nasty and does not follow concept of Java types
				String typeName = sMap.getTarget().getFullPath().removeFileExtension().removeFirstSegments(2).toOSString().replace('/', '.');
				int lineNumber = -1;
				
				installJavaBreakpoint(sMap.getTarget(), typeName, lineNumber, sMap.getTargetStartChar(), sMap.getTargetEndChar());								
				
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
//		createRequest(debugTarget);
	}
	
	private void installJavaBreakpoint(IResource resource, String typeName, int lineNumber, int charStart, int charEnd) throws CoreException {
		javaLineBreakpoint = DSLDebugModel.lineBreakpointExists(resource, typeName, lineNumber, charStart, charEnd);
		
		if (javaLineBreakpoint == null) {
			javaLineBreakpoint = JDIDebugModel.createLineBreakpoint(resource, typeName, lineNumber, charStart, charEnd, 0, true, null);
		}
		
	}

	protected void createRequest(DSLDebugTarget target) {
		IJavaDebugTarget jDebugTarget = debugTarget.getJavaDebugTarget();
		jDebugTarget.breakpointAdded(this.javaLineBreakpoint);		
	}
	
	public void remove(DSLDebugTarget target) {
		IJavaDebugTarget jDebugTarget = debugTarget.getJavaDebugTarget();		
		jDebugTarget.breakpointRemoved(this.javaLineBreakpoint, null);
		
		this.debugTarget = null;
	}

	public boolean supportJavaBreakpoint(IBreakpoint jbp) {
		if (jbp.equals(this.javaLineBreakpoint)) 
			return true;
		else
			return false;
	}
	
	public void hit() {
		if (debugTarget != null) {
			try {
				IThread[] threads = debugTarget.getThreads();
				if (threads.length == 1) {
					((DSLDebugThread) threads[0]).suspendedBy(this);
				}
			} catch (DebugException e) {				
				e.printStackTrace();
			}			
		}
	}
}
