package Cazzeggio.ParticleSimulator;

import javax.swing.*;
import java.awt.Graphics;
import java.util.*;

public class SimulationFrame extends JFrame{
    List<Particle> particles = new ArrayList<>();
    SimulationFrame(){
        JFrame frame = new JFrame("Simulation");
        frame.setSize(400,400);

        particles.add(new Particle(0,0,1));
        particles.add(new Particle(10,10,1));
        particles.add(new Particle(237,300,1));


    }
    public class DrawParticles extends JComponent{
        @Override
        public void paint(Graphics g){
            for(Particle p : particles){
            }
        }
    }
}
