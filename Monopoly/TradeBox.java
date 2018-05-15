 

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class TradeBox
{

    static String answer;

    public static String display(String title, String[] info1, String[] info2, String message1, String message2, String tip)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        ComboBox<String> player1 = new ComboBox<>();
        player1.setTooltip(new Tooltip(tip));
        player1.setPromptText(message1);

        ComboBox<String> player2 = new ComboBox<>();
        player2.setTooltip(new Tooltip(tip));
        player2.setPromptText(message2);



        for(int x = 0; x < info1.length; x++)
            player1.getItems().add(info1[x]);

        for(int x = 0; x < info2.length; x++)
            player2.getItems().add(info2[x]);



        Button sendButton = new Button("Send");
        Button acceptButton = new Button("Accept Trade");
        Button closeButton = new Button("Close Trade");

        sendButton.setOnAction(e -> {
            answer = player1.getValue();
            window.close();
        });

        acceptButton.setOnAction(e -> {
            window.close();
        });

        closeButton.setOnAction(e -> {
            window.close();
        });

        //comboBox.setOnAction(e -> System.out.println("User Selected: " + comboBox.getValue()));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 30, 10, 30));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField passInput = new TextField();
        passInput.setPromptText(tip);


        //GridPane.setConstraints(comboBox, 0, 0);
        //GridPane.setConstraints(selectButton, 1, 0);
        //GridPane.setConstraints(backButton, 2, 0);

        //grid.getChildren().addAll(comboBox, selectButton, backButton);


        Scene scene = new Scene(grid, 400, 50);
        window.setScene(scene);
        window.showAndWait();
        return answer;

    }
}
