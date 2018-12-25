package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.actuator.DoorMotor;
import cn.edu.neu.elevator.actuator.ElevatorMotor;
import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorIdleClosedState extends ElevatorState{

    @Override
    public void onDoorClosed() {

    }

    @Override
    public void onDoorOpen() {
        ElevatorLogger.info("valid operation", "Elevator is open.");
        context.setCurrentElevatorState(new ElevatorIdleOpenState());
    }

    @Override
    public void onDoorBlocked() {

    }

    @Override
    public void onFloorButtonPressed(int floor) {
        ElevatorMotor elevatorMotor = new ElevatorMotor();
        context.setDestinationFloor(floor);
        if (context.getCurrentFloor() < floor)
            elevatorMotor.goUp();
        else if (context.getCurrentFloor() > floor)
            elevatorMotor.goDown();
        else
            ElevatorLogger.warning("Invalid Operation", "Current floor equals destionation floor.");
    }

    @Override
    public void onOpenButtonPressed() {
        DoorMotor doorMotor = new DoorMotor();
        doorMotor.goOpen();
    }

    @Override
    public void onCloseButtonPressed() {
        ElevatorLogger.warning("Invalid Operation", "Elevator is closed. close door operation is invalid.");
    }
}