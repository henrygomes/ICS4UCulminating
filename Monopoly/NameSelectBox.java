
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class NameSelectBox
{

    static String answer;

    public static int display(String title, Player[] info, String message, String tip, String currentName)
    {
        final int[] returnInt = {0};
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setTooltip(new Tooltip(tip));
        comboBox.setPromptText(message);


        for(int x = 0; x < info.length; x++){
            if (info[x].getName() != currentName){
                comboBox.getItems().add(info[x].getName());
                System.out.println("Added Name" + info[x].getName());
            }
        }



        Button selectButton = new Button("Select");
        Button backButton = new Button("Back");

        selectButton.setOnAction(e -> {
            for (int x = 0; x < info.length; x++) {
                if (comboBox.getValue() != info[x].getName()){
                    returnInt[0] = x;
                    window.close();
                }
            }
        });

        backButton.setOnAction(e -> {
            window.close();
        });

        comboBox.setOnAction(e -> System.out.println("User Selected: " + comboBox.getValue()));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 30, 10, 30));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField passInput = new TextField();
        passInput.setPromptText(tip);


        GridPane.setConstraints(comboBox, 0, 0);
        GridPane.setConstraints(selectButton, 1, 0);
        GridPane.setConstraints(backButton, 2, 0);

        grid.getChildren().addAll(comboBox, selectButton, backButton);


        Scene scene = new Scene(grid, 400, 50);
        window.setScene(scene);
        window.showAndWait();
        return returnInt[0];

    }
}

