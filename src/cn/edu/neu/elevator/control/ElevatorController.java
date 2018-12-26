package cn.edu.neu.elevator.control;

import cn.edu.neu.elevator.Main;
import cn.edu.neu.elevator.actuator.DoorMotor;
import cn.edu.neu.elevator.actuator.ElevatorMotor;
import cn.edu.neu.elevator.control.listener.DoorSensorListener;
import cn.edu.neu.elevator.control.listener.ElevatorPanelListener;
import cn.edu.neu.elevator.control.listener.FloorSensorListener;
import cn.edu.neu.elevator.control.state.*;

/**
 * Elevator controller class
 */
public class ElevatorController implements DoorSensorListener, ElevatorPanelListener, FloorSensorListener {

    public static final int DEFAULT_FLOOR = 1;
    public final int MAX_FLOOR;
    /**
     * Predefined states with state pattern for elevator controller
     */
    public final ElevatorGoingUpClosedState ELEVATOR_GOING_UP_CLOSED_STATE = new ElevatorGoingUpClosedState();
    public final ElevatorGoingDownClosedState ELEVATOR_GOING_DOWN_CLOSED_STATE = new ElevatorGoingDownClosedState();
    public final ElevatorIdleOpenState ELEVATOR_IDLE_OPEN_STATE = new ElevatorIdleOpenState();
    public final ElevatorIdleClosingState ELEVATOR_IDLE_CLOSING_STATE = new ElevatorIdleClosingState();
    public final ElevatorIdleClosedState ELEVATOR_IDLE_CLOSED_STATE = new ElevatorIdleClosedState();
    public final ElevatorIdleBlockedState ELEVATOR_IDLE_BLOCKED_STATE = new ElevatorIdleBlockedState();
    /**
     * Data attributes storing current floor
     */
    private int currentFloor;
    /**
     * Data attributes storing destination floor
     */
    private int destinationFloor;
    /**
     * state placeholder for elevator controller
     */
    private ElevatorState currentElevatorState;
    /**
     * Actuators of elevator
     */
    private ElevatorMotor elevatorMotor;
    private DoorMotor doorMotor;

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
        Main.getDisplay().setElevatorStatus(true, "Idle");
        Main.getDisplay().setDoorStatus("Closed");
        Main.getDisplay().setDoorWidth(0);
        currentElevatorState.onDoorClosed();
    }

    @Override
    public void onDoorOpen() {
        Main.getDisplay().setElevatorStatus(true, "Idle");
        Main.getDisplay().setDoorStatus("Open");
        Main.getDisplay().setDoorWidth(1);
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
        Main.getDisplay().setTxtFloorIndicator(currentFloor);
        Main.getDisplay().setSliderHeight(currentFloor);
    }

    @Override
    public void update() {
    }
}
