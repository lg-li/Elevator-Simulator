package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.Main;
import cn.edu.neu.elevator.display.GUIDisplay;
import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorIdleClosingState extends ElevatorState {
    @Override
    public void onDoorClosed() {
        ElevatorLogger.info("Door Closed", "The elevator door has been closed.");
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_CLOSED_STATE);
    }

    @Override
    public void onDoorOpen() {
        ElevatorLogger.warning("Door Opened", "The elevator door has been opened unexpectedly.");
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_OPEN_STATE);
    }

    @Override
    public void onDoorBlocked() {
        Main.getDisplay().setDoorStatus("Blocked");
        Main.getDisplay().setElevatorStatus(true, "Blocked");
        ElevatorLogger.info("Blocked", "Door was blocked when closing. Now you cannot operate the door for 5 seconds.");
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_BLOCKED_STATE);
        context.getDoorMotor().goBreak();
        // Thread to delay the block state automatic transition
        new Thread(() -> {
            // sleep for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            context.getDoorMotor().goOpen();
        }).start();
    }

    @Override
    public void onFloorButtonPressed(int floor) {

    }

    @Override
    public void onOpenButtonPressed() {

    }

    @Override
    public void onCloseButtonPressed() {

    }

    @Override
    public void onFloorReached(int current, int destination) {

    }
}
