package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.linstener.ElevatorPanelListener;
import cn.edu.neu.elevator.linstener.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Panel sensor simulation class
 */
public class ElevatorPanel implements Listenable{

    public ElevatorPanel () {
        listeners = new ArrayList<>();
    }

    private ElevatorButton buttonPressed;

    public void press(ElevatorButton buttonPressed) {
        this.buttonPressed = buttonPressed;
        notifyEvent();
    }

    private List<ElevatorPanelListener> listeners;
    @Override
    public void attachListener(Listener listener) {
        listeners.add((ElevatorPanelListener) listener);
    }

    @Override
    public void detachListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyEvent() {
        if(buttonPressed != null) {
            switch (buttonPressed.getButtonType()) {
                case OPEN_BUTTON:
                    for (ElevatorPanelListener listener : listeners) {
                        listener.onOpenButtonPressed();
                    }
                    break;
                case CLOSED_BUTTON:
                    for (ElevatorPanelListener listener : listeners) {
                        listener.onCloseButtonPressed();
                    }
                    break;
                case FLOOR_BUTTON:
                    for (ElevatorPanelListener listener : listeners) {
                        listener.onFloorButtonPressed(buttonPressed.getButtonValue());
                    }
                    break;
            }
            // clear pressed button signal when notified
            buttonPressed = null;
        }
    }
}
