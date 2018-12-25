package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.linstener.DoorSensorListener;
import cn.edu.neu.elevator.linstener.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Door sensor simulation class
 */
public class DoorSensor implements Listenable {

    /**
     * 3 states for the door:
     * Open, closed and blocked
     */
    public enum DoorState {
        OPEN, CLOSED, BLOCKED
    }

    public DoorSensor() {
        currentDoorState = DoorState.CLOSED;
        listeners = new ArrayList<>();
    }

    private DoorState currentDoorState;

    public DoorState getCurrentDoorState() {
        return currentDoorState;
    }

    public void setCurrentDoorState(DoorState currentDoorState) {
        this.currentDoorState = currentDoorState;
        notifyEvent();
    }

    private List<DoorSensorListener> listeners;

    @Override
    public void attachListener(Listener listener) {
        listeners.add((DoorSensorListener) listener);
    }

    @Override
    public void detachListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyEvent() {
        switch (currentDoorState) {
            case OPEN:
                for (DoorSensorListener doorListener : listeners) {
                    doorListener.onDoorOpen();
                }
                break;
            case CLOSED:
                for (DoorSensorListener doorListener : listeners) {
                    doorListener.onDoorClosed();
                }
                break;
            case BLOCKED:
                for (DoorSensorListener doorListener : listeners) {
                    doorListener.onDoorBlocked();
                }
                break;
        }
    }
}
