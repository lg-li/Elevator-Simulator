package cn.edu.neu.elevator.control;

import cn.edu.neu.elevator.control.state.*;
import cn.edu.neu.elevator.external.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class ElevatorControllerTest {
    ElevatorController elevatorController = new ElevatorController(10);

    @Test
    public void testIdleClosedToIdleOpen() {
        elevatorController.onOpenButtonPressed();


        elevatorController.onDoorOpen();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleOpenState);
    }

    @Test
    public void testIdleOpenToIdleClosing() {
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_IDLE_OPEN_STATE);
        elevatorController.onCloseButtonPressed();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleClosingState);
    }

    @Test
    public void testIdleClosingToIdleBlocked() {
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_IDLE_OPEN_STATE);
        elevatorController.onCloseButtonPressed();
        elevatorController.onDoorBlocked();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleBlockedState);
        elevatorController.onDoorClosed();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleBlockedState);
        elevatorController.onDoorOpen();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleBlockedState);

    }

    @Test
    public void testIdleClosingToIdleClosed() {
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_IDLE_CLOSING_STATE);
        elevatorController.onDoorClosed();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleClosedState);
    }

    @Test
    public void testIdleOpenToIdleClosed() {
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_IDLE_OPEN_STATE);
        elevatorController.onCloseButtonPressed();
        elevatorController.onDoorClosed();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleClosedState);
    }

    @Test
    public void testIdleClosedToGoingUp() {
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_IDLE_CLOSED_STATE);
        elevatorController.onFloorButtonPressed(2);
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorGoingUpClosedState);
    }

    @Test
    public void testIdleGoingUpToIdle() {
        elevatorController.setDestinationFloor(2);
        Environment.getInstance().runElevatorMotor(true);
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_GOING_UP_CLOSED_STATE);
        elevatorController.onFloorReached();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleClosedState);
    }

    @Test
    public void testIdleClosedToGoingDown() {
        elevatorController.setCurrentFloor(2);
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_IDLE_CLOSED_STATE);
        elevatorController.onFloorButtonPressed(1);
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorGoingDownClosedState);
    }

    @Test
    public void testGoingDownToIdleClosed() {
        elevatorController.setCurrentFloor(2);
        elevatorController.setDestinationFloor(1);
        Environment.getInstance().runElevatorMotor(false);
        elevatorController.setCurrentElevatorState(elevatorController.ELEVATOR_GOING_DOWN_CLOSED_STATE);
        elevatorController.onFloorReached();
        assertTrue(elevatorController.getCurrentElevatorState() instanceof ElevatorIdleClosedState);
    }
}