/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.ui.xtext.adapters;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtext.ui.editor.XtextEditor;

import cz.cuni.mff.d3s.dsl.debugger.ui.adapters.DSLBreakpointAdapter;

/**
 * @author Michal Malohlava
 *
 */
public class XtextEditorAdapterFactory implements IAdapterFactory {
	
	private static final Class[] SUPPORTED_ADAPTERS = new Class[] { IToggleBreakpointsTarget.class };

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (IToggleBreakpointsTarget.class.equals(adapterType)) {
			if (adaptableObject instanceof XtextEditor) {
				ITextEditor textEditor = (ITextEditor) adaptableObject;
				IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
				if (resource != null) {
					return new DSLBreakpointAdapter();
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	@Override
	public Class[] getAdapterList() {
		return SUPPORTED_ADAPTERS;		
	}

}
