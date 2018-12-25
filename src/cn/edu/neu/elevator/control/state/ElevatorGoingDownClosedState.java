package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorGoingDownClosedState extends ElevatorState{

    @Override
    public void onDoorClosed() {

    }

    @Override
    public void onDoorOpen() {

    }

    @Override
    public void onDoorBlocked() {
        ElevatorLogger.info("Invalid Operation", "Elevator is going down. Block door operation is invalid.");
    }

    @Override
    public void onFloorButtonPressed(int floor) {

    }

    @Override
    public void onOpenButtonPressed() {
        ElevatorLogger.info("Invalid Operation", "Elevator is going down. Open door operation is invalid.");
    }

    @Override
    public void onCloseButtonPressed() {
        ElevatorLogger.info("Invalid Operation", "Elevator is going down. Close door operation is invalid.");
    }
}
