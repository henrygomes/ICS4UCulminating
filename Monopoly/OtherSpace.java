
/**
 * Write a description of class Action here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OtherSpace extends Space
{
   private int typeOfCard;//type of card to pick up, 0 if none
   private String name;
   private int tax;
   
   
   public OtherSpace(String name, int typeOfCard, int tax)
   {
       this.name = name;
       this.typeOfCard = typeOfCard;
       this.tax = tax;
   }
   
   public String getName()
   {
       return name;
   }
   
   public int getCardValue()
   {
       return typeOfCard;
   }
   
   public int getTax()
   {
       return tax;
   }
}
