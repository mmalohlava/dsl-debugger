/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.ui.launch.tabs;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import cz.cuni.mff.d3s.dsl.debugger.launch.IDSLDebuggerLaunchConfigurationConstants;
import cz.cuni.mff.d3s.dsl.debugger.ui.DSLDebuggerUIPlugin;
import cz.cuni.mff.d3s.dsl.debugger.ui.internal.BrowseDialogsHelper;
import cz.cuni.mff.d3s.dsl.debugger.ui.internal.SWTFactory;

/**
 * @author Michal Malohlava
 *
 */
public class DSLDebuggerMainTab extends AbstractLaunchConfigurationTab {
	
	private Text fDSLProgramFilenameText;
	private Text fTraceabilityModelFilenameText;

	@Override
	public String getId() {		
		return "cz.cuni.mff.d3s.desl.debugger.ui.tabs.DSLDebuggerMainTab";
	}

	@Override
	public void createControl(Composite parent) {		
		Composite comp = SWTFactory.createComposite(parent, parent.getFont(), 1, 1, GridData.FILL_BOTH);  	
		((GridLayout) comp.getLayout()).verticalSpacing = 0;
		createDSLEditor(comp);
		createTraceabilityModelEditor(comp);
		
		setControl(comp);
	}	

	protected void createDSLEditor(Composite parent) {
		Group group = SWTFactory.createGroup(parent, "DSL program", 2, 1, GridData.FILL_HORIZONTAL);
		fDSLProgramFilenameText = SWTFactory.createSingleText(group, 1);		
//		ControlAccessibleListener.addListener(fProjText, group.getText());
		Button fDSLButton = SWTFactory.createPushButton(group, "Select...", null); 
		fDSLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				handleDSLProgramFileSelection();
			}
		});		
	}
	
	protected void createTraceabilityModelEditor(Composite parent) {
		Group group = SWTFactory.createGroup(parent, "Traceability model", 2, 1, GridData.FILL_HORIZONTAL);
		fTraceabilityModelFilenameText= SWTFactory.createSingleText(group, 1);		
//		ControlAccessibleListener.addListener(fProjText, group.getText());
		Button fDSLButton = SWTFactory.createPushButton(group, "Select...", null); 
		fDSLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				handleTraceabilityModelSelection();
			}
		});
	}
	
	private void handleDSLProgramFileSelection() {
		IFile dslProgramFile = BrowseDialogsHelper.browseForFile(getShell(), ResourcesPlugin.getWorkspace().getRoot(), "");
		if (dslProgramFile != null) {
			fDSLProgramFilenameText.setText(dslProgramFile.getFullPath().toPortableString());
			setDirty(true);
			updateLaunchConfigurationDialog();
		}		
	}
	
	private void handleTraceabilityModelSelection() {
		IFile traceabilityModelFile = BrowseDialogsHelper.browseForFile(getShell(), ResourcesPlugin.getWorkspace().getRoot(), "");
		if (traceabilityModelFile != null) {
			fTraceabilityModelFilenameText.setText(traceabilityModelFile.getFullPath().toPortableString());
			setDirty(true);
			updateLaunchConfigurationDialog();
		}		
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			fDSLProgramFilenameText.setText(configuration.getAttribute(IDSLDebuggerLaunchConfigurationConstants.ATTR_DSL_PROGRAM_FILENAME, ""));
			fTraceabilityModelFilenameText.setText(configuration.getAttribute(IDSLDebuggerLaunchConfigurationConstants.ATTR_TRACEABILITY_MODEL_FILENAME, ""));
		} catch (CoreException e) {			
			DSLDebuggerUIPlugin.log(e);
		}
		
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(IDSLDebuggerLaunchConfigurationConstants.ATTR_DSL_PROGRAM_FILENAME, fDSLProgramFilenameText.getText());
		configuration.setAttribute(IDSLDebuggerLaunchConfigurationConstants.ATTR_TRACEABILITY_MODEL_FILENAME, fTraceabilityModelFilenameText.getText());		
	}

	@Override
	public String getName() {
		return "DSL";
	}
	
}
