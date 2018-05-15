
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private double money;
    private int location = 0;
    private Property[] properties;
    private boolean hasLost = false;
    private int playerNum;
    public Player(String name, double money, int playerNum)
    {
        this.name = name;
        this.money = money;
        this.playerNum = playerNum;
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
    public Property[] getProperties ()
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
        }
        return location;
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
}
