package gen.karel.api.impl;

import gen.karel.api.KarelTheRobot;
import cz.cuni.mff.d3s.karel.core.api.impl.KarelImpl;

public class KarelTheRobotImpl extends KarelImpl implements KarelTheRobot {

	    /** 
	     * This is the implementation of Karel's command defined by user.
	     *
	     * This is a simple user defined command
	     */
		public void simpleCmd() {	
				this.move();				
										
				this.move();				
										
		}	
	    /** 
	     * This is the implementation of Karel's command defined by user.
	     *
	     * org.eclipse.emf.ecore.impl.DynamicEObjectImpl@2c803533 (eClass: org.eclipse.emf.ecore.impl.EClassImpl@1524be18 (name: OclInvalid_Class) (instanceClassName: null) (abstract: false, interface: false))
	     */
		public void turnLeft() {	
				this.turn();				
										
		}	
	    /** 
	     * This is the implementation of Karel's command defined by user.
	     *
	     * org.eclipse.emf.ecore.impl.DynamicEObjectImpl@2c803533 (eClass: org.eclipse.emf.ecore.impl.EClassImpl@1524be18 (name: OclInvalid_Class) (instanceClassName: null) (abstract: false, interface: false))
	     */
		public void turnRight() {	
				this.turn();				
										
				this.turn();				
										
				this.turn();				
										
		}	
}	
