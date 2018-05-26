package sample;

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class AlertBox
{

    public static void display(String title, String message)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        label.setText(message);
        Button buttonCloser = new Button("Ok");
        buttonCloser.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, buttonCloser);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
