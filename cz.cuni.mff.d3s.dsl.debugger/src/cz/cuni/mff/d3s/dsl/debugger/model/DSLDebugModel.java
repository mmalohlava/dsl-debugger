/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.model;

import org.eclipse.acceleo.traceability.TraceabilityModel;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.debug.core.IJavaLineBreakpoint;
import org.eclipse.jdt.debug.core.JDIDebugModel;
import org.eclipse.jdt.internal.debug.core.breakpoints.JavaLineBreakpoint;

/**
 * Helper class.
 * 
 * @author Michal Malohlava
 *
 */
public class DSLDebugModel {
	
	public static DSLDebugTarget createDSLDebugTarget(ILaunch launch, TraceabilityModel tModel, EObject dslProgramModel) {
		return new DSLDebugTarget(launch, tModel, dslProgramModel);
	}
	
	public static IJavaLineBreakpoint lineBreakpointExists(IResource resource, String typeName, int lineNumber, int charStart, int charEnd) throws CoreException {
		String modelId= JDIDebugModel.getPluginIdentifier();
		String markerType= JavaLineBreakpoint.getMarkerType();
		IBreakpointManager manager= DebugPlugin.getDefault().getBreakpointManager();
		IBreakpoint[] breakpoints= manager.getBreakpoints(modelId);
		for (int i = 0; i < breakpoints.length; i++) {
			if (!(breakpoints[i] instanceof IJavaLineBreakpoint)) {
				continue;
			}
			IJavaLineBreakpoint breakpoint = (IJavaLineBreakpoint) breakpoints[i];
			IMarker marker = breakpoint.getMarker();
			if (marker != null && marker.exists() && marker.getType().equals(markerType)) {
				String breakpointTypeName = breakpoint.getTypeName();
				if ((breakpointTypeName.equals(typeName) || breakpointTypeName.startsWith(typeName + '$')) &&
					breakpoint.getLineNumber() == lineNumber &&
					resource.equals(marker.getResource()) &&
					marker.getAttribute(IMarker.CHAR_START, -1) == charStart && 
					marker.getAttribute(IMarker.CHAR_END, -1) == charEnd ) {
						return breakpoint;
				}
			}
		}
		return null;
	}

}
