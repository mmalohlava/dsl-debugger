/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.launch;

import org.eclipse.acceleo.traceability.TraceabilityModel;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.launching.JavaLaunchDelegate;

import cz.cuni.mff.d3s.dsl.debugger.model.DSLDebugTarget;

/**
 * @author Michal Malohlava
 *
 */
public class DSLDebuggerLaunchConfigurationDelegate extends JavaLaunchDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration, java.lang.String, org.eclipse.debug.core.ILaunch, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		// load models
		ResourceSet rs = new ResourceSetImpl();
		URI dslProgramUri = URI.createPlatformResourceURI(configuration.getAttribute(IDSLDebuggerLaunchConfigurationConstants.ATTR_DSL_PROGRAM_FILENAME, ""), true);
		Resource dslProgramResource = rs.getResource(dslProgramUri, true);		
		EObject dslProgramModel = dslProgramResource.getContents().get(0);
		
		URI traceModelUri = URI.createPlatformResourceURI(configuration.getAttribute(IDSLDebuggerLaunchConfigurationConstants.ATTR_TRACEABILITY_MODEL_FILENAME, ""), true);
		Resource traceModelResource = rs.getResource(traceModelUri, true);
		TraceabilityModel traceModel = (TraceabilityModel) traceModelResource.getContents().get(0); 
						
		DSLDebugTarget dslDebugTarget = new DSLDebugTarget(launch, traceModel, dslProgramModel);		
		launch.addDebugTarget(dslDebugTarget);
		
		// debug listener		
		DebugPlugin.getDefault().addDebugEventListener(new IDebugEventSetListener() {
			
			@Override
			public void handleDebugEvents(DebugEvent[] events) {
				for(DebugEvent e : events) {
					System.err.println(e.getKind() + " from " + e.getSource() + " [" + e.getSource().getClass() + "] DATA: " + e.getData());
				}
				
			}
		});
		
		// delegate to original Java launch 
		super.launch(configuration, mode, launch, monitor);
	}

}
