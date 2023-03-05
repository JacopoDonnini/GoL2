package Cazzeggio.ParticleSimulator;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public void setCoordinates(double x, double y){
        this.x = (int) x;
        this.y = (int) y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double distance(Coordinates p2){
        return Math.sqrt((x+p2.x)*(x+p2.x)+(y+p2.y)*(y+p2.y));
    }

    public double angle(Coordinates p2){
        if(p2.x-x<0.001)    return Math.PI/2;
        return Math.atan((p2.y-y)/(p2.x-x));
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
