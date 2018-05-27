package sample;

import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class Token
{
    int[][] boardPos = new int[40][2];
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

    public void move(int position, int playerNumber)
    {
        offset = 10;
        if (position < 11 || (position > 19 && position < 30)){
            circle.setCenterX(boardPos[position][0]);
            circle.setCenterY(boardPos[position][1] + (offset * playerNumber));
        }
        else{
            circle.setCenterX(boardPos[position][0] + (offset * playerNumber));
            circle.setCenterY(boardPos[position][1]);
        }
    }
}
