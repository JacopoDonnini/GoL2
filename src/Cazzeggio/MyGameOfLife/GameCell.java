package Cazzeggio.MyGameOfLife;
import java.util.*;
import javax.swing.*;

public class GameCell extends Cell{
    private boolean alive=false;
    private int positionX;
    private int positionY;

    public GameCell(boolean alive,int positionX, int positionY) {
        this.alive = alive;
        this.positionX=positionX;
        this.positionY=positionY;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
