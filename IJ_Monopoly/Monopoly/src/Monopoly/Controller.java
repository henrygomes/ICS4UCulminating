
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.shape.*;

public class Controller implements Initializable
{
    public static Space[] board = boardCreation();//the board is just a space array with properties and is created int eh boardCreation() method
    public static State state = new State();//holds some game variales
    public static Player[] players = new Player[0];//the current players in the game
    public static Token[] tokens = new Token[0];

    @FXML private Label moneyAmount;
    @FXML private Label playerTurn;
    @FXML private Label rollValue;
    @FXML private Label nextTurn;
    @FXML private AnchorPane BoardPane;
    @FXML private ListView list;
    @FXML private Label propName;
    @FXML private Label propRent;
    @FXML private Label propOwner;
    @FXML private Label propPrice;
    @FXML private HBox a;
    @FXML private HBox b;
    @FXML private HBox c;
    @FXML private HBox d;
    @FXML private HBox e;
    @FXML private VBox f;
    @FXML private VBox g;
    @FXML private VBox h;
    @FXML private VBox i;
    @FXML private VBox j;
    @FXML private VBox k;
    @FXML private HBox l;
    @FXML private HBox m;
    @FXML private HBox n;
    @FXML private HBox o;
    @FXML private HBox p;
    @FXML private HBox q;
    @FXML private VBox r;
    @FXML private VBox s;
    @FXML private VBox t;
    @FXML private VBox u;
    @FXML private VBox v;
    @FXML private javafx.scene.control.Button closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)//creates the cariables for the game
    {

        ArrayList<String> nameList = NameBox.display();//displayes the name box
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
        for (int i = 0; i< numPlayers; i++)//creates players array
        {
            players[i] = new Player (name[i], i);
        }
        state.setNumPlayers(players.length);//sets state
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
    public void rollDice()//starts the game
    {
        int dice1 = roll();
        int dice2 = roll();//to roll to allow for doubles and proper odds
        state.setD1(dice1);
        state.setD2(dice2);
        int totalRoll = dice1+dice2;
        //totalRoll = 1;//for tseting
        int currentPlayer = state.getNextPlayer();
        state.setCurrentPlayer(state.getNextPlayer());//switches players
        players[currentPlayer].move(totalRoll);//moving the player location object
        tokens[currentPlayer].move(players, currentPlayer);//moving rthe player on the board
        System.out.println("You Rolled: " + dice1 + " " + dice2 + " Current Player: " + currentPlayer + " Position: " + players[currentPlayer].getLocation() + " NumPlayers " + state.getNumPlayers());
        if (dice1 == dice2)//if doubles player goes again
            state.setNextPlayer(currentPlayer);
        else{//if no doubles next player is selected
            state.setNextPlayer((currentPlayer+1)%state.getNumPlayers());
        }
        if (players[currentPlayer].getLocation() != 40){//executes the turn
            turn(players[state.getCurrentPlayer()], -1);
            showProperties(currentPlayer);
            getPlayerStatus(currentPlayer, totalRoll);
            getPropertyStatus(players[currentPlayer].getLocation());
        }

    }

    private static int roll()//returns a valid random dice roll
    {
        int rand = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        return(rand);
    }

    public void showProperties(int c)
    {
        int playerLoc = players[state.getCurrentPlayer()].getLocation();
        ObservableList<String> nameList =FXCollections.observableArrayList();
        ArrayList<Property> playerProp = new ArrayList<Property>();
        int propOwned = players[c].getProperties().size();
        playerProp = players[c].getProperties();
        if (propOwned > 0){
            nameList.add("Properties:");
            for (int i = 0; i < propOwned; i++){
                Property p = playerProp.get(i);
                if (((Property)board[p.getLocation()]).getIsMortgage() == true){ //Checks to see if property is mortgaged
                    Color color = ((Property)board[p.getLocation()]).getColor();
                    String n = p.getName();
                    nameList.add(n + " (Mortgaged)");
                }
                else{
                    String n = p.getName();
                    nameList.add(n);
                }
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
        if (board[p] instanceof OtherSpace)
        {
            String name = ((OtherSpace)board[p]).getName();
            propName.setText(name);
            propRent.setText("N/A");
            propPrice.setText("N/A");
            propOwner.setText("N/A");
        }
    }

    public static void turn(Player player, int utilityMultiplier)//executes the game
    {
        int playerLoc = players[state.getCurrentPlayer()].getLocation();
        System.out.println(playerLoc);
        if (utilityMultiplier <0)
            utilityMultiplier = 1;
        if (board[playerLoc] instanceof Property)//if lands on property
        {
            if (((Property)board[playerLoc]).getPlayer()!=null && ((Property)board[playerLoc]).getColour() != 'u' && ((Property)board[playerLoc]).getIsMortgage() == false)//if the player has to pay rent
            {
                pay(player, ((Property)board[playerLoc]).getPlayer(), (((Property)board[playerLoc]).getRent()));
            }
            else if (((Property)board[playerLoc]).getPlayer()!=null && ((Property)board[playerLoc]).getColour() == 'u' && ((Property)board[playerLoc]).getIsMortgage() == false)//if the player has to pay rent on a utility
            {
                pay(player, ((Property)board[playerLoc]).getPlayer(), ((Property)board[playerLoc]).getRent(roll()) * utilityMultiplier);
            }
        }
        else//if land on otherspace
        {
            if (Math.abs(((OtherSpace)board[playerLoc]).getTax()) > 0)//if there is a tax, pay it
            {
                pay(player, ((OtherSpace)board[playerLoc]).getTax());
            }
            else if (((OtherSpace)board[playerLoc]).getCardValue()>0)//pickup card if is cardpickup
            {
                System.out.println("Got to card - need to fix card class as it is calling Monopoly");
                CardPickup(player, players);
            }
        }
        if (hasPlayerLost(player))
            player.hasLost();
    }

    public static boolean pay (Player fromPlayer, double amount)//have the player pay the amount
    {
        if (fromPlayer.transaction(amount))
        {
            return true;
        }
        if (fromPlayer.getValueOfPlayer() < amount )
        {
            return false;
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

    public static boolean hasPlayerLost (Player player)//checks if the player has lost all money and property
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
        int totalRoll = (state.getD1() + state.getD2());
        if (players[state.getCurrentPlayer()].getInJail() == true){
            AlertBox.display("Error!", "You Cant Buy While In Jail!");
            return;
        }
        int playerLoc = players[state.getCurrentPlayer()].getLocation();
        if (board[playerLoc] instanceof Property)
        {
            if (((Property)board[playerLoc]).getOwner() != null){
                AlertBox.display("Error!", "This Property Is Already Owned");
                return;
            }
            buyProperty(players[state.getCurrentPlayer()], ((Property)board[playerLoc]));
            showProperties(state.getCurrentPlayer());
            getPlayerStatus(state.getCurrentPlayer(), totalRoll);
            getPropertyStatus(players[state.getCurrentPlayer()].getLocation());
        }
        if (board[playerLoc] instanceof OtherSpace)
        {
            AlertBox.display("Error!", "You Cant Buy This Property!");
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
        }
    }

    public static boolean pay (Player fromPlayer, Player toPlayer, double amount)//transaction between players
    {
        if (fromPlayer.transaction(amount))
        {
            toPlayer.transaction(amount);
            return true;
        }
        if (fromPlayer.getValueOfPlayer() < amount )
        {
            return false;
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

    public static Space[] boardCreation()//initialize board
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
            System.out.println("loaded file");
            return board;
        }
        catch(IOException e)
        {
            System.err.println ("ERROR: " + e);
        }
        return null;
    }

    public void mortgageInfo()
    {
        if (players[state.getCurrentPlayer()].getInJail() == true){//makes sure the player is not in jail
            AlertBox.display("Error!", "You Cant Buy While In Jail!");//alerts the user they cant trade in jail
            return;
        }

        if(players[state.getCurrentPlayer()].getProperties().size() == 0){//makes sure the player owns property
            AlertBox.display("Error!", "You Don't Own Any Property!");//alerts the user they cant have no property to mortgage
            return;
        }

        boolean sucess = false;// used keep track if the mortgage is successful
        ArrayList<Property> mortgagedProperties = new ArrayList<Property>();//creating array lists for the properties that will fill the selection boxes
        ArrayList<Property> unMortgagedProperties = new ArrayList<>();

        for(int i = 0; i < players[state.getCurrentPlayer()].getProperties().size(); i++){
            if(players[state.getCurrentPlayer()].getProperties().get(i).getIsMortgage() == true){
                mortgagedProperties.add(players[state.getCurrentPlayer()].getProperties().get(i));
            }
            else{
                unMortgagedProperties.add(players[state.getCurrentPlayer()].getProperties().get(i));
            }
        }

        boolean choice = MortgageBox.display("Mortgage", "What Would You Like To Do?");//asks the player if they want to mortgage or un mortgage

        if (choice == true && unMortgagedProperties.size() > 0){
            Property property = SelectBoxArrayList.display("Mortgage", unMortgagedProperties, "Available Properties", "Properties");
            if (property == null){//exits code if they cancel or close the window
                return;
            }
            mortgageProperty(players[state.getCurrentPlayer()], property);
            sucess = true;
        }
        else if (mortgagedProperties.size() > 0){
            Property property = SelectBoxArrayList.display("Un Mortgage", mortgagedProperties, "Available Properties", "Properties");
            if (property == null){//exits code if they cancel or close the window
                return;
            }
            unMortgageProperty(players[state.getCurrentPlayer()], property);
            sucess = true;
        }
        if (choice == true && unMortgagedProperties.size() > 0 && sucess == false){// used to decide what alert to give to the user
            AlertBox.display("Error", "No Cards Available To Mortgage");//calls the alertBox with the error
        }
        if (choice == true && mortgagedProperties.size() > 0 && sucess == false){
            AlertBox.display("Error", "No Cards Available To Un Mortgage");//calls the alert box with the error
        }

    }

    public void mortgageProperty(Player currentPlayer, Property property)
    {
        currentPlayer.addMoney(property.getMortgage());
        property.isMortgaged();
        showProperties(state.getCurrentPlayer());
        getPlayerStatus(state.getCurrentPlayer(), (state.getD1() + state.getD2()));
        getPropertyStatus(players[state.getCurrentPlayer()].getLocation());
    }

    public void unMortgageProperty(Player currentPlayer, Property property)
    {
        pay(currentPlayer, property.getMortgage());
        property.unMortgage();
        showProperties(state.getCurrentPlayer());
        getPlayerStatus(state.getCurrentPlayer(), (state.getD1() + state.getD2()));
        getPropertyStatus(players[state.getCurrentPlayer()].getLocation());
    }

    public void tradeInfo()
    {
        if (players[state.getCurrentPlayer()].getInJail() == true){
            AlertBox.display("Error!", "You Cant Trade In Jail!");
            return;
        }
        ArrayList<Property> player1 = new ArrayList<Property>();
        ArrayList<Property> player2 = new ArrayList<Property>();
        ArrayList<Player> intArray = new ArrayList<Player>();
        int match = 0;

        if (players[state.getCurrentPlayer()].getProperties().size() == 0){//if the player has property
            AlertBox.display("Error!", "You Have No Property To Trade!");//alerts the user they have no property
            return;
        }

        for (int i = 0; i< state.numPlayers; i++)
        {
            System.out.println(state.currentPlayer);

            if(players[i].getProperties().size() != 0 && i != state.currentPlayer){ //not adding players with no cards
                intArray.add(players[i]);
                match = 1;
            }
        }

        if (match == 0){ //if no players have property
            AlertBox.display("Error!", "There Is No One To Trade With!");//calling an alert box warning the user
            return;
        }

        String currentPlayerName = players[state.getCurrentPlayer()].getName();
        System.out.println("current Player " + currentPlayerName);
        int returnPlayerNum = NameSelectBox.display("Name Select", intArray ,"Please Select a Player To Trade With", "", currentPlayerName);
        if (returnPlayerNum >= state.getCurrentPlayer()){
            System.out.println("added 1");
            returnPlayerNum = (returnPlayerNum + 1);
        }
        System.out.println("playerNum: " + returnPlayerNum);

        getTrade(players[state.getCurrentPlayer()].getProperties(), players[returnPlayerNum].getProperties(), players[state.getCurrentPlayer()].getName(), players[returnPlayerNum].getName(), players[state.getCurrentPlayer()], players[returnPlayerNum]);
    }

    public void getTrade( ArrayList<Property> player1,  ArrayList<Property> player2, String namePlayer1, String namePlayer2, Player playerObj1, Player playerObj2)
    {

        ArrayList<Property> return1 = new ArrayList<Property>();
        ArrayList<Property> return2 = new ArrayList<Property>();

        ArrayList<Property>[] tradeReturnValue =  TradeBox.display("Trade Menu", player1, player2, namePlayer1, namePlayer2, "");
        System.out.println(tradeReturnValue);
        if (tradeReturnValue == null){
            return;
        }
        for (int x = 0; x < tradeReturnValue[0].size(); x++)
            return1.add(tradeReturnValue[0].get(x));

        for (int x = 0; x < tradeReturnValue[1].size(); x++)
            return2.add(tradeReturnValue[1].get(x));

        for (int i = 0; i < return1.size(); i++)
        {
            System.out.println("Player 1 Trade Items: " + return1.get(i).getName());
        }

        for (int i = 0; i < return2.size(); i++)
        {
            System.out.println("Player 2 Trade Items: " + return2.get(i).getName());
        }

        trade(playerObj1, playerObj2, return1, return2);
    }

    /**
     * This method creates the option for players to trade properties and money between eachother. 
     */
    public void trade(Player player1, Player player2, ArrayList<Property> player1Properties, ArrayList<Property> player2Properties)
    {
        for(int i = 0; i < player1Properties.size(); i++)
        {
            player1Properties.get(i).newOwner(player2);
            player2.addNewProperty(player1Properties.get(i));
            player1.removeProperty(player1Properties.get(i));
        }
        for(int i = 0; i < player2Properties.size(); i++)
        {
            player2Properties.get(i).newOwner(player1);
            player1.addNewProperty(player2Properties.get(i));
            player2.removeProperty(player2Properties.get(i));
        }
        getPropertyStatus(players[state.getCurrentPlayer()].getLocation());
        showProperties(state.getCurrentPlayer());
    }

    public void closeProgram()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        boolean answer = ConfirmBox.display("", "Are You Sure You Would Like To Quit?");
        if(answer == true)
            stage.close();

    }

    public void house()
    {
        ArrayList<Property> properties = new ArrayList<Property>();
        for(int i = 0; i < players[state.getCurrentPlayer()].getProperties().size(); i++) {
            if (canAddHouse(players[state.getCurrentPlayer()], players[state.getCurrentPlayer()].getProperties().get(i))) {
                properties.add(players[state.getCurrentPlayer()].getProperties().get(i));
            }

            if(properties.size() == 0){
                AlertBox.display("Error!", "No Properties To Build Houses On");
                return;
            }
        }
        Property selectedProperty = SelectBoxArrayList.display("Add House", properties, "Select House", "Owned Houses");

        addHouse(players[state.getCurrentPlayer()], selectedProperty);
    }

    public boolean canAddHouse(Player currentPlayer, Property property)
    {
        char propertyColour = property.getColour();
        Property checkProperty;
        int numOfColour = 0;
        int ownedNumOfColour = 0;
        for(int i = 0; i < board.length; i++)
        {
            if(board[i] instanceof Property)
            {
                checkProperty = ((Property)board[i]);
                if(checkProperty.getColour() == propertyColour && checkProperty.getOwner() == currentPlayer && !checkProperty.getIsMortgage())
                {
                    numOfColour++;
                }
            }
        }
        if(numOfColour == property.getNumColorProperty()){
            return true;
        }
        else{
            return false;
        }
    }

    public void addHouse(Player currentPlayer, Property property)
    {
        ArrayList<Property> currentProperties = new ArrayList<Property>();
        int rent = 0;
        int amountHouses = HouseBox.display(property.getNumColorProperty(), (property.getNumberOfHouses()*property.getNumColorProperty()), property.getNumberOfHouses());
        if (amountHouses == 0){
            return;
        }
        char color = property.getColour();
        for(int i = 0; i < board.length; i++)
        {
            if(board[i] instanceof Property)
            {
                if(color == ((Property)board[i]).getColour()){
                    currentProperties.add(((Property)board[i]));
                }
            }
        }
        if(property.getColour() == 'b' || property.getColour() == 'l'){
            rent = 50;
        }
        if(property.getColour() == 'p' || property.getColour() == 'o'){
            rent = 100;
        }
        if(property.getColour() == 'r' || property.getColour() == 'y'){
            rent = 150;
        }
        if(property.getColour() == 'g' || property.getColour() == 'd'){
            rent = 100;
        }

        for(int i = 0; i < currentProperties.size(); i++){
            currentProperties.get(i).setRent(rent * (amountHouses/property.getNumColorProperty() + property.getNumberOfHouses()));
            currentProperties.get(i).setNumberOfHouses((amountHouses/property.getNumColorProperty() + property.getNumberOfHouses()));
        }
        getPlayerStatus(state.getCurrentPlayer(), (state.getD1() + state.getD2()));
        getPropertyStatus(players[state.getCurrentPlayer()].getLocation());
    }

    public void displayHouses()
    {
        HBox hMapping[] = {a, a, b,b,b,c,d,e,e,e,e,e,e,e,e,e,e,e,e,l,l,m,n,n,o,p,p,q};
        VBox vMapping[] = {};
        Rectangle[] rectangles = new Rectangle[100];
        for (int i = 0; i < 100; i++)
        {
            rectangles[i] = new Rectangle(5, 5, Color.GREEN);
        }
        for(int i = 0; i < board.length; i++)
        {
            if(board[i] instanceof Property)
            {
                if(((Property)board[i]).getNumberOfHouses() > 0){
                    if(((Property)board[i]).getLocation() < 10 || (((Property)board[i]).getLocation() > 20 && ((Property)board[i]).getLocation() < 30)) {
                        for (int x = 0; x > ((Property) board[i]).getNumberOfHouses(); x++) {
                            // mapping[i]
                        }
                    }
                }
            }
        }
    }

    public static void CardPickup (Player player, Player[] players)
    {
        Random rand = new Random();
        int card =rand.nextInt(15);
        
        switch (card){
            case 0://advance to go, if pass go collect 200
            player.moveTo(0, true);
            break;
            case 1://advance to Illinois Ave (24), if pass go collect 200
            player.moveTo(24, true);
            turn ( player, -1);
            break;
            case 2://advance to St. Charles Place (11), if pass go collect 200
            player.moveTo(11, true);
            turn( player, -1);
            break;
            case 3://advance to nearest utility, if owned pay 10x roll, if unowned buy
            if (player.getLocation() > 28 || player.getLocation() < 12){
                player.moveTo(12, true);
                turn( player, 10);
            }
            else{
                player.moveTo(28, true);
                turn( player, 10);
            }
            break;
            case 4://advance to nearest railroad, pay 2x rental, if unowned buy
            if (player.getLocation() < 5 || player.getLocation() >= 35)
                player.moveTo (5, true);
            else if( player.getLocation() < 15)
                player.moveTo(15, true);
            else if (player.getLocation() < 25)
                player.moveTo(25, true);
            else if (player.getLocation() < 35)
                player.moveTo(35, true);
            turn (player, -1);
            break;
            case 5://get 50
            player.addMoney (50);
            break;
            case 6://get out of jail free card
            player.newGOOJFCard();
            break;
            case 7://go back 3 spaces
            int playerLoc = player.getLocation();
            int playerTo = player.getLocation() -3;
            if (playerTo <0)
                playerTo = 40 + playerTo;
            player.moveTo(playerTo, false);
            turn ( player, -1);
            break;
            case 8://go to jail, dont get 200$
            player.moveTo(30, false);
            break;
            case 9://pay 15$
            pay (player, 15);
            break;
            case 10://got to reading railroad, if pass go pay 200$
            player.moveTo(5, true);
            turn( player, -1);
            break;
            case 11:// go to boardwalk
            player.moveTo(39, true);
            turn( player, -1);
            break;
            case 12://pay each player 50
            for (int i = 0; i <players.length; i++)
            {
                if (players[i] != player)
                    MonopolyGame.pay (player, players[i],50);

            }
            break;
            case 13://collect 150
            player.addMoney(150);
            break;
            case 14://collect 100
            player.addMoney(100);
            break;

        }
        return;
    }
}