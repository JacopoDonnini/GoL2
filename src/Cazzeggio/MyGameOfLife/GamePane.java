package Cazzeggio.MyGameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePane extends JPanel implements MouseMotionListener {
    private int size=200;

    private int scale=4;
    private Coordinates clickpos = new Coordinates(0,0);
    private ArrayList<Coordinates> alives = new ArrayList<>();

    public GamePane() {
        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                clickpos.setX(e.getX());
                clickpos.setY(e.getY());
            }
        });
        GameGrid grid = new GameGrid(size);
        grid.createGliderGun(10,10);
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.nextGeneration();
                if (clickpos.getX() != 0 && clickpos.getY() != 0) {
                    grid.createBrush(clickpos.getX()/scale, clickpos.getY()/scale );
                }
                alives = grid.getAlivesIndex();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size * scale, size * scale);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (Coordinates i : alives) {
            g.fillRect(i.getX() * scale, i.getY() * scale, scale, scale);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        clickpos.setX(e.getX());
        clickpos.setY(e.getY());
    }
    @Override
    public void mouseMoved(MouseEvent e){

    }
}