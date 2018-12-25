package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.control.ElevatorController;

public abstract class ElevatorState {

    int currentFloor;
    int destinationFloor;

    private ElevatorController context;
    public void setContext (ElevatorController context){
        this.context = context;
    }

    public void onFloorReached() {
        if (currentFloor < destinationFloor) {
            currentFloor++;
        } else if (currentFloor > destinationFloor) {
            currentFloor--;
        } else {
            context.getElevatorMotor().goBreak();
            return;
        }
        // continue to act the elevator motor if destination floor has not been reached
        activateElevatorMotor();
    }

    /**
     * Activate the motor if the current floor is not at the destination floor
     */
    private void activateElevatorMotor(){
        if (currentFloor < destinationFloor) {
            context.getElevatorMotor().goUp();
        } else if (currentFloor > destinationFloor) {
            context.getElevatorMotor().goDown();
        } else {
            context.getElevatorMotor().goBreak();
        }
    }



}