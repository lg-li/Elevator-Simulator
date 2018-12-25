package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.control.ElevatorController;

public abstract class ElevatorState {



    private ElevatorController context;

    public void setContext(ElevatorController context) {
        this.context = context;
    }

    public abstract void closeDoor();

    public abstract void openDoor();

    public abstract void blockDoor();

    public abstract void goUp();

    public abstract void goDown();

}