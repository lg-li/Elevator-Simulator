package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.control.ElevatorController;
import cn.edu.neu.elevator.external.DoorSensor.*;

/**
 * Elevator Environment Simulation Class
 * As there is only one environment for one independent elevator system
 * Singleton design is applied to the environment simulation class
 */
public class Environment {
    private static Environment INSTANCE;

    private DoorSensor doorSensor;
    private ElevatorPanel elevatorPanel;
    private FloorSensor floorSensor;

    private Environment() {
        doorSensor = new DoorSensor();
        elevatorPanel = new ElevatorPanel();
        floorSensor = new FloorSensor();
    }

    /**
     * Get singleton instance of the Environment
     * @return the instance reference
     */
    public static Environment getInstance(){
        if(INSTANCE == null) {
            synchronized (Environment.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Environment();
                }
            }
        }
        return INSTANCE;
    }

    public void registerElevatorController(ElevatorController controller){
        doorSensor.attachListener(controller);
        elevatorPanel.attachListener(controller);
        floorSensor.attachListener(controller);
    }

    public void floorReached(){
        floorSensor.notifyEvent();
    }

    public void pressButton(ElevatorButton buttonPressed) {
        elevatorPanel.press(buttonPressed);
    }

    public void closeDoor() {
        doorSensor.setCurrentDoorState(DoorState.CLOSED);
        doorSensor.notifyEvent();
    }

    public void openDoor() {
        doorSensor.setCurrentDoorState(DoorState.OPEN);
        doorSensor.notifyEvent();
    }

    public void blockDoor() {
        doorSensor.setCurrentDoorState(DoorState.BLOCKED);
        doorSensor.notifyEvent();
    }
}
