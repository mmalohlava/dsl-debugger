package cz.cuni.mff.d3s.dsl.debugger.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class DSLDebuggerUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "cz.cuni.mff.d3s.dsl.debugger.ui"; //$NON-NLS-1$

	// The shared instance
	private static DSLDebuggerUIPlugin plugin;
	
	/**
	 * The constructor
	 */
	public DSLDebuggerUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DSLDebuggerUIPlugin getDefault() {
		return plugin;
	}
	
	public static String getPluginId() {
		return PLUGIN_ID;
	}
	
	/**
	 * Logs an internal error with the specified throwable
	 * 
	 * @param e the exception to be logged
	 */	
	public static void log(Throwable e) {
		if (e instanceof CoreException) {
			log(new Status(IStatus.ERROR, getPluginId(), IStatus.ERROR, e.getMessage(), e.getCause()));
		} else {
			log(new Status(IStatus.ERROR, getPluginId(), IDSLDebuggerUIConstants.INTERNAL_ERROR, "Internal Error", e));   //$NON-NLS-1$
		}
	}
	
	/**
	 * Logs the specified status with this plug-in's log.
	 * 
	 * @param status status to log
	 */
	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}
	

}
