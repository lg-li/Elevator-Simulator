package cn.edu.neu.elevator.actuator;

import cn.edu.neu.elevator.external.Environment;

/**
 * Elevator door motor simulation class
 */
public class DoorMotor {

    public void goOpen(){
        Environment.getInstance().runDoorMotor(true);
    }

    public void goClose(){
        Environment.getInstance().runDoorMotor(false);
    }

    public void goBreak(){

    }
}
