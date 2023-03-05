package Cazzeggio.MyGameOfLife;

public class FoodCell extends GameCell{
    private boolean food=false;

    public FoodCell(int positionX, int positionY) {
        super(false, positionX, positionY);
        this.food = true;
    }

    public boolean isNotEaten(){ return food; }

    public void setFood(boolean food){
        this.food = food;
    }
}
