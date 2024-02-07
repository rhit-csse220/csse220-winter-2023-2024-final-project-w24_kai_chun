package Entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Panel;

public class TrackMissile extends Missile {

	public TrackMissile(Panel p, int x, int y, int angle) {
		super(p, x, y, angle);
		getTrackMissileImage();
	}

	public void move() {
		x -= 6;
	}
	public void moveup() {
		y+=2; 
	}
	public void movedown() {
		y-=2;
	}
	public void getTrackMissileImage() {
		try {
//			System.out.println("ElectricPipe");
			image = ImageIO.read(getClass().getResourceAsStream("TrackMissile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, this.width / 3, this.height, null);

	}
}
