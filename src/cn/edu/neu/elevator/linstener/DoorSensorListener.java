package cn.edu.neu.elevator.linstener;

public interface DoorSensorListener extends Listener {
    public void onDoorClosed();
    public void onDoorOpen();
    public void onDoorBlocked();
}
