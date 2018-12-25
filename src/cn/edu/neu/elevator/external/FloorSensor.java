package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.linstener.FloorSensorListener;
import cn.edu.neu.elevator.linstener.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Floor Sensor Simulation Class
 */
public class FloorSensor implements Listenable {

    public FloorSensor () {
        listeners = new ArrayList<>();
    }

    public void floorReached() {
        notifyEvent();
    }

    private List<FloorSensorListener> listeners;

    @Override
    public void attachListener(Listener listener) {
        listeners.add((FloorSensorListener) listener);
    }

    @Override
    public void detachListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyEvent() {
        for (FloorSensorListener listener : listeners) {
            listener.onFloorReached();
        }
    }
}
