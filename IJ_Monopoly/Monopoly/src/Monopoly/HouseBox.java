 

import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class HouseBox
{

    static int answer = 0;

    public static int display(int totalNumProperties, int numHouses, int currentNumHouses) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("How Many Houses:");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("How Many Total Houses Would You Like To Add To Your Selected Properties?");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Houses");

        if (numHouses != 4*totalNumProperties) {
            numHouses = currentNumHouses;
        }
        else {
            AlertBox.display("Error!", "Maximum Number Of Houses Added!");
            window.close();
        }

        System.out.println("numHouses: " + numHouses);
            while (numHouses < 4*totalNumProperties) {
                if(numHouses%totalNumProperties == 0){
                    String numHousesString = Integer.toString(4*totalNumProperties-numHouses);
                    comboBox.getItems().add(numHousesString);
                }
                numHouses++;
            }

        Button selectButton = new Button("Select");
        Button backButton = new Button("Back");

        selectButton.setOnAction(e -> {
            String myString = comboBox.getValue();
            answer = Integer.parseInt(myString);
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