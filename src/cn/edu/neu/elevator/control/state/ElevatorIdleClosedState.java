package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.Main;
import cn.edu.neu.elevator.actuator.DoorMotor;
import cn.edu.neu.elevator.display.GUIDisplay;
import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorIdleClosedState extends ElevatorState {

    @Override
    public void onDoorClosed() {
        ElevatorLogger.error("Invalid Operation", "Elevator is closed. Close door operation is invalid.");
    }

    @Override
    public void onDoorOpen() {
        ElevatorLogger.info("Door Open", "Elevator is open.");
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_OPEN_STATE);
    }

    @Override
    public void onDoorBlocked() {
        ElevatorLogger.error("Invalid Operation", "Elevator is closed. Door block operation is invalid.");
    }

    @Override
    public void onFloorButtonPressed(int floor) {
        if (floor < 1 || floor > context.MAX_FLOOR) {
            ElevatorLogger.error("Invalid Input", "The floor is out of bound.");
            return;
        }
        context.setDestinationFloor(floor);
        if (context.getCurrentFloor() < floor) {
            context.getElevatorMotor().goUp();
            Main.getDisplay().setElevatorStatus(false, "Going up...");
            context.setCurrentElevatorState(context.ELEVATOR_GOING_UP_CLOSED_STATE);
        } else if (context.getCurrentFloor() > floor) {
            context.getElevatorMotor().goDown();
            Main.getDisplay().setElevatorStatus(false, "Going down...");
            context.setCurrentElevatorState(context.ELEVATOR_GOING_DOWN_CLOSED_STATE);
        } else
            ElevatorLogger.warning("Invalid Operation", "Current floor equals destination floor.");
    }

    @Override
    public void onOpenButtonPressed() {
        DoorMotor doorMotor = new DoorMotor();
        doorMotor.goOpen();
    }

    @Override
    public void onCloseButtonPressed() {
        ElevatorLogger.warning("Invalid Operation", "Elevator is closed. Close door operation is invalid.");
    }

    @Override
    public void onFloorReached(int current, int destination) {

    }
}