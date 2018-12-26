package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.control.listener.FloorSensorListener;
import cn.edu.neu.elevator.control.listener.Listener;

import java.util.ArrayList;

/**
 * Floor Sensor Simulation Class
 */
public class FloorSensor extends Listenable {

    public FloorSensor() {
        listeners = new ArrayList<>();
    }

    public void floorReached() {
        notifyEvent();
    }

    @Override
    public void notifyEvent() {
        for (Listener listener : listeners) {
            ((FloorSensorListener) listener).onFloorReached();
        }
    }
}
