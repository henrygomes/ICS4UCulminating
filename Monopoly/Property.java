
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
    public Property(String name, int loc, double cost, double rent, char colour)
    {
        this.name = name;
        this.location = loc;
        this.colour = colour;
        this.price = cost;
        this.rent = rent;
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
    
    //public double getRent(int roll)

    public double getMortgage()
    {
        return price/2;
    }
}
