package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Entity.Barrier;
import Entity.Coin;
import Entity.ElectricBarrier;
import Entity.Hero;

public class Panel extends JPanel implements Runnable {
	private static final int Coin = 1;
	private static final int Barrier = 2;
	private static final int ElectricBarrier = 3;

	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;// 768 px
	final int screenHeight = tileSize * maxScreenRow;// 576 px

	int FPS = 60;
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	ArrayList<Coin> coins = new ArrayList<>();
	ArrayList<Barrier> barriers = new ArrayList<>();
	ArrayList<ElectricBarrier> electricBarriers = new ArrayList<>();

	Hero hero = new Hero(this, keyH);
//	Coin coin = new Coin(this, 300, 300);
//	Barrier barrier = new Barrier(this, 400, 300);
//	ElectricBarrier electricBarrier = new ElectricBarrier(this, 300, 400);

	int playerX = 10;
	int playerY = 500;
	int palyerSpeed = 14;

	public Panel() {

//		coins.add(coin);
//		barriers.add(barrier);
//		electricBarriers.add(electricBarrier);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void loadfile(int label) {
		String filename = "Level" + label + ".txt";

		try {
			FileReader file = new FileReader(filename);
			Scanner scanner = new Scanner(file);
			System.out.println("file loaded");
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				try {
					addObject(data);
				} catch (InvalidLevelFormatException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.err.println("File " + filename + " not found.");
		} catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}

	private void addObject(String data) throws InvalidLevelFormatException {
		Scanner scanner = new Scanner(data);
		int name = 0,x=0,y=0,angle=0;
		try {
	    name = scanner.nextInt();
		x=scanner.nextInt();
		y=scanner.nextInt();
		angle=scanner.nextInt();
		if(name<1 || name>3 ||
				x>768 ||x<0 ||
				y<0 || y>576|| 
				angle>360 || angle<0) {
			throw new InvalidLevelFormatException(data);
		}
		}catch(InputMismatchException e){
			System.err.println("Wrong format");
		}finally {
			switch(name) {
			case Coin:
				coins.add(new Coin(this,x,y));
				break;
			case Barrier:
				barriers.add(new Barrier(this,x,y));
			case ElectricBarrier:
				electricBarriers.add(new ElectricBarrier(this,x,y));
			}
			
			
		}
		
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS; // 0.016666seconds
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			update();
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0)
					remainingTime = 0;
				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void update() {
		hero.update();
		handleCollisions();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		hero.draw(g2);

		for (Coin co : coins) {
			if (co.ifdisapper()) {
				g2.setColor(Color.black);
				g2.drawRect(co.getX(), co.getY(), co.getWidth(), co.getHeight());
			} else {
				co.draw(g2);
			}
		}
		for(Barrier barrier : barriers) {
			if (barrier.ifdisapper()) {
				g2.setColor(Color.black);
				g2.drawRect(barrier.getX(), barrier.getY(), barrier.getWidth(), barrier.getHeight());
			} else {
				barrier.draw(g2);
			}
		}
		for(ElectricBarrier ele: electricBarriers) {
			if (ele.ifdisapper()) {
				g2.setColor(Color.black);
				g2.drawRect(ele.getX(), ele.getY(), ele.getWidth(), ele.getHeight());
			} else {
				ele.draw(g2);
			}
		}
//		barrier.draw(g2);
//		electricBarrier.draw(g2);
		g2.dispose();

	}

	public void handleCollisions() {
		for (Coin co : coins) {
			if (co.collidewith(hero)) {
				co.disapper();
			}
		}
	}

}