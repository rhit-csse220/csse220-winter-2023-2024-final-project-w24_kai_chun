package mainApp;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;

public class GameComponent extends JComponent{

protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	JLayeredPane EverythingButPlayer = new JLayeredPane();

	Hero hero = new Hero();
	BufferedImage img = null;
    try {
        img = ImageIO.read(new File("Mario.png"));
    } catch (IOException e) {
    }
    g2 = (Graphics2D) img.getGraphics();
    g2.drawImage(img,300, 300, EverythingButPlayer);
    this.add(EverythingButPlayer);
}
}
