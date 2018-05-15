import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;


import javax.swing.*;


public class Main extends Application
{
    Stage window;
    Scene scene1;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("JavaFX Test Program");
        
        
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        
        Button BuyProperty = new Button("Buy Property");
        BuyProperty.setOnAction(e -> {
            //call goes here
        });
        
        Button TradeProperty = new Button("Trade Property");
        TradeProperty.setOnAction(e -> {
            //call goes here
        });
        
        
        //Select Button
        String playerArray[] = {"Card 1", "Card 2", "Card 3", "Card 4", "Card 5", "Card 6"};
        Button selectButton = new Button("SelectBox");

        selectButton.setOnAction(e -> {
            String result = SelectBox.display("Selection", playerArray, "Select Card To Sell", "Available Properties");
            System.out.println(result);
        });

        Button ConfirmButton = new Button("Confirm Box");
        ConfirmButton.setOnAction(e -> {
            Boolean result = ConfirmBox.display("Yes/No Message", "Choose Yes Or No");
            System.out.println(result);
        });


        Button AlertButton = new Button("Alert Box");
        AlertButton.setOnAction(e -> {
            ConfirmBox.display("Error Message", "You have an error");
        });
        

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        TextField passInput = new TextField();
        passInput.setPromptText("test");

        TextField userInput = new TextField();
        userInput.setMaxWidth(200);
        Label label = new Label();
        label.textProperty().bind(userInput.textProperty());


        GridPane.setConstraints(selectButton, 0, 0);
        GridPane.setConstraints(TradeProperty, 0, 1);
        GridPane.setConstraints(BuyProperty, 0, 2);
        GridPane.setConstraints(ConfirmButton, 0, 3);
        GridPane.setConstraints(AlertButton, 0, 4);
        grid.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(selectButton, BuyProperty, TradeProperty, ConfirmButton, AlertButton);


        Scene scene = new Scene(grid, 900, 600);
        window.setScene(scene);

        primaryStage.show();
    }

    private void closeProgram()
    {
        boolean answer = ConfirmBox.display("", "Are you Sure You Would Like To Exit?");
        if(answer == true)
            window.close();

    }
}

