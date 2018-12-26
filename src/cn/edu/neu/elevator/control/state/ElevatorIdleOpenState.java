package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.actuator.DoorMotor;
import cn.edu.neu.elevator.display.GUIController;
import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorIdleOpenState extends ElevatorState {
    @Override
    public void onDoorClosed() {
        ElevatorLogger.info("Door Closed", "The elevator door has been closed.");
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_CLOSED_STATE);
    }

    @Override
    public void onDoorOpen() {
        ElevatorLogger.warning("Ignoring Signal", "The door is already opened.");
    }

    @Override
    public void onDoorBlocked() {
        ElevatorLogger.error("Invalid Operation", "Elevator is open. Door block operation is invalid.");
    }

    @Override
    public void onFloorButtonPressed(int floor) {
        ElevatorLogger.warning("Invalid Operation", "Elevator is open.Close the floor before you change floor.");
    }

    @Override
    public void onOpenButtonPressed() {
        ElevatorLogger.warning("Invalid Operation", "The door is already opened.");
    }

    @Override
    public void onCloseButtonPressed() {
        DoorMotor doorMotor = new DoorMotor();
        doorMotor.goClose();
        GUIController.getInstance().setElevatorStatus(false, "Closing door...");
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_CLOSING_STATE);
    }

    @Override
    public void onFloorReached(int current, int destination) {

    }
}