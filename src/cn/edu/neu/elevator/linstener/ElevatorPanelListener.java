package cn.edu.neu.elevator.linstener;

public interface ElevatorPanelListener extends Listener {
    public void onFloorButtonPressed(int floor);
    public void onOpenButtonPressed();
    public void onCloseButtonPressed();
}
