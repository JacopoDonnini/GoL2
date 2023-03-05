package Cazzeggio.MyGameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameOfLife implements ActionListener{
    private final int size = 200;
    private final int scale = 4;

    public static void main(String[] args) {
        new GameOfLife();
    }
    public GameOfLife(){
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }

            GamePane panel = new GamePane();
            panel.setBackground(Color.BLACK);

            JButton button = new JButton("Glider");
            button.setLocation(0,size*scale);
            button.setSize(100,30);
            button.setBackground(Color.lightGray);
            button.setVisible(true);
            button.addActionListener(GameOfLife.this);

            JFrame frame = new JFrame("Game of life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(size*scale,size*scale+30);

            frame.add(button);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    public class GamePane extends JPanel implements MouseMotionListener {
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
    @Override
    public void actionPerformed(ActionEvent e){

    }
}
