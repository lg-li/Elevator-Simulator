package cn.edu.neu.elevator.util;

import cn.edu.neu.elevator.Main;

public class ElevatorLogger {
    public static void info(String producer, String content) {
        Main.getDisplay().submitLogText("INFO [" + producer + "] " + content);
    }

    public static void error(String producer, String content) {
        Main.getDisplay().submitLogText("ERROR [" + producer + "] " + content);
    }

    public static void warning(String producer, String content) {
        Main.getDisplay().submitLogText("WARNING [" + producer + "] " + content);
    }
}
