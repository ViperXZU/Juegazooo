package Juegazo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Juegazoo extends JFrame implements ActionListener {

    static int FRAME_WIDTH = 640;
    static int FRAME_HEIGHT = 480;
    public final int DELAY = 10;
    public Background background;

    private Timer timer;
    public Juegazoo(){
        setTitle("Jueguito");

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width, dim.height/2-this.getSize().height);
        /*
         * Background
         */
        background = new Background("src/IMG/Combined background - Blue.png");

        timer = new Timer(DELAY, this);
        timer.start();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background.getImageBackground(),0,0,null);
    }

    public void actionPerformed(java.awt.event.ActionEvent e){
        repaint();
        updateGame();
    }

    public void updateGame(){
        background.updateBackground();
    }

    public static void main(String[] args) {
        Juegazoo juego = new Juegazoo();
    }
}