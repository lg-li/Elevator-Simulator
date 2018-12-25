package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.linstener.Listener;

import java.util.ArrayList;
import java.util.List;


public class Listenable {
    private List<Listener> listeners = new ArrayList<>();

    public void attachListener(Listener listener){
        listeners.add(listener);
    }

    public void detachListener(Listener listener){
        listeners.remove(listener);
    }

    public void notifyEvent(){
        listeners.forEach(listener -> listener.update());
    }
}
