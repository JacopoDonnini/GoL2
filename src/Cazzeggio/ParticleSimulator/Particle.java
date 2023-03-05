package Cazzeggio.ParticleSimulator;

import javax.swing.text.Position;

public class Particle {
    private final static int universalacceleration=391;
    private int mass;
    private Coordinates coordinates;

    public Particle(int x, int y, int mass){
        this.mass = mass;
        coordinates = new Coordinates(x,y);
    }

    public Particle(Coordinates coordinates, int mass) {
        this.mass = mass;
        this.coordinates = coordinates;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getMass() {
        return mass;
    }

    public Complex getAcceleration(Particle p){
        double forceOVmass = (universalacceleration*(mass*p.mass)/(coordinates.distance(p.coordinates)*coordinates.distance(p.coordinates)))/mass;
        Complex res = new Complex(forceOVmass,coordinates.angle(p.coordinates));
        return res;
    }

    public void updateCoordinates(Complex acceleration){
        double x = Math.sin(acceleration.getAngle())*acceleration.getModule();
        double y = Math.cos(acceleration.getAngle())*acceleration.getModule();
        int prex = coordinates.getX();
        int prey = coordinates.getY();
        System.out.println("New coordinates{" + "x=" + x + prex + ", y=" + y + prey + '}');
        coordinates.setCoordinates(x+prex,y+prey);
    }

    public static void main(String[] args) {
        Particle p1 = new Particle(0,0,1);
        Particle p2 = new Particle(10,10,1);
        Particle p3 = new Particle(5,5,1);
        System.out.println(p1.coordinates.toString());
        System.out.println(p2.coordinates.toString());
        System.out.println(p3.coordinates.toString());
        for(int i=0; i<2; i++){
            p1.updateCoordinates(p1.getAcceleration(p2));
            p1.updateCoordinates(p1.getAcceleration(p3));
            p2.updateCoordinates(p2.getAcceleration(p1));
            p2.updateCoordinates(p2.getAcceleration(p3));
            p3.updateCoordinates(p3.getAcceleration(p1));
            p3.updateCoordinates(p3.getAcceleration(p2));
            System.out.println();
        }
        System.out.println(p1.coordinates.toString());
        System.out.println(p2.coordinates.toString());
        System.out.println(p3.coordinates.toString());
    }
}
