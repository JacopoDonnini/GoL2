package Cazzeggio.ParticleSimulator;

public class Complex {
    private double module;
    private double angle;

    public Complex(double module, double angle) {
        this.module = module;
        this.angle = angle;
    }

    public double getModule() {
        return module;
    }

    public double getAngle() {
        return angle;
    }

    public void setModule(double module) {
        this.module = module;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setNumber(double module, double angle){
        this.module = module;
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "Complex{" +
                "module=" + module +
                ", angle=" + angle +
                '}';
    }
}
