import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;

import static java.awt.SystemColor.text;

public class Controller implements Initializable
{
    public static Space[] board = boardCreation();
    State state = new State();
    public static Player[] players = new Player[0];
    //ObservableList<storeContacts> contacts = FXCollections.observableArrayList();
    File f = new File("contacts.txt");


    @FXML
    public Label moneyAmount;

    @FXML
    public Label playerTurn;

    @FXML
    public Label rollValue;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        String[] name = {"Ian", "Henry"};
        createGame(2, name);
    }

    public void createGame(int numPlayers, String[] name)
    {
        players = new Player[numPlayers];
        for (int i = 0; i< numPlayers; i++)
        {
            players[i] = new Player (name[i], i);
        }
        state.setNumPlayers(players.length);
        rollDice();
        StringProperty stringName;
    }

    public void rollDice()
    {
        int dice1 = roll();
        int dice2 = roll();
        int totalRoll = dice1+dice2;
        int currentPlayer = state.getCurrentPlayer();
        System.out.println(state.getNumPlayers());
        players[currentPlayer].move(totalRoll);
        System.out.println("You Rolled: " + totalRoll + " Current Player: " + currentPlayer + " Position: " + players[currentPlayer].getLocation());
        if (dice1 != dice2)
            currentPlayer++;
        if (currentPlayer >= state.getNumPlayers())
            currentPlayer = 0;
        
        state.setCurrentPlayer(currentPlayer);
        getPlayerStatus(currentPlayer-1);
        //getPropertyStatus(players[currentPlayer].getLocation());
        turn(players[state.getCurrentPlayer()], -1);
    }

    private int roll()
    {
        int rand = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        return(rand);
    }    
    
    public void getPlayerStatus(int p)
    {
        double playerMoney = players[p].getMoney();
        String playerName = players[p].getName();
        System.out.println("Player Money: " + playerMoney + " Player Name: " + playerName);
    }

    public void getPropertyStatus(int s)
    {
        if (board[players[state.getCurrentPlayer()].getLocation()] instanceof Property);
            System.out.print("");
    }

    public void turn(Player player, int utilityMultiplier)
    { 
        int playerLoc = players[state.getCurrentPlayer()].getLocation();
        System.out.println("playerLoc: " + playerLoc);
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
            else
            {
                buyProperty(player, ((Property)board[playerLoc]));
            }
        }

        else
        {
            if (Math.abs(((OtherSpace)board[playerLoc]).getTax()) > 0)
            {
                pay(player, ((OtherSpace)board[playerLoc]).getTax());
            }
            else if (((OtherSpace)board[playerLoc]).getCardValue()>0)
            {
                Card.CardPickup (player, ((OtherSpace)board[playerLoc]).getCardValue(), players);
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

    public static void buyProperty (Player player, Property property)
    {
        Boolean result = ConfirmBox.display("Are You Sure?", "Are You Sure You Woul Like To Buy This Property");
        if (result == true && pay (player, property.getPrice()))
        {
            property.newOwner(player);
            player.addNewProperty (property);
        }
        else
        {
            System.out.println ("Did not buy " + property.getName());
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
            File file = new File ("BoardConfig.txt");
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

        //trade(playerObj1, playerObj2, return1, return2);
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