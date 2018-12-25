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

    public abstract void onFloorReached(int current, int destination);

}