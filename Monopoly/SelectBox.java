 

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class SelectBox
{

    static String answer;

    public static String display(String title, String[] info, String message, String tip)
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


        for(int x = 0; x < info.length; x++)
            comboBox.getItems().add(info[x]);



        Button selectButton = new Button("Select");
        Button backButton = new Button("Back");

        selectButton.setOnAction(e -> {
            answer = comboBox.getValue();
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
