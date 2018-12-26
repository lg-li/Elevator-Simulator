package cn.edu.neu.elevator.control.listener;

public interface DoorSensorListener extends Listener {
    public void onDoorClosed();

    public void onDoorOpen();

    public void onDoorBlocked();
}
