package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.actuator.DoorMotor;
import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorIdleOpenState extends ElevatorState{


    @Override
    public void onDoorClosed() {
        //doNothing
    }

    @Override
    public void onDoorOpen() {
        ElevatorLogger.error("Invalid Operation", "Elevator is open. Open door operation is invalid.");
    }

    @Override
    public void onDoorBlocked() {
        ElevatorLogger.error("Invalid Operation", "Elevator is open. Door block operation is invalid.");
    }

    @Override
    public void onFloorButtonPressed(int floor) {
        ElevatorLogger.warning("Invalid Operation", "Elevator is open. Change floor operation is invalid.");
    }

    @Override
    public void onOpenButtonPressed() {
        ElevatorLogger.warning("Invalid Operation", "Elevator is open. Open door operation is invalid.");
    }

    @Override
    public void onCloseButtonPressed() {
        DoorMotor doorMotor = new DoorMotor();
        doorMotor.goClose();
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_CLOSING_STATE);
    }

    @Override
    public void onFloorReached(int current, int destination) {

    }
}