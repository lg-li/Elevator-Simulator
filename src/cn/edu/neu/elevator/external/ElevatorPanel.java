package cn.edu.neu.elevator.external;

import cn.edu.neu.elevator.control.listener.ElevatorPanelListener;
import cn.edu.neu.elevator.control.listener.Listener;

import java.util.ArrayList;

/**
 * Panel sensor simulation class
 */
public class ElevatorPanel extends Listenable {

    private ElevatorButton buttonPressed;

    public ElevatorPanel() {
        listeners = new ArrayList<>();
    }

    public void press(ElevatorButton buttonPressed) {
        this.buttonPressed = buttonPressed;
        notifyEvent();
    }

    @Override
    public void notifyEvent() {
        if (buttonPressed != null) {
            switch (buttonPressed.getButtonType()) {
                case OPEN_BUTTON:
                    for (Listener listener : listeners) {
                        ((ElevatorPanelListener) listener).onOpenButtonPressed();
                    }
                    break;
                case CLOSED_BUTTON:
                    for (Listener listener : listeners) {
                        ((ElevatorPanelListener) listener).onCloseButtonPressed();
                    }
                    break;
                case FLOOR_BUTTON:
                    for (Listener listener : listeners) {
                        ((ElevatorPanelListener) listener).onFloorButtonPressed(buttonPressed.getButtonValue());
                    }
                    break;
            }
            // clear pressed button signal when notified
            buttonPressed = null;
        }
    }
}
