package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.control.listener.DoorSensorListener;
import cn.edu.neu.elevator.control.listener.Listener;

/**
 * Door sensor simulation class
 */
public class DoorSensor extends Listenable {


    private DoorState currentDoorState;
    /**
     * 3 states for the door:
     * Open, closed and blockDoor
     */
    public enum DoorState {
        OPEN, CLOSED, BLOCKED;

    }

    public DoorSensor() {
        currentDoorState = DoorState.CLOSED;
    }

    public DoorState getCurrentDoorState() {
        return currentDoorState;
    }

    public void setCurrentDoorState(DoorState currentDoorState) {
        this.currentDoorState = currentDoorState;
        notifyEvent();
    }


    @Override
    public void notifyEvent() {
        switch (currentDoorState) {
            case OPEN:
                for (Listener doorListener : listeners) {
                    ((DoorSensorListener)doorListener).onDoorOpen();
                }
                break;
            case CLOSED:
                for (Listener doorListener : listeners) {
                    ((DoorSensorListener)doorListener).onDoorClosed();
                }
                break;
            case BLOCKED:
                for (Listener doorListener : listeners) {
                    ((DoorSensorListener)doorListener).onDoorBlocked();
                }
                break;
        }
    }
}
