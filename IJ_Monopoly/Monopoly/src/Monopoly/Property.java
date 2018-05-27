package Monopoly;


/**
 * Write a description of class Property here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Property extends Space
{
    private Player player;
    private double price;
    private double rent;
    private char colour;
    private boolean isPropMortgaged = false;
    private int numberOfHouses = 0;
    private String name;
    public Property(String name, int loc, double cost, double rent, char colour)
    {
        this.name = name;
        this.location = loc;
        this.colour = colour;
        this.price = cost;
        this.rent = rent;
    }
    
    public int getNumberOfHouses()
    {
        return numberOfHouses;
    }
    
    public int getLocation()
    {
        return location;
    }
    
    public String getName()
    {
        return name;
    }
    
    public char getColour ()
    {
        return colour;
    }
    
    public void newOwner(Player player)
    {
        this.player = player;
    }
   
    public Player getPlayer()
    {
        return player;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public double getRent()
    {
        return rent;
    }
    
    public void addRent(double amount)
    {
        if(numberOfHouses == 1)
        {
            rent *= 5;
        }
        else if(numberOfHouses > 1 && numberOfHouses > 3)
        {
            rent *= 3;
        }
        else if(numberOfHouses == 4)
        {
            rent *= 1.5;
        }
    }
    
    public double getRent(int roll)
    {
        return roll * 4;
    }

    public double getMortgage()
    {
        return price / 2;
    }
    
    public boolean getIsMortgage()
    {
        return isPropMortgaged;
    }
    
    public void isMortgaged()
    {
        isPropMortgaged = true;
    }
    
    public void unMortgage()
    {
        isPropMortgaged = false;
    }
}
