package cn.edu.neu.elevator.display;

public class DisabledDisplay extends Display {

    public DisabledDisplay() {
        instance = this;
    }

    @Override
    public void submitLogText(String text) {

    }

    @Override
    public void setTxtFloorIndicator(int floor) {

    }

    @Override
    public void setElevatorStatus(boolean isIdle, String status) {

    }

    @Override
    public void adjustSliderHeight(double diff) {

    }

    @Override
    public void setSliderHeight(double value) {

    }

    @Override
    public void adjustDoorWidth(double diff) {

    }

    @Override
    public void setDoorWidth(double value) {

    }

    @Override
    public void setDoorStatus(String status) {

    }
}
