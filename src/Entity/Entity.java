package Entity;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Entity {
	public int x, y, width, height;
	public int speed;
	public boolean collision = false;

	public BufferedImage image;

//	public Rectangle2D.Double getBoundingBox() {
//		return new Rectangle2D.Double(this.x, this.y, this.width, this.height);
//	}

//	public boolean collidewith(Hero hero) {
////		System.out.println(this.getBoundingBox().intersects((hero.getBoundingBox())));
//		return this.getBoundingBox().intersects(hero.getBoundingBox());
//
//	}

	public void update() {
		x -= 2;
	}

	public boolean ifcollision() {
		return collision;
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
	public boolean collidewith(Hero hero) {
		Rectangle heroRectangle = new Rectangle(hero.x,hero.y,hero.width,hero.height);
		if(heroRectangle.intersects(new Rectangle(this.x,this.y,this.width,this.height))) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public void resetcollision() {
		
		this.collision=false;
		
	}
	public void disappear() {
		this.collision = true;
	}

}
