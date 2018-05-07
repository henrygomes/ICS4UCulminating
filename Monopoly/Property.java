
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
    private static char colour;
    public Property(String name, int loc, double cost, double rent, double mortgage, char colour)
    {
        this.name = name;
        this.location = loc;
        this.colour = colour;
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
    
    //public double getRent(int roll)

    public double getMortgage()
    {
        return mortgageValue;
    }
}
