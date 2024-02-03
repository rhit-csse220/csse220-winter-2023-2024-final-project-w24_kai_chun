package Entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.KeyHandler;
import Main.Panel;

public class Coin extends Entity{
	Panel p;
	
	public Coin(Panel p, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.p = p;
		getCoinImage();
	}
	
	public void getCoinImage()
	{
		try {
//			System.out.println("coin");
			image = ImageIO.read(getClass().getResourceAsStream("MarioCoin-removebg-preview.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update()
	{
//		if(keyH.upPressed == true && y>=0)
//		{
//			y -= speed;
//			
//		}
//		if(y<=576-48) {
//			y+=5;
//		}
	}
	
	public void draw(Graphics2D g2)
	{
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, p.tileSize, p.tileSize);
		g2.drawImage(image, x, y, p.tileSize/2, p.tileSize/2, null);
	
	}

	public void disapper() {
		this.disapper=true;
	}

	public boolean collideWithHero(Hero hero) {
 		return false;
	}
}
