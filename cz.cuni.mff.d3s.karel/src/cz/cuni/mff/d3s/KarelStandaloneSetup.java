
package cz.cuni.mff.d3s;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class KarelStandaloneSetup extends KarelStandaloneSetupGenerated{

	public static void doSetup() {
		new KarelStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

