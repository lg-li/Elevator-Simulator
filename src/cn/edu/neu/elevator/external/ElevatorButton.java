package cn.edu.neu.elevator.external;

/**
 * Elevator button simulation class for Panel Sensor when invoke press method
 */
public class ElevatorButton {

    private ButtonType buttonType;
    private int buttonValue;

    /**
     * enum types for button types
     */
    public enum ButtonType {
        OPEN_BUTTON,
        CLOSED_BUTTON,
        FLOOR_BUTTON
    }

    /**
     * Constructor for openDoor or closeDoor button (no need for button value)
     * @param buttonType Button method
     */
    public ElevatorButton (ButtonType buttonType) {
        this(buttonType, 0);
    }

    /**
     * Constructor for openDoor, closeDoor or floor button
     * @param buttonType Button method
     * @param buttonValue Button value when type is floor button
     */
    public ElevatorButton (ButtonType buttonType, int buttonValue) {
        this.buttonValue = buttonValue;
        this.buttonType = buttonType;
    }

    public ButtonType getButtonType() {
        return buttonType;
    }

    public void setButtonType(ButtonType buttonType) {
        this.buttonType = buttonType;
    }

    public int getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(int buttonValue) {
        this.buttonValue = buttonValue;
    }
}