package Cazzeggio;

import Cazzeggio.MyGameOfLife.Coordinates;
import Cazzeggio.MyGameOfLife.GameGrid;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ActionListener,ChangeListener {

    public static void main(String[] args) {
    new GameFrame();
    }
    int size=300;
    int scale=3;
    Timer timer;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JSlider slider;
    JPanel slpanel;
    JLabel sllabel;
    GamePane panel;

    boolean clicked=false;
    boolean reset=false;
    boolean paused=false;
    boolean randomize=false;
    boolean food=false;
    boolean brush=false;
    GameFrame(){
        panel = new GamePane();
        panel.setBackground(Color.BLACK);
        panel.add(setupGUI());

        this.add(panel);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    @Override
    public void stateChanged(ChangeEvent e){
        timer.setDelay(1001-slider.getValue());
        sllabel.setText("Speed : "+slider.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button1){clicked=true;}
        if(e.getSource()==button2){reset=true;}
        if(e.getSource()==button3){
            if(!paused) paused=true;
            else        paused=false;
        }
        if(e.getSource()==button4){randomize=true;}
        if(e.getSource()==button5){
            if(!brush) brush=true;
            else       brush=false;
        }
        if(e.getSource()==button6){
            if(!food)  food=true;
            else       food=false;
        }
    }

    private JComponent setupGUI(){

        sllabel = new JLabel("Speed : 51");

        slpanel = new JPanel();
        slpanel.setOpaque(true);
        slpanel.setBackground(Color.lightGray);
        slpanel.setBorder(BorderFactory.createRaisedBevelBorder());

        slider = new JSlider(1,1001);
        slider.setBackground(Color.lightGray);
        slider.addChangeListener(this);

        button6 = new JButton("Food");
        button6.setBackground(Color.GRAY);
        button6.setFocusable(false);
        button6.setVisible(true);
        button6.setBorder(BorderFactory.createRaisedBevelBorder());
        button6.addActionListener(this);

        button5 = new JButton("Brush/Pixel");
        button5.setBackground(Color.lightGray);
        button5.setFocusable(false);
        button5.setVisible(true);
        button5.setBorder(BorderFactory.createRaisedBevelBorder());
        button5.addActionListener(this);

        button4 = new JButton("Randomize");
        button4.setBackground(Color.lightGray);
        button4.setFocusable(false);
        button4.setVisible(true);
        button4.setBorder(BorderFactory.createRaisedBevelBorder());
        button4.addActionListener(this);

        button3 = new JButton("Pause");
        button3.setBackground(Color.lightGray);
        button3.setFocusable(false);
        button3.setVisible(true);
        button3.setBorder(BorderFactory.createRaisedBevelBorder());
        button3.addActionListener(this);

        button2 = new JButton("Empty");
        button2.setBackground(Color.lightGray);
        button2.setFocusable(false);
        button2.setVisible(true);
        button2.setBorder(BorderFactory.createRaisedBevelBorder());
        button2.addActionListener(this);

        button1 = new JButton("Glider");
        button1.setBackground(Color.lightGray);
        button1.setFocusable(false);
        button1.setVisible(true);
        button1.setBorder(BorderFactory.createRaisedBevelBorder());
        button1.addActionListener(this);

        this.setTitle("Game of Life");
        this.setLayout(new BorderLayout(0,0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(size*scale,size*scale);

        slpanel.add(slider);
        slpanel.add(sllabel);

        JPanel ret = new JPanel();
        ret.setOpaque(true);
        ret.setBackground(Color.LIGHT_GRAY);
        ret.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ret.add(button1);
        ret.add(button2);
        ret.add(button3);
        ret.add(button4);
        ret.add(button5);
        ret.add(button6);
        ret.add(slpanel);
        return ret;
    }

    public class GamePane extends JPanel implements MouseMotionListener,MouseListener {
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
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    clickpos.setX(0);
                    clickpos.setY(0);
                }
            });
            GameGrid grid = new GameGrid(size);
            grid.createGliderGun(10,10);
            timer = new Timer(51, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!paused) grid.nextGeneration();
                    if(food){
                        if (clickpos.getX() != 0 && clickpos.getY() != 0) {
                            if (clickpos.getX() == size * scale || clickpos.getY() == size * scale) {
                                clickpos.setX(0);
                                clickpos.setY(0);
                            }else{

                            }
                        }
                    }else if (clickpos.getX() != 0 && clickpos.getY() != 0) {
                        if(clickpos.getX() == size*scale || clickpos.getY() == size*scale){
                            clickpos.setX(0);
                            clickpos.setY(0);
                        }else {
                            if(brush)   grid.createBrush(clickpos.getX() / scale, clickpos.getY() / scale);
                            else        grid.createPixel(clickpos.getX() / scale, clickpos.getY() / scale);
                        }
                    }
                    if(clicked){
                        grid.createGliderGun(50,50);
                        clicked=false;
                    }
                    if(reset){
                        grid.emptyGrid();
                        reset=false;
                    }
                    if(randomize){
                        grid.randomize();
                        randomize=false;
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

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            clickpos.setX(0);
            clickpos.setY(0);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
