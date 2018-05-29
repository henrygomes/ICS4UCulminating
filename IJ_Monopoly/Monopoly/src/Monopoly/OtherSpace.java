 

 

/**
 * Write a description of class Action here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OtherSpace extends Space// OtherSpaces are all spaces on the board excluding Properties
{
   private int typeOfCard;//type of card to pick up, 0 if none

   private double tax;//tax to pay 
   public OtherSpace(String name, int typeOfCard, double tax, int location)//initialization
   {
       this.name = name;
       this.typeOfCard = typeOfCard;
       this.tax = tax;
       this.location = location;
   }
   
   public String getName()
   {
       return name;
   }
   
   public int getCardValue()
   {
       return typeOfCard;
   }
   
   public double getTax()
   {
       return tax;
   }
}
