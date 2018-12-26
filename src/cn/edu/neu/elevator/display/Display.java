package cn.edu.neu.elevator.display;

/**
 * This interface is defined to reduce the coupling
 * between the display controller classes and other classes
 */
public abstract class Display {

    protected static Display instance;

    public static Display getInstance() {
        return instance;
    }

    /**
     * Submit the text to the log area
     *
     * @param text text to show
     */
    abstract public void submitLogText(String text);

    /**
     * set the indicator's floor
     *
     * @param floor floor value
     */
    abstract public void setTxtFloorIndicator(int floor);

    /**
     * Set the elevator status indicator
     *
     * @param isIdle if the elevator is Idle
     * @param status the text status of elevator
     */
    abstract public void setElevatorStatus(boolean isIdle, String status);

    /**
     * Elevator running delay simulation animation method
     * this method provided a way to increase or decrease the height of the elevator cart
     *
     * @param diff the height to increase or decrease (minus)
     */
    abstract public void adjustSliderHeight(double diff);

    abstract public void setSliderHeight(double value);

    /**
     * Elevator door delay simulation animation
     *
     * @param diff the value to change (<=0: Closed; >=100:Open; otherwise: in the moving progress or blockDoor)
     */
    abstract public void adjustDoorWidth(double diff);

    abstract public void setDoorWidth(double value);

    /**
     * Set the status of door with a text
     *
     * @param status status text
     */
    abstract public void setDoorStatus(String status);
}
