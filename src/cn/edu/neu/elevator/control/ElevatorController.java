package cn.edu.neu.elevator.control;

import cn.edu.neu.elevator.actuator.DoorMotor;
import cn.edu.neu.elevator.actuator.ElevatorMotor;
import cn.edu.neu.elevator.control.state.*;
import cn.edu.neu.elevator.linstener.DoorSensorListener;
import cn.edu.neu.elevator.linstener.ElevatorPanelListener;
import cn.edu.neu.elevator.linstener.FloorSensorListener;

/**
 * Elevator controller class
 */
public class ElevatorController implements DoorSensorListener, ElevatorPanelListener, FloorSensorListener {

    int currentFloor;
    int destinationFloor;
    /**
     * Predefined states with state pattern for elevator controller
     */
    public final ElevatorGoingUpClosedState ELEVATOR_GOING_UP_CLOSED_STATE = new ElevatorGoingUpClosedState();
    public final ElevatorGoingDownClosedState ELEVATOR_GOING_DOWN_CLOSED_STATE = new ElevatorGoingDownClosedState();
    public final ElevatorIdleOpenState ELEVATOR_IDLE_OPEN_STATE = new ElevatorIdleOpenState();
    public final ElevatorIdleClosedState ELEVATOR_IDLE_CLOSED_STATE = new ElevatorIdleClosedState();
    public final ElevatorIdleBlockedState ELEVATOR_IDLE_BLOCKED_STATE = new ElevatorIdleBlockedState();

    // state placeholder for elevator controller
    private ElevatorState currentElevatorState;


    public ElevatorController(int maxFloor) {
        MAX_FLOOR = maxFloor;
        elevatorMotor = new ElevatorMotor();
        doorMotor = new DoorMotor();
        // initial state
        currentElevatorState = ELEVATOR_IDLE_CLOSED_STATE;
        currentFloor = 1;
        destinationFloor = 1;
    }

    public final int MAX_FLOOR;
    static final int DEFAULT_FLOOR = 1;


    public ElevatorState getCurrentElevatorState() {
        return currentElevatorState;
    }

    public void setCurrentElevatorState(ElevatorState currentElevatorState) {
        this.currentElevatorState = currentElevatorState;
    }

    public ElevatorMotor getElevatorMotor() {
        return elevatorMotor;
    }

    public DoorMotor getDoorMotor() {
        return doorMotor;
    }

    /**
     * Actuators of elevator
     */
    private ElevatorMotor elevatorMotor;
    private DoorMotor doorMotor;

    /**
     * Methods implemented from listeners
     */
    @Override
    public void onDoorClosed() {

    }

    @Override
    public void onDoorOpen() {

    }

    @Override
    public void onDoorBlocked() {

    }

    @Override
    public void onFloorButtonPressed(int floor) {

    }

    @Override
    public void onOpenButtonPressed() {

    }

    @Override
    public void onCloseButtonPressed() {

    }

    @Override
    public void onFloorReached() {
        if (currentFloor < destinationFloor) {
            currentFloor++;
        } else if (currentFloor > destinationFloor) {
            currentFloor--;
        } else {
            this.getElevatorMotor().goBreak();
            return;
        }
        // continue to act the elevator motor if destination floor has not been reached
        activateElevatorMotor();
    }

    /**
     * Activate the motor if the current floor is not at the destination floor
     */
    private void activateElevatorMotor() {
        if (currentFloor < destinationFloor) {
            this.getElevatorMotor().goUp();
        } else if (currentFloor > destinationFloor) {
            this.getElevatorMotor().goDown();
        } else {
            this.getElevatorMotor().goBreak();
        }
    }

    @Override
    public void update() {
    }
}
