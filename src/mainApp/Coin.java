package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Coin {
	private Color coincolor = Color.yellow;
	private int radius=4;
	private int Positionx;
	private int Positiony;
	
	public Coin(int x,int y) {
		this.Positionx=x;
		this.Positiony=y;
		
	}
	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawOval(Positionx, Positiony, radius, radius);
	}
}
