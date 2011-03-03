package gen.karel.api;

import cz.cuni.mff.d3s.karel.core.api.Karel;

public interface KarelTheRobot extends Karel {

    /**
     * User defined command SimpleCmd 
     */
	public void simpleCmd();
    /**
     * User defined command TurnLeft 
     */
	public void turnLeft();
    /**
     * User defined command TurnRight 
     */
	public void turnRight();
}	
