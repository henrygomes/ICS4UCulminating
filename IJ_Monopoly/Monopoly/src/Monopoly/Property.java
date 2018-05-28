package Monopoly;

import javafx.scene.paint.*;
/**
 * Write a description of class Property here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Property extends Space
{
    private Player player;//who owns this property
    private double price;//cpst of initially buying the property
    private double rent;//cost of landing on space when owned
    private char colour;//the car value to show what colour the property is and what group it belongs to
    private boolean isPropMortgaged = false;//property cannot be created mortgaged
    private int numberOfHouses = 0;
    private String name;//name of property
    public Property(String name, int loc, double cost, double rent, char colour)//initializing the property
    {
        this.name = name;
        this.location = loc;
        this.colour = colour;
        this.price = cost;
        this.rent = rent;
    }
    public void setRent (double newRent)
    {
        rent = newRent;
    }
    public Player getOwner()// get method for the player that owns this
    {
        return this.player;
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
    
    public void newOwner(Player player)//assigns a new owner
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
        
    public double getRent(int roll)//if the property is a utility
    {
        return roll * 4;
    }

    public double getMortgage()//get mortgage value
    {
        return price / 2;
    }
    
    public boolean getIsMortgage()// get if the property is mortgaged
    {
        return isPropMortgaged;
    }
    
    public void isMortgaged()//sets the boolean mortgaged to true
    {
        isPropMortgaged = true;
    }

    public Color getColor()//returns the color value as a colour object
    {
        if(this.colour == 'b'){
            Color c = Color.rgb(151, 85, 57);
            return c;
        }
        if(this.colour == 'l'){
            Color c = Color.rgb(163,223,249);
            return c;
        }
        if (this.colour == 'p'){
            Color c = Color.rgb(156,122,79);
            return c;
        }
        if (this.colour == 'o'){
            Color c = Color.rgb(253,146,39);
            return c;
        }
        if (this.colour == 'r'){
            Color c = Color.rgb(246,42,29);
            return c;
        }
        if (this.colour == 'y'){
            Color c = Color.rgb(255,239,53);
            return c;
        }
        if (this.colour == 'g'){
            Color c = Color.rgb(0,174,97);
            return c;
        }
        if (this.colour == 'd'){
            Color c = Color.rgb(0,115,184);
            return c;
        }
        else{
            Color c = Color.rgb(0,0,0);
            return c;
        }
    }
    
    public void unMortgage()//sets mortgage to true
    {
        isPropMortgaged = false;
    }
}
