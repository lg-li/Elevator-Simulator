package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorIdleBlockedState extends ElevatorState {

    @Override
    public void onDoorClosed() {
        ElevatorLogger.error("Invalid Sensor Signal", "Received an invalid signal when running. Door has been closed unexpectedly.");
    }

    @Override
    public void onDoorOpen() {
        ElevatorLogger.error("Invalid Sensor Signal", "Received an invalid signal when running. Door has been opened unexpectedly.");
    }

    @Override
    public void onDoorBlocked() {
        ElevatorLogger.info("Block Signal", "Already blocked. Ignoring block signal from sensor.");
    }

    @Override
    public void onFloorButtonPressed(int floor) {
        ElevatorLogger.info("Blocked", "Operation not allowed");
    }

    @Override
    public void onOpenButtonPressed() {
        ElevatorLogger.info("Blocked", "Operation not allowed");
    }

    @Override
    public void onCloseButtonPressed() {
        ElevatorLogger.info("Blocked", "Operation not allowed");
    }

    @Override
    public void onFloorReached(int current, int destination) {
        ElevatorLogger.error("Invalid Sensor Signal", "Received an invalid signal when running. Motor starts work unexpectedly.");
    }
}