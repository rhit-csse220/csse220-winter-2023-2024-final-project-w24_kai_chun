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
 
        try {
            BufferedImage originalImg = ImageIO.read(new File("Mario.png"));
 
            // Desired new dimensions
            int newWidth = originalImg.getWidth() / 5+20; // Example: scale to half the original width
            int newHeight = originalImg.getHeight() / 5+20; // Example: scale to half the original height
 
            // Create a new image with the desired dimensions
            BufferedImage scaledImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
 
            // Draw the original image scaled onto the new image
            Graphics2D g2d = scaledImg.createGraphics();
            g2d.drawImage(originalImg, 0, 0, newWidth, newHeight, null);
            g2d.dispose();
 
            // Draw the scaled image on the component at the desired position
            g2.drawImage(scaledImg, 50, 800, this);
 
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if there's an error
        }
    }

}
