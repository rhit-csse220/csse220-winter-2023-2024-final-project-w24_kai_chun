package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		this.angle = angle;

		getPipeImage();
	}

	public void getPipeImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("MarioPipe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean collideTop(Hero hero) {
		Rectangle heroRectangle = new Rectangle(hero.x,hero.y,hero.width,hero.height);
		if(heroRectangle.intersectsLine(x+5,y,x+this.width,y)) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean collideBottom(Hero hero) {
		Rectangle heroRectangle = new Rectangle(hero.x,hero.y,hero.width,hero.height);
		if(heroRectangle.intersectsLine(x+5, y+this.height,x+this.width, y+this.height)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.rotate(Math.toRadians(angle), x, y);
		g2.drawImage(image, x, y, this.width, this.height, null);
		g2.setColor(Color.red);
		g2.setColor(Color.yellow);
		g2.drawLine(x+5,y,x+this.width,y);
		g2.drawLine(x+5, y+this.height,x+this.width, y+this.height);		
		g2.setColor(Color.black);
		g2.rotate(-Math.toRadians(angle), x, y);
	}
}
