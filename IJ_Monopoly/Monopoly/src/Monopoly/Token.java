 

import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class Token
{
  //  int[][] boardPos = new int[40][2];
    int boardPos[][] = {
          {745,745},
          {664,759},
          {596,759},
          {530,759},
          {465,759},
          {401,759},
          {335,759},
          {271,759},
          {203,759},
          {137,759},
          {51,783},
          {40,658},
          {40,597},
          {40,530},
          {40,463},
          {40,398},
          {40,333},
          {40,265},
          {40,201},
          {40,141},
          {53,42},
          {139,40},
          {204,40},
          {271,40},
          {332,40},
          {401,40},
          {466,40},
          {530,40},
          {596,40},
          {660,40},
          {749,54},
          {762,137},
          {762,201},
          {762,265},
          {762,333},
          {762,398},
          {762,463},
          {762,530},
          {762,597},
          {762,658}
  };
    int x;
    int y;
    Circle circle;
    Color color;
    int offset = 0;


    public Token(int pos)
    {
        if (pos ==0){
            color = Color.BLUE;
            x = 740;
            y = 740;
        }

        if (pos ==1){
            color = Color.GREEN;
            x = 770;
            y = 740;
        }

        if (pos ==2){
            color = Color.RED;
            x = 740;
            y = 770;
        }

        if (pos ==3){
            color = Color.YELLOW;
            x = 770;
            y = 770;
        }

        circle  = new Circle(x, y, 10, color);
    }

    public Circle getCircle()
    {
        return circle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move(Player players[], int playerNumber)
    {
        offset = 10;
        int position = players[playerNumber].getLocation();
        int counter = 0;
        for (int i = 0; i < players.length; i++){
            if (players[i].getLocation() == position && i != playerNumber)
                counter++;
        }

        if (players[playerNumber].getInJail()){
            boardPos[10][0] = 72;
            boardPos[10][1] = 728;
        }
        else{
            boardPos[10][0] = 51;
            boardPos[10][1] = 783;
        }

        if (counter == 0){
            circle.setCenterX(boardPos[position][0]);
            circle.setCenterY(boardPos[position][1]);
        }

        else if (counter%2 == 0 ){
            circle.setCenterX(boardPos[position][0] + (offset * counter));
            circle.setCenterY(boardPos[position][1]);
        }

        else {
            circle.setCenterX(boardPos[position][0] - (offset * counter));
            circle.setCenterY(boardPos[position][1]);
        }
    }
}
