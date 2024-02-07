package Entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Panel;

public class Missile extends Entity{
	public Missile(Panel p, int x, int y, int angle) {
		this.x=x;
		this.y=y;
		this.speed=5;
		this.width=p.tileSize*4/3;
		this.height=p.tileSize;
		getMissileImage();
	}

	public void move() {
		x -= 5;
	}

	public void getMissileImage() {
		try {
//			System.out.println("ElectricPipe");
			image = ImageIO.read(getClass().getResourceAsStream("MarioMissile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		
		g2.drawImage(image, x, y, this.width, this.height, null);

	}
}
