package cn.edu.neu.elevator;

import cn.edu.neu.elevator.control.ElevatorController;
import cn.edu.neu.elevator.external.Environment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Environment.getInstance().registerElevatorController(new ElevatorController(20));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("elevator.fxml"));
        primaryStage.setTitle("Elevator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
