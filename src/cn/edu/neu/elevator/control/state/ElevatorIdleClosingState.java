package cn.edu.neu.elevator.control.state;

import cn.edu.neu.elevator.util.ElevatorLogger;

public class ElevatorIdleClosingState extends ElevatorState{
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
        ElevatorLogger.info("Blocked", "Door was blocked when closing. Now you cannot close the door in 5 seconds.");
        context.setCurrentElevatorState(context.ELEVATOR_IDLE_BLOCKED_STATE);
        context.getDoorMotor().goBreak();
        new Thread(()->{
            // sleep for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            context.setCurrentElevatorState(context.ELEVATOR_IDLE_OPEN_STATE);
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
