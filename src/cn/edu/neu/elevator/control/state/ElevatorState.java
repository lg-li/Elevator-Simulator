package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.control.ElevatorController;

public abstract class ElevatorState {

    protected ElevatorController context;

    public void setContext(ElevatorController context) {
        this.context = context;
    }
    public abstract void onDoorClosed();

    public abstract void onDoorOpen();

    public abstract void onDoorBlocked();

    public abstract void onFloorButtonPressed(int floor);

    public abstract void onOpenButtonPressed();

    public abstract void onCloseButtonPressed();

    public void onFloorReached(int current, int destination) {
        if(current < destination){
            context.getElevatorMotor().goDown();
            context.setCurrentFloor(current-1);
            context.setCurrentElevatorState(context.ELEVATOR_GOING_DOWN_CLOSED_STATE);
        } else if(current > destination) {
            context.getElevatorMotor().goUp();
            context.setCurrentFloor(current+1);
            context.setCurrentElevatorState(context.ELEVATOR_GOING_UP_CLOSED_STATE);
        } else {
            context.getElevatorMotor().goBreak();
            context.setCurrentElevatorState(context.ELEVATOR_IDLE_CLOSED_STATE);
            context.getDoorMotor().goOpen();
        }
    }

}