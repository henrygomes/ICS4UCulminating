 

 

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class MortgageBox
{

    static boolean answer;

    public static boolean display(String title, String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Mortgage");
        Button noButton = new Button("Un Mortgage");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 30, 10, 30));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);


        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(yesButton, 0, 1);
        GridPane.setConstraints(noButton, 1, 1);

        grid.getChildren().addAll(label, yesButton, noButton);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
        return answer;

    }
}

