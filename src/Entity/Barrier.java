package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Panel;

public class Barrier extends Entity {
	Panel p;
	public int angle;

	public Barrier(Panel p, int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.p = p;
		this.width = p.tileSize;
		this.height = p.tileSize * 3;
		this.angle = angle;// (int) (Math.random()*46);

		getPipeImage();
	}

	public void getPipeImage() {
		try {
//			System.out.println("Pipe");
			image = ImageIO.read(getClass().getResourceAsStream("MarioPipe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void draw(Graphics2D g2) {
		g2.rotate(Math.toRadians(angle), x, y);
		g2.drawImage(image, x, y, this.width, this.height, null);
		g2.setColor(Color.red);
		g2.drawRect(x, y, width, height);
		g2.setColor(Color.black);
		g2.rotate(-Math.toRadians(angle), x, y);

	}

}
