package cn.edu.neu.elevator.util;

import cn.edu.neu.elevator.display.GUIController;

public class ElevatorLogger {
    public static void info(String producer, String content) {
        GUIController.getInstance().submitLogText("INFO ["+producer+"] " + content);
    }

    public void error(String producer, String content) {
        GUIController.getInstance().submitLogText("ERROR ["+producer+"] " + content);
    }

    public void warning(String producer, String content) {
        GUIController.getInstance().submitLogText("WARNING ["+producer+"] " + content);
    }
}
