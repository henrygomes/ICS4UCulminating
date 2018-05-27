package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application 
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Console Desktop");
        primaryStage.setScene(new Scene(root, 1200, 800));
        //Circle circle = new Circle(0, 0, 10);
        //root.getChildren().add(circle);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

