package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 500;
    Image logo;
    Timer timer;
    int xVelocity = 10;
    int yVelocity = 10;
    int x = 10;
    int y = 10;

    MainMenuPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.cyan);

        logo = new ImageIcon("porcupinefish.png").getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(logo, x, y, null);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x>= PANEL_WIDTH-logo.getWidth(null) || x<0){
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;

        if (y>= PANEL_HEIGHT-logo.getHeight(null) || y<0){
            yVelocity = yVelocity * -1;
        }
        y = y + yVelocity;
        repaint();
        System.out.println(x);

    }
}
