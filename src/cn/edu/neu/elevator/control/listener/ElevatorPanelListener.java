package cn.edu.neu.elevator.control.listener;

public interface ElevatorPanelListener extends Listener {
    public void onFloorButtonPressed(int floor);

    public void onOpenButtonPressed();

    public void onCloseButtonPressed();
}
