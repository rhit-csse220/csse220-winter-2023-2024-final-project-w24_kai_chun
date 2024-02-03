package Entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Panel;

public class ElectricBarrier extends Barrier {

	public ElectricBarrier(Panel p, int x, int y, int angle) {
		super(p, x, y,angle);
		this.width=p.tileSize*4;
		this.height=p.tileSize;
	}
	
	@Override
	public void getPipeImage()
	{
		try {
//			System.out.println("ElectricPipe");
			image = ImageIO.read(getClass().getResourceAsStream("EletricBarrier.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics2D g2)
	{
		g2.rotate(Math.toRadians(angle), x, y);
		g2.drawImage(image, x, y, this.width/6, this.height*3, null);
		g2.rotate(-Math.toRadians(angle), x, y);
		
	}

}
