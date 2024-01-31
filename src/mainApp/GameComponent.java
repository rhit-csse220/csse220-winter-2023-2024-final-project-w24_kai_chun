package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class GameComponent extends JComponent {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Mario.png"));
            g2.drawImage(img, 300, 300, this); // Draw the image on the component
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if there's an error
        }
    }
}
