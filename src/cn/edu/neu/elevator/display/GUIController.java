package cn.edu.neu.elevator.display;

import cn.edu.neu.elevator.external.ElevatorButton;
import cn.edu.neu.elevator.external.Environment;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * GUI controller for elevator simulation
 * No logic of elevator included in this class.
 * Only public view operations provided.
 */
public class GUIController {

    /**
     * FXML injection fields
     */
    @FXML
    private static TextArea txtAreaLogger;

    @FXML
    private static Button btnOpenDoor;

    @FXML
    private static Button btnCloseDoor;

    @FXML
    private static Button btnBlockDoor;

    @FXML
    private static Button btnFloorSubmit;

    @FXML
    private static TextField txtFloor;

    @FXML
    private static ProgressIndicator progDoorIndicator;

    @FXML
    private static ProgressIndicator progRunning;

    @FXML
    private static Label txtStatus;

    @FXML
    private static Slider sliderFloor;

    @FXML
    private static Label txtFloorIndicator;

    @FXML
    private static Label txtDoorStatus;

    /**
     * Submit the text to the log area
     * @param text text to show
     */
    public static void submitLogText(String text) {
        txtAreaLogger.appendText(text+"\n");
    }

    /**
     * set the indicator's floor
     * @param floor
     */
    public static void setTxtFloorIndicator(int floor) {
        txtFloorIndicator.setText(floor+"F");
    }

    /**
     * Set the elevator status indicator
     * @param isIdle if the elevator is Idle
     * @param status the text status of elevator
     */
    public static void setElevatorStatus(boolean isIdle, String status) {
        txtStatus.setText(status);
        progRunning.setVisible(!isIdle);
    }

    /**
     * Elevator running delay simulation animation method
     * this method provided a way to increase or decrease the height of the elevator cart
     * @param diff the height to increase or decrease (minus)
     */
    public static void adjustSliderHeight(double diff) {
        double newValue = sliderFloor.getValue()+diff;
        if(newValue<sliderFloor.getMin()) {
            // too small value, set to minimum
            sliderFloor.adjustValue(sliderFloor.getMin());
        } else if (newValue>sliderFloor.getMax()) {
            // too large value, set to maximum
            sliderFloor.adjustValue(sliderFloor.getMax());
        } else {
            // valid value
            sliderFloor.adjustValue(sliderFloor.getValue() + diff);
        }
    }

    /**
     * Elevator door delay simulation animation
     * @param diff the value to change (<=0: Closed; >=100:Open; otherwise: in the moving progress or blocked)
     */
    public static void adjustDoorWidth(double diff) {
        double newValue = progDoorIndicator.getProgress()+diff;
        if(newValue < 0){
            progDoorIndicator.setProgress(0);
        } else if (newValue > 100) {
            progDoorIndicator.setProgress(100);
        } else {
            progDoorIndicator.setProgress(newValue);
        }
    }

    /**
     * Set the status of door with a text
     * @param status status text
     */
    public static void setDoorStatus(String status) {
        txtDoorStatus.setText(status);
    }


    /**
     * Submit floor method
     * Simulation of pressing on a floor button
     * This method will invoke the press button method in the environment interface
     */
    public static void submitFloor() {
        // press the button on the environment
        // and environment will forward the request to elevator panel
        Environment.getInstance().pressButton(
                new ElevatorButton(ElevatorButton.ButtonType.FLOOR_BUTTON, Integer.valueOf(txtFloor.getText()))
        );
    }

}
