package cn.edu.neu.elevator.control;

import cn.edu.neu.elevator.actuator.DoorMotor;
import cn.edu.neu.elevator.actuator.ElevatorMotor;
import cn.edu.neu.elevator.control.state.*;
import cn.edu.neu.elevator.display.GUIController;
import cn.edu.neu.elevator.control.listener.DoorSensorListener;
import cn.edu.neu.elevator.control.listener.ElevatorPanelListener;
import cn.edu.neu.elevator.control.listener.FloorSensorListener;

/**
 * Elevator controller class
 */
public class ElevatorController implements DoorSensorListener, ElevatorPanelListener, FloorSensorListener {

    /**
     * Data attributes storing current floor
     */
    private int currentFloor;

    /**
     * Data attributes storing destination floor
     */
    private int destinationFloor;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    /**
     * state placeholder for elevator controller
     */
    private ElevatorState currentElevatorState;
    public final int MAX_FLOOR;
    public static final int DEFAULT_FLOOR = 1;
    /**
     * Actuators of elevator
     */
    private ElevatorMotor elevatorMotor;
    private DoorMotor doorMotor;

    /**
     * Predefined states with state pattern for elevator controller
     */
    public final ElevatorGoingUpClosedState ELEVATOR_GOING_UP_CLOSED_STATE = new ElevatorGoingUpClosedState();
    public final ElevatorGoingDownClosedState ELEVATOR_GOING_DOWN_CLOSED_STATE = new ElevatorGoingDownClosedState();
    public final ElevatorIdleOpenState ELEVATOR_IDLE_OPEN_STATE = new ElevatorIdleOpenState();
    public final ElevatorIdleClosingState ELEVATOR_IDLE_CLOSING_STATE = new ElevatorIdleClosingState();
    public final ElevatorIdleClosedState ELEVATOR_IDLE_CLOSED_STATE = new ElevatorIdleClosedState();
    public final ElevatorIdleBlockedState ELEVATOR_IDLE_BLOCKED_STATE = new ElevatorIdleBlockedState();

    public ElevatorController(int maxFloor) {
        // initialize state context
        MAX_FLOOR = maxFloor;
        elevatorMotor = new ElevatorMotor();
        doorMotor = new DoorMotor();
        // initial state
        currentElevatorState = ELEVATOR_IDLE_CLOSED_STATE;
        currentFloor = DEFAULT_FLOOR;
        destinationFloor = DEFAULT_FLOOR;
        initializeStateContext();
    }

    /**
     * Initialize the context reference of all states
     */
    private void initializeStateContext() {
        ELEVATOR_GOING_UP_CLOSED_STATE.setContext(this);
        ELEVATOR_GOING_DOWN_CLOSED_STATE.setContext(this);
        ELEVATOR_IDLE_OPEN_STATE.setContext(this);
        ELEVATOR_IDLE_CLOSED_STATE.setContext(this);
        ELEVATOR_IDLE_CLOSING_STATE.setContext(this);
        ELEVATOR_IDLE_BLOCKED_STATE.setContext(this);
    }

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
     * Methods implemented from listeners
     */
    @Override
    public void onDoorClosed() {
        GUIController.getInstance().setElevatorStatus(true, "Idle");
        GUIController.getInstance().setDoorStatus("Closed");
        GUIController.getInstance().setDoorWidth(0);
        currentElevatorState.onDoorClosed();
    }

    @Override
    public void onDoorOpen() {
        GUIController.getInstance().setElevatorStatus(true, "Idle");
        GUIController.getInstance().setDoorStatus("Open");
        GUIController.getInstance().setDoorWidth(1);
        currentElevatorState.onDoorOpen();
    }

    @Override
    public void onDoorBlocked() {
        currentElevatorState.onDoorBlocked();
    }

    @Override
    public void onFloorButtonPressed(int floor) {
        currentElevatorState.onFloorButtonPressed(floor);
    }

    @Override
    public void onOpenButtonPressed() {
        currentElevatorState.onOpenButtonPressed();
    }

    @Override
    public void onCloseButtonPressed() {
        currentElevatorState.onCloseButtonPressed();
    }

    @Override
    public void onFloorReached() {
        currentElevatorState.onFloorReached(currentFloor, destinationFloor);
        GUIController.getInstance().setTxtFloorIndicator(currentFloor);
        GUIController.getInstance().setSliderHeight(currentFloor);
    }

    @Override
    public void update() {
    }
}
