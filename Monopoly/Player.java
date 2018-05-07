
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
    
    public Player(String name, double money)
    {
        this.name = name;
        
        this.money = money;
    }
    
    public String getName()
    {
        return name;
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
}
