 

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.ArrayList;


public class SelectBoxArrayList
{

    static Property answer;

    public static Property display(String title, ArrayList<Property> info, String message, String tip)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setTooltip(new Tooltip(tip));
        comboBox.setPromptText(message);


        for(int x = 0; x < info.size(); x++){
            comboBox.getItems().add(info.get(x).getName());
        }

        Button selectButton = new Button("Select");
        Button backButton = new Button("Back");


        selectButton.setOnAction(e -> {
            for(int i = 0; i < info.size(); i++){
                if (info.get(i).getName() == comboBox.getValue()){
                    answer = info.get(i);
                }
            }
            window.close();
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
        return answer;

    }
}

