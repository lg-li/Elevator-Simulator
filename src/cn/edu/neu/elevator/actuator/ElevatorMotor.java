package cn.edu.neu.elevator.actuator;

import cn.edu.neu.elevator.external.Environment;

/**
 * Elevator motor simulation class
 */
public class ElevatorMotor {

    public void goUp(){
        Environment.getInstance().runElevatorMotor(true);
    }

    public void goDown(){
        Environment.getInstance().runElevatorMotor(false);
    }

    public void goBreak(){
        Environment.getInstance().interruptElevatorMotor();
    }
}
