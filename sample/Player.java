package sample;


/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Player
{
    private String name;
    private double money;
    private int location = 0;
    private ArrayList<Property> properties;
    private boolean hasLost = false;
    private int playerNum;
    private int doubles = 0;
    private int GOOJFCard =0;
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
        this.location += spacesMoved;

        if(location >= 40)
        {
            location -= 40;
            money += 200;
        }
        return location;
    }
    
    public void moveTo (int toLoc, boolean giveStartMoney)
    {
        if (location > toLoc && giveStartMoney)
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
