package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.linstener.Listener;


public interface Listenable {
    public void attachListener(Listener listener);
    public void detachListener(Listener listener);
    public void notifyEvent();
}
