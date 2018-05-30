 


/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Player
{
    private String name;//name of the player
    private double money;// the amount of ca$h the player has
    private int location = 0;//location on the board
    private ArrayList<Property> properties = new ArrayList <Property>();//properties the player has
    private boolean hasLost = false;// determines if the player has lost (No money & no property)
    private int playerNum;//Player num (Same as location in the players array in the main class
    private int doubles = 0;// num of doubles in a row (if reaches 3/4 sends to jail)
    private int GOOJFCard =0;// num of GetOutOfJailFree (GOOJF) cards
    private int turnsInJail = 0;//num of turns in jail (if reaches a certain num, released)
    private boolean inJail = false;//if player is in jail
    public boolean getInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {// sets if the player is in jail or not
        this.inJail = inJail;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void setTurnsInJail(int turnsInJail) {//increase by one or 
        this.turnsInJail = turnsInJail;
    }

    public Player(String name, double money, int playerNum)
    {
        this.name = name;
        this.money = money;
        this.playerNum = playerNum;
    }
    
    public void addNewProperty (Property newProperty)
    {   
        properties.add(newProperty);
    }

    public void removeProperty (Property removeProperty)
    {
        properties.remove(removeProperty);
    }
    
    public Player(String name, int playerNum)
    {
        this.name = name;
        money = 1500;
        this.playerNum = playerNum;
    }

    public int getDoubles()
    {
        return doubles;
    }

    public void newDoubles(int newDoubles)
    {
        doubles = newDoubles;
    }

    public int getPlayerNum ()
    {
        return playerNum;
    }

    public void hasLost()
    {
        hasLost = true;
    }

    public boolean getHasLost()
    {
        return hasLost;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Property> getProperties ()
    {
        return properties;
    }

    public double getMoney()
    {
        return money;
    }

    public int getLocation()
    {
        return location;
    }

    public int move(int spacesMoved)
    {
        if(this.inJail == true){
            if (this.turnsInJail < 3){
                this.turnsInJail++;
            }
            else{
                this.location += spacesMoved;
                this.setInJail(false);
            }
        }
        else{
            this.location += spacesMoved;
        }

        if(location >= 40)
        {
            location -= 40;
            money += 200;
        }

        if(location == 30){
            location = 10;
            this.inJail = true;
            AlertBox.display("You Have Been A Bad/Girl/Other", "GO TOO JAIL!");
        }

        return location;
    }
    
    public void moveTo (int toLoc, boolean giveStartMoney)
    {
        if (location >= toLoc && giveStartMoney)
            money+= 200;
            location = toLoc;
    }
    
    public double getValueOfPlayer()
    {
        double valueOfProperties = 0;
        for (int i = 0; i < properties.size();i++)
        {
            valueOfProperties += properties.get(i).getMortgage();
        }
        return valueOfProperties + money;
    }
    
    public void addMoney (double amount)
    {
      money += amount;   
    }
    
    public boolean transaction(double amount)
    {
        if (money >=amount)
        {
            money -= amount;
            return true;
        }
        else 
            return false;
    }
    
    public void  newGOOJFCard()
    {
        GOOJFCard++;
    }
}
