package Monopoly;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.stage.Stage;


public class Controller implements Initializable
{
    public static Space[] board = boardCreation();
    State state = new State();
    public static Player[] players = new Player[0];
    public static Token[] tokens = new Token[0];



    //ObservableList<storeContacts> contacts = FXCollections.observableArrayList();

    @FXML
    private Label moneyAmount;

    @FXML
    private Label playerTurn;

    @FXML
    private Label rollValue;

    @FXML
    private Label nextTurn;

    @FXML
    private AnchorPane BoardPane;

    @FXML
    private ListView list;

    @FXML
    private Label propName;

    @FXML
    private Label propRent;

    @FXML
    private Label propOwner;

    @FXML
    private Label propPrice;

    @FXML
    private javafx.scene.control.Button closeButton;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        ArrayList<String> nameList = NameBox.display();
        while(nameList.size() < 2){
            AlertBox.display("Error!", "Please Add More Than 2 Players!");
            nameList = NameBox.display();
        }
        int playerNum = (nameList.size());
        String[] names = new String[playerNum];

        for (int i = 0; i < playerNum; i++)
        {
            names[i] = nameList.get(i);
        }
        System.out.println(playerNum);
        //String[] name = {"Ian","Bob"};
        for (int i = 0; i < playerNum; i++)
        {
            System.out.println(names[i]);
        }
        createGame(playerNum, names);
    }

    public void createGame(int numPlayers, String[] name)
    {
        System.out.println(numPlayers);
        players = new Player[numPlayers];
        for (int i = 0; i< numPlayers; i++)
        {
            players[i] = new Player (name[i], i);
        }
        state.setNumPlayers(players.length);
        tokens = new Token[numPlayers];
        for (int i = 0; i < numPlayers; i++)
        {
            tokens[i] = new Token (i);
        }

        for (int i = 0; i < numPlayers; i++)
        {
            BoardPane.getChildren().add(tokens[i].getCircle());
        }

    }

    @FXML
    public void rollDice()
    {
        int dice1 = roll();
        int dice2 = roll();
        int totalRoll = dice1+dice2;
        int currentPlayer = state.getNextPlayer();
        state.setCurrentPlayer(state.getNextPlayer());
        players[currentPlayer].move(totalRoll);
        tokens[currentPlayer].move(players, currentPlayer);
        System.out.println("You Rolled: " + dice1 + " " + dice2 + " Current Player: " + currentPlayer + " Position: " + players[currentPlayer].getLocation() + " NumPlayers " + state.getNumPlayers());
        turn(players[state.getCurrentPlayer()], -1);
        if (dice1 == dice2)
            state.setNextPlayer(currentPlayer);
        else{
            state.setNextPlayer((currentPlayer+1)%state.getNumPlayers());
        }

        showProperties(currentPlayer);
        getPlayerStatus(currentPlayer, totalRoll);
        getPropertyStatus(players[currentPlayer].getLocation());

    }

    private int roll()
    {
        int rand = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        return(rand);
    }

    public void showProperties(int c)
    {
        ObservableList<String> nameList =FXCollections.observableArrayList();
        ArrayList<Property> playerProp = new ArrayList<Property>();
        int propOwned = players[c].getProperties().size();
        playerProp = players[c].getProperties();
        if (propOwned > 0){
            nameList.add("Properties:");
            for (int i = 0; i < propOwned; i++){
                Property p = playerProp.get(i);
                String n = p.getName();
                nameList.add(n);
            }
        }
        else{
            nameList.add("You Don't Own Any Properties Yet!");
        }
        list.setItems(nameList);

    }

    public void getPlayerStatus(int p, int r)
    {
        int n = state.getNextPlayer();
        System.out.println(n);
        double m = players[p].getMoney();
        String mString = String.valueOf(m);
        moneyAmount.setText(mString);
        if (p == 0){
            playerTurn.setTextFill(Color.BLUE);
            playerTurn.setText(players[p].getName());
        }
        if (p == 1){
            playerTurn.setTextFill(Color.GREEN);
            playerTurn.setText(players[p].getName());
        }
        if (p == 2){
            playerTurn.setTextFill(Color.RED);
            playerTurn.setText(players[p].getName());
        }
        if (p == 3){
            playerTurn.setTextFill(Color.YELLOW);
            playerTurn.setText(players[p].getName());
        }
        String rString = String.valueOf(r);
        rollValue.setText(rString);
        if (n == 0){
            nextTurn.setTextFill(Color.BLUE);
            nextTurn.setText(players[n].getName());
        }
        if (n == 1){
            nextTurn.setTextFill(Color.GREEN);
            nextTurn.setText(players[n].getName());
        }
        if (n == 2){
            nextTurn.setTextFill(Color.RED);
            nextTurn.setText(players[n].getName());
        }
        if (n == 3){
            nextTurn.setTextFill(Color.YELLOW);
            nextTurn.setText(players[n].getName());
        }
    }

    public void getPropertyStatus(int p)
    {
        if (board[p] instanceof Property)
        {
            String name = ((Property)board[p]).getName();
            double rent = ((Property)board[p]).getRent();
            String rentString = String.valueOf(rent);
            Player player = ((Property)board[p]).getPlayer();
            String stringPlayer;
            if(player != null){
                player.getName();
                stringPlayer = player.getName();
            }
            else{
                stringPlayer = "None";
            }
            double price = ((Property)board[p]).getPrice();
            String priceString = String.valueOf(price);

            propName.setText(name);
            propRent.setText(rentString);
            propPrice.setText(priceString);
            propOwner.setText(stringPlayer);
        }

    }

    public void turn(Player player, int utilityMultiplier)
    {
        int playerLoc = players[state.getCurrentPlayer()].getLocation();
        System.out.println(playerLoc);
        if (utilityMultiplier <0)
            utilityMultiplier = 4;
        if (board[playerLoc] instanceof Property)
        {
            if (((Property)board[playerLoc]).getPlayer()!=null && ((Property)board[playerLoc]).getColour() != 'u')
            {
                pay(player, ((Property)board[playerLoc]).getPlayer(), (((Property)board[playerLoc]).getRent()) * -1);
            }
            else if (((Property)board[playerLoc]).getPlayer()!=null && ((Property)board[playerLoc]).getColour() == 'u')
            {
                pay(player, ((Property)board[playerLoc]).getPlayer(), ((Property)board[playerLoc]).getRent(playerLoc));
            }
            //else
            //{
                //buyProperty(player, ((Property)board[playerLoc]));
            //}
        }

        else
        {
            if (Math.abs(((OtherSpace)board[playerLoc]).getTax()) > 0)
            {
                pay(player, ((OtherSpace)board[playerLoc]).getTax());
            }
            else if (((OtherSpace)board[playerLoc]).getCardValue()>0)
            {
                System.out.println("Got to card - need to fix card class as it is calling Monopoly");
                // Card.CardPickup (player, ((OtherSpace)board[playerLoc]).getCardValue(), players);
            }
        }
        if (hasPlayerLost(player))
            player.hasLost();
    }

    public static boolean pay (Player fromPlayer, double amount)
    {
        if (fromPlayer.transaction(amount))
        {
            return true;
        }
        amount -= fromPlayer.getMoney();
        pay (fromPlayer, fromPlayer.getMoney());
        if (bankrupcy (fromPlayer, amount))
        {
            pay (fromPlayer, amount);
            return true;
        }
        return false;
    }

    public static boolean hasPlayerLost (Player player)
    {
        if (player.getMoney() <=0 && player.getProperties().isEmpty())
            return true;
        else
            return false;
    }

    public static boolean bankrupcy (Player player, double amount)//returns true if debt is paid back, false if it is not
    {
        double valueOfPlayer = player.getValueOfPlayer();
        if (valueOfPlayer >= amount)
        {
            return true;
            //if you sell some things you can pay the debt
        }
        else
            return false;
        //amount is what player owes
        //asks if player wants to sell stuff

    }

    public void buy()
    {
        int playerLoc = players[state.getCurrentPlayer()].getLocation();
        if (board[playerLoc] instanceof Property)
        {
            buyProperty(players[state.getCurrentPlayer()], ((Property)board[playerLoc]));
        }
        if (board[playerLoc] instanceof OtherSpace)
        {

        }
    }

    public void buyProperty (Player player, Property property)
    {
        Boolean result = ConfirmBox.display("Are You Sure?", "Are You Sure You Would Like To Buy This Property");
        if (result == true && pay (player, property.getPrice()))
        {
            property.newOwner(player);
            player.addNewProperty (property);
        }
        else
        {
            return;
            //System.out.println ("Did not buy " + property.getName());
        }
    }

    public static boolean pay (Player fromPlayer, Player toPlayer, double amount)
    {
        if (fromPlayer.transaction(amount))
        {
            toPlayer.transaction(amount);
            return true;
        }
        amount -= fromPlayer.getMoney();
        pay (fromPlayer, toPlayer, fromPlayer.getMoney());
        if (bankrupcy(fromPlayer, amount))
        {
            pay(fromPlayer, toPlayer, amount);
            return true;
        }
        return false;
    }


    public static Space[] boardCreation()
    {
        try {
            File file = new File ("src\\Monopoly\\BoardConfig.txt");
            Scanner scanFile = new Scanner (file);
            String firstLine = scanFile.nextLine();
            Scanner scan = new Scanner (firstLine);
            int numOfSpaces = scan.nextInt();
            System.out.println("numOfSpaces" + numOfSpaces);
            Space[] board = new Space[numOfSpaces];
            for (int i = 0; i <numOfSpaces; i ++)
            {
                String type = scanFile.next();
                if (type.equals ("Property"))
                {
                    String name = scanFile.nextLine();
                    int position = scanFile.nextInt();
                    double cost = scanFile.nextInt();
                    double rent = scanFile.nextInt();

                    String colorString = scanFile.next();
                    char color = colorString.charAt (0);
                    board[i] = new Property(name, position, cost, rent, color);
                }
                else if (type.equals("OtherSpace"))
                {
                    String name = scanFile.nextLine();
                    int location = scanFile.nextInt();
                    int typeOfCard = scanFile.nextInt();
                    double tax = scanFile.nextDouble();

                    board[i] = new OtherSpace(name, typeOfCard, tax, location);
                }
            }
            System.out.println("loaded file");
            return board;
        }
        catch(IOException e)
        {
            System.err.println ("ERROR: " + e);
        }
        return null;
    }

    public static void mortgageProperty(Player currentPlayer, Property property)
    {
        currentPlayer.addMoney(property.getMortgage());
        property.isMortgaged();
    }

    public static void unMortgageProperty(Player currentPlayer, Property property)
    {
        pay(currentPlayer, property.getMortgage());
        property.unMortgage();
    }

    public void tradeInfo()
    {
        ArrayList<Property> player1 = new ArrayList<Property>();
        ArrayList<Property> player2 = new ArrayList<Property>();
        int match = 0;

        Player[] intArray = new Player[state.numPlayers];

        if (players[state.getCurrentPlayer()].getProperties() == null){//if the player has property
            AlertBox.display("Error!", "You Have No Property To Trade!");//alerts the user they have no property
            return;
        }

        for (int i = 0; i< state.numPlayers; i++)
        {
            if(players[i].getProperties() != null){ //not adding players with no cards
                intArray[i] = players[i];
                System.out.println(players[i].getName());
                match = 1;
            }

            if (match == 0){ //if no players have property
                AlertBox.display("Error!", "There Is No One To Trade With!");//calling an alert box warning the user
                return;
            }
        }

        String currentPlayerName = players[state.getCurrentPlayer()].getName();
        System.out.println("current Player " + currentPlayerName);
        int returnPlayerNum = NameSelectBox.display("Name Select", intArray,"Please Select a Player To Trade With", "", currentPlayerName);

        getTrade(players[state.getCurrentPlayer()].getProperties(), players[returnPlayerNum].getProperties(), players[state.getCurrentPlayer()].getName(), players[returnPlayerNum].getName(), players[state.getCurrentPlayer()], players[state.getCurrentPlayer()]);
    }

    public void getTrade( ArrayList<Property> player1,  ArrayList<Property> player2, String namePlayer1, String namePlayer2, Player playerObj1, Player playerObj2)
    {
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(namePlayer1);
        System.out.println(namePlayer2);
        System.out.println(playerObj1);
        System.out.println(playerObj2);

        ArrayList<Property> return1 = new ArrayList<Property>();
        ArrayList<Property> return2 = new ArrayList<Property>();

        ArrayList<Property>[] tradeReturnValue =  TradeBox.display("Trade Menu", player1, player2, namePlayer1, namePlayer2, "");

        for (int x = 0; x < tradeReturnValue[0].size(); x++)
            player1.add(tradeReturnValue[0].get(x));

        for (int x = 0; x < tradeReturnValue[0].size(); x++)
            player1.add(tradeReturnValue[0].get(x));

        trade(playerObj1, playerObj2, return1, return2);
    }

    /**
     * This method creates the option for players to trade properties and money between eachother. 
     */
    public static void trade(Player fromPlayer, Player toPlayer, ArrayList<Property> fromPlayerProperties, ArrayList<Property> toPlayerProperties)
    {
        for(int i = 0; i < fromPlayerProperties.size(); i++)
        {
            fromPlayerProperties.get(i).newOwner(toPlayer);
            toPlayerProperties.add(fromPlayerProperties.get(i));
            fromPlayerProperties.remove(i);
        }
        for(int i = 0; i < toPlayerProperties.size(); i++)
        {
            toPlayerProperties.get(i).newOwner(toPlayer);
            fromPlayerProperties.add(toPlayerProperties.get(i));
            toPlayerProperties.remove(i);
        }
    }

    public void closeProgram()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        boolean answer = ConfirmBox.display("", "Are You Sure You Would Like To Quit?");
        if(answer == true)
            stage.close();

    }

    public static void addHouse(Player currentPlayer, Property property)
    {
        char propertyColour = property.getColour();
        ArrayList<Property> currentPlayerProps = currentPlayer.getProperties();
        Property checkProperty;
        int numOfColour = 0;
        int ownedNumOfColour = 0;
        for(int i = 0; i < board.length; i++)
        {
            if(board[i] instanceof Property)
            {
                checkProperty = (Property) board[i];
                if(checkProperty.getColour() == propertyColour)
                {
                    numOfColour++;
                }
            }
        }

        for(int i = 0; i < currentPlayerProps.size(); i++)
        {
            if(currentPlayerProps.get(i).getColour() == propertyColour)
            {
                ownedNumOfColour++;
            }
        }

        if(ownedNumOfColour == numOfColour)
        {
            if(property.getLocation() > 1 && property.getLocation() < 10)
            {
                if(currentPlayer.getMoney() >= 50)
                {
                    /*
                     * Do what i said i was going to do!
                     */
                }
            }
        }
    }
}