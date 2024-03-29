package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.KeyHandler;
import Main.Panel;

public class Hero extends Entity {
	Panel p;
	KeyHandler keyH;
	int lives;
	public boolean yDirection = true;

	public Hero(Panel p, KeyHandler keyH) {
		this.p = p;
		this.keyH = keyH;
		this.width = p.tileSize;
		this.height = p.tileSize;
		setDefaultValues();
		getHeroImage();
	}

	public void setDefaultValues() {
		this.x = 10;
		this.y = 528;
		this.speed = 8;
	}

	public void getHeroImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("Mario-removebg-preview.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (keyH.upPressed == true && y >= 0 && yDirection) {
			y -= 8;

		}
		if (y <= 576 - 48 && yDirection) {
			y += 3;
		}

		if (x >= 768 - 48) {
			x = 10;
			y = 500;
			if (p.countLevel == 4) {
				keyH.restartTheGame=true;
				p.setWinTheGame();
				
			} else {
				p.goUpOneLevel();
			}
		}
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, this.width, this.height, null);

	}

}
