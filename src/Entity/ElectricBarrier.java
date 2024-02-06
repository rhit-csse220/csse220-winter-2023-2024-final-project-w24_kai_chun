package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Panel;

public class ElectricBarrier extends Barrier {

	public ElectricBarrier(Panel p, int x, int y, int angle) {
		super(p, x, y, angle);
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.width = 32;
		this.height = 144;
	}

	@Override
	public void getPipeImage() {
		try {
//			System.out.println("ElectricPipe");
			image = ImageIO.read(getClass().getResourceAsStream("EletricBarrier.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
//		if(this.collision) {
//			g2.setColor(Color.red);
//			g2.setFont(new Font("MV Boli",Font.PLAIN,45));
//			g2.drawString("Game Over!",150,100);
//		}
		
		g2.rotate(Math.toRadians(angle), x, y);
		g2.drawImage(image, x, y, this.width , this.height, null);
		g2.drawRect( x, y, this.width , this.height );
		
		g2.rotate(-Math.toRadians(angle), x, y);
		
	}
	@Override
	public boolean collidewith(Hero hero) {
		Rectangle heroRectangle = new Rectangle(hero.x,hero.y,hero.width,hero.height);
		if(heroRectangle.intersects(new Rectangle(this.x,this.y,this.width,this.height))) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
