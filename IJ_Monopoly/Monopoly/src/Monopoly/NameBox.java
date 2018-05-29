package Monopoly;

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.ArrayList;


public class NameBox
{

    static String answer;

    public static ArrayList<String> display()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select Names");
        window.setMinWidth(250);
        ArrayList<String> names = new ArrayList<String>();
        Label label1 = new Label("Player 1 Name:");
        Label label2 = new Label("Player 2 Name:");
        Label label3 = new Label("Player 3 Name:");
        Label label4 = new Label("Player 4 Name:");

        TextField name1 = new TextField();
        TextField name2 = new TextField();
        TextField name3 = new TextField();
        TextField name4 = new TextField();


        Button selectButton = new Button("Start");
        Button closeButton = new Button("Quit");

        selectButton.setOnAction(action -> {
            if (name1.getText().length() != 0){
                System.out.println(name1.getText());
                names.add(name1.getText());
            }

            if (name2.getText().length() != 0){
                System.out.println(name2.getText());
                names.add(name2.getText());
            }

            if (name3.getText().length() != 0){
                System.out.println(name3.getText());
                names.add(name3.getText());
            }

            if (name4.getText().length() != 0){
                System.out.println(name4.getText());
                names.add(name4.getText());
            }
            window.close();
        });

        closeButton.setOnAction(action -> {
            boolean answer = ConfirmBox.display("Are You Sure?", "Are You Sure You Would Like To Close The Game?");
            if(answer == true){
                System.exit(0);
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 30, 10, 30));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField passInput = new TextField();


        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(label3, 0, 2);
        GridPane.setConstraints(label4, 0, 3);
        GridPane.setConstraints(name1, 1, 0);
        GridPane.setConstraints(name2, 1, 1);
        GridPane.setConstraints(name3, 1, 2);
        GridPane.setConstraints(name4, 1, 3);
        GridPane.setConstraints(selectButton, 2, 0);
        GridPane.setConstraints(closeButton, 2, 1);


        grid.getChildren().addAll(label1, label2, label3, label4, name1, name2, name3, name4, selectButton, closeButton);


        Scene scene = new Scene(grid, 400, 200);
        window.setScene(scene);
        window.showAndWait();
        return names;

    }
}
