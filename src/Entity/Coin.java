package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.KeyHandler;
import Main.Panel;

public class Coin extends Entity {
	Panel p;

	public Coin(Panel p, int x, int y) {
		this.x = x;
		this.y = y;
		this.p = p;
		this.width = 20;
		this.height = 20;
		getCoinImage();
	}

	public void getCoinImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("MarioCoin-removebg-preview.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		if (this.collision == false) {
			g2.drawImage(image, x, y, p.tileSize / 2, p.tileSize / 2, null);
		} else {

		}
	}
}
