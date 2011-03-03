package cz.cuni.mff.d3s.dsl.debugger;

import org.eclipse.acceleo.engine.event.IAcceleoTextGenerationListener;
import org.eclipse.acceleo.engine.service.AcceleoService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import cz.cuni.mff.d3s.dsl.debugger.listeners.AcceleoGenerationListener;

/**
 * The activator class controls the plug-in life cycle
 */
public class DSLDebuggerCore extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "cz.cuni.mff.d3s.dsl.debugger"; //$NON-NLS-1$

	// The shared instance
	private static DSLDebuggerCore plugin;
	
	private IAcceleoTextGenerationListener acceleoTextGenerationListener = new AcceleoGenerationListener();
	
	/**
	 * The constructor
	 */
	public DSLDebuggerCore() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		AcceleoService.addStaticListener(this.acceleoTextGenerationListener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		
		AcceleoService.removeStaticListener(this.acceleoTextGenerationListener);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DSLDebuggerCore getDefault() {
		return plugin;
	}

}
