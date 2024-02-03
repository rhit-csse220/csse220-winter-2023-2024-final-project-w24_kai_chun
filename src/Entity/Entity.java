package Entity;


import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Entity {
	public int x, y, width, height;
	public int speed;
	public boolean disapper=false;

	public BufferedImage image;

	public Rectangle2D.Double getBoundingBox() {
		return new Rectangle2D.Double(this.x, this.y, this.width, this.height);
	}

	public boolean collidewith(Hero hero) {
//		System.out.println(this.getBoundingBox().intersects((hero.getBoundingBox())));
		return this.getBoundingBox().intersects(hero.getBoundingBox());

	}
	
	public boolean ifdisapper() {
		return disapper;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
