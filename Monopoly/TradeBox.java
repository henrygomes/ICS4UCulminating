import java.util.*;
//import javafx.beans.property.Property;
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.util.ArrayList;


public class TradeBox
{

    static String answer;

    public static ArrayList<Property>[] display(String title, ArrayList<Property> info1, ArrayList<Property> info2, String message1, String message2, String tip)
    {
        System.out.println("in Trade");
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        ArrayList<Property> trade1 = new ArrayList<Property>();
        ArrayList<Property> trade2 = new ArrayList<Property>();
        ArrayList<Property>[] tradeReturn = new ArrayList[2];

        ComboBox<String> player1 = new ComboBox<>();
        player1.setPromptText(message1);
        player1.setTooltip(new Tooltip(tip));

        ComboBox<String> player2 = new ComboBox<>();
        player2.setPromptText(message2);
        player2.setTooltip(new Tooltip(tip));

        int info1size = info1.size();
        int info2size = info2.size();


        for(int x = 0; x < info1size; x++)
            player1.getItems().add(info1.get(x).getName());

        for(int x = 0; x < info2size; x++)
            player2.getItems().add(info2.get(x).getName());


        Button tradeButtonPlayer1 = new Button("Add To Trade");
        Button tradeButtonPlayer2 = new Button("Add To Trade");
        Button tradeButton = new Button("Finalize Trade");
        Button closeButton = new Button("Close Trade");

        tradeButtonPlayer1.setOnAction(e -> {
            int match1 = 0;
            int numProperty1 = 0;
            for (int x = 0; x < info1size; x++) {
                if (player1.getValue() == info1.get(x).getName())
                    numProperty1 = x;
            }
            for (int j = 0; j < trade1.size(); j++) {
                if (trade1.get(j) == info1.get(numProperty1))
                    match1 = 1;
            }
                if (match1 == 0) {
                    System.out.println("in match");
                    trade1.add(info1.get(numProperty1));
                }
        });

        tradeButtonPlayer2.setOnAction(e -> {
            int match2 = 0;
            int numProperty2 = 0;
            for (int x = 0; x < info2size; x++) {
                if (player2.getValue() == info2.get(x).getName())
                    numProperty2 = x;
            }
            for (int j = 0; j < trade2.size(); j++) {
                if (trade2.get(j) == info2.get(numProperty2))
                    match2 = 1;
            }
            if (match2 == 0) {
                System.out.println("in match");
                trade2.add(info2.get(numProperty2));
            }
        });

        /*
        tradeButtonPlayer2.setOnAction(e -> {
            trade2.add(player2.getValue());
        });
        */

        tradeButton.setOnAction(e -> {
            tradeReturn[0] = trade1;
            tradeReturn[1] = trade2;
            window.close();
        });

        closeButton.setOnAction(e -> {
            window.close();
        });


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 30, 10, 30));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField passInput = new TextField();
        passInput.setPromptText(tip);


        GridPane.setConstraints(player1, 0, 0);
        GridPane.setConstraints(tradeButtonPlayer1, 1, 0);
        GridPane.setConstraints(player2, 2, 0);
        GridPane.setConstraints(tradeButtonPlayer2, 3, 0);
        GridPane.setConstraints(tradeButton, 1, 2);
        GridPane.setConstraints(closeButton, 2, 2);

        grid.getChildren().addAll(player1, tradeButtonPlayer1, player2, tradeButtonPlayer2, tradeButton, closeButton);


        Scene scene = new Scene(grid, 700, 100);
        window.setScene(scene);
        window.showAndWait();
        return tradeReturn;

    }
}

