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
	
	public Hero(Panel p, KeyHandler keyH)
	{
		this.p = p;
		this.keyH = keyH;
		this.width=p.tileSize;
		this.height=p.tileSize;
		setDefaultValues();
		getHeroImage();
	}
	
	public void setDefaultValues()
	{
		this.x = 10;
		this.y = 500;
		this.speed = 14;
	}
	
	public void getHeroImage()
	{
		try {
			System.out.println("ter");
			image = ImageIO.read(getClass().getResourceAsStream("Mario-removebg-preview.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update()
	{               
		if(keyH.upPressed == true && y>=0)
		{
			y -= speed;
			
		}
		if(y<=576-48) {
			y+=5;
		}
		
		if(x >= 768-48)
		{
			x-= speed/7;
		}
		x += speed/7;
	}
	
	public void draw(Graphics2D g2)
	{
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, p.tileSize, p.tileSize);
		g2.drawImage(image, x, y, this.width, this.height, null);
		
	}
	
}
