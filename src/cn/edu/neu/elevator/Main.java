package cn.edu.neu.elevator;

import cn.edu.neu.elevator.display.DisabledDisplay;
import cn.edu.neu.elevator.display.Display;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    // default display is disabled
    private static Display display = new DisabledDisplay();

    public static Display getDisplay() {
        return display;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("elevator.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Elevator");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        display = fxmlLoader.getController();   //获取Controller的实例对象
        primaryStage.show();
    }
}
