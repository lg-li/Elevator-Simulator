package cn.edu.neu.elevator.util;

public interface ElevatorLogger {
    void info(String producer, String content);
    void error(String producer, String content);
    void warning(String producer, String content);
}
