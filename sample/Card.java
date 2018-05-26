package sample;
/**
 * Write a description of class Cards here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
public class Card
{
    public static void CardPickup (Player player, int cardType, Player[] players)
    {

        Random rand = new Random();
        if (cardType ==1)//chance
        {
            int card = rand.nextInt(16);
            switch (card){
                case 0://advance to go, if pass go collect 200
                player.moveTo(0, true);
                break;
                case 1://advance to Illinois Ave (24), if pass go collect 200
                player.moveTo(24, true);
                MonopolyGame.land (24, player, -1);
                break;
                case 2://advance to St. Charles Place (11), if pass go collect 200
                player.moveTo(11, true);
                MonopolyGame.land(11, player, -1);
                break;
                case 3://advance to nearest utility, if owned pay 10x roll, if unowned buy
                if (player.getLocation() > 28 || player.getLocation() < 12){
                    player.moveTo(12, true);
                    MonopolyGame.land (12, player, 10);
                }
                else{
                    player.moveTo(28, true);
                    MonopolyGame.land (28, player, 10);
                }
                break;
                case 4://advance to nearest railroad, pay 2x rental, if unowned buy
                break;
                case 5://get 50
                player.addMoney (50);
                break;
                case 6://get out of jail free card
                player.newGOOJFCard();
                break;
                case 7://go back 3 spaces
                int playerLoc = player.getLocation();
                int playerTo = player.getLocation() -3;
                if (playerTo <0)
                    playerTo = 40 + playerTo;
                player.moveTo(playerTo, false);
                MonopolyGame.land (playerTo, player, -1);
                break;
                case 8://go to jail, dont get 200$
                break;
                case 9://pay 25 for each house, 100 for each hotel
                for (int i = 0; i < player.getProperties().size();i++)
                {
                    
                }
                break;
                case 10://pay 15$
                MonopolyGame.pay (player, 15);
                break;
                case 11://got to reading railroad, if pass go pay 200$
                player.moveTo(5, true);
                MonopolyGame.land(5, player, -1);
                break;
                case 12:// go to boardwalk
                player.moveTo(39, true);
                MonopolyGame.land(39, player, -1);
                break;
                case 13://pay each player 50
                for (int i = 0; i <players.length; i++)
                {
                    if (players[i] != player)
                        MonopolyGame.pay (player, players[i],50); 

                }
                break;
                case 14://collect 150
                player.addMoney(150);
                break;
                case 15://collect 100
                player.addMoney(100);
                break;
            }
        }
        else if (cardType == 2)
        {

        }
    }

}
