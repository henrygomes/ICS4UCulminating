
/**
 * Write a description of class Property here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Property extends Space
{
    private static int player;
    private static double price;
    private static double rent;
    private static double mortgageValue;
    public Property(int location, double price, String name, double rent,double mortgageValue )
    {
        this.name = name;
        this.location = location;
    }
    
    public int getPlayer()
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
    
    public double getMortgage()
    {
        return mortgageValue;
    }
}
