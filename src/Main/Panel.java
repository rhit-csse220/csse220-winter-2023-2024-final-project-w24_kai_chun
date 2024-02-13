package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Entity.Barrier;
import Entity.Coin;
import Entity.ElectricBarrier;
import Entity.Hero;
import Entity.Missile;
import Entity.TrackMissile;

public class Panel extends JPanel implements Runnable {
	private static final int Coin = 1;
	private static final int Barrier = 2;
	private static final int ElectricBarrier = 3;
	private static final int Missile = 4;
	private static final int TrackMissile = 5;
	final int originalTileSize = 16;
	final int scale = 3;
	public int countCoins = 0;
	public int countLevel = 1;
	public int countLives = 3;
	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;// 768 px
	final int screenHeight = tileSize * maxScreenRow;// 576 px
	boolean gameOver;
	int countBarrierCollision;
	int FPS = 60;
	long seconds = 0;
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Sound sound;

	ArrayList<Coin> coins = new ArrayList<>();
	ArrayList<Barrier> barriers = new ArrayList<>();
	ArrayList<ElectricBarrier> electricBarriers = new ArrayList<>();
	ArrayList<Missile> missiles = new ArrayList<>();
	ArrayList<TrackMissile> trackMissiles = new ArrayList<>();
	Hero hero = new Hero(this, keyH);

	int playerX = 10;
	int playerY = 528;
	int palyerSpeed = 14;
	private int frameCount;
	private boolean gameStop;
	private int countSound = 1;
	private boolean playingMusic;

	public Panel() {
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
		} catch (FileNotFoundException e) {
			System.err.println("File " + filename + " not found.");
		} catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}

	private void addObject(String data) throws InvalidLevelFormatException {
		Scanner scanner = new Scanner(data);
		int name = 0, x = 0, y = 0, angle = 0;
		try {
			name = scanner.nextInt();
			x = scanner.nextInt();
			y = scanner.nextInt();
			angle = scanner.nextInt();
			if (name < 1 || name > 5 || x < 0 || y < 0 || y > 576 || angle > 360 || angle < 0) {
				throw new InvalidLevelFormatException(data);
			}
		} catch (InputMismatchException e) {
			System.err.println("Wrong format");
		} finally {
			switch (name) {
			case Coin:
				coins.add(new Coin(this, x, y));
				break;
			case Barrier:
				barriers.add(new Barrier(this, x, y, angle));
				break;
			case ElectricBarrier:
				electricBarriers.add(new ElectricBarrier(this, x, y, angle));
				break;
			case Missile:
				missiles.add(new Missile(this, x, y, angle));
				break;
			case TrackMissile:
				trackMissiles.add(new TrackMissile(this, x, y, angle));
			}

		}

	}

	public void startGameThread() {
		gameThread = new Thread(this);

		gameThread.start();
		sound = new Sound();

	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS; // 0.016666seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		long start = System.currentTimeMillis();
		;
		long seconds = 0;
		while (gameThread != null) {

			if (keyH.startGame) {
				seconds = (System.currentTimeMillis() - start) / 1000;
			}
//			System.out.println(String.valueOf(seconds));

//			        Thread.sleep(500);

			if (seconds >= 5 && !gameOver) {
				hero.x += hero.speed / 3;
			}
			// update coins and if collision add countCoins and remove the coin
			for (int i = 0; i < coins.size(); i++) {
				Coin coin = coins.get(i);
				if (coin.collidewith(hero)) {
					if (countSound > 1) {
						stopMusic();
					}
					countSound++;
					coins.remove(i);
					countCoins++;
					i--;
					if (countLives != 0) {
						playSoundEffect(0);
					}
				}
				if (keyH.startGame && countLives > 0 && !this.gameStop) {
					coin.update();
				}
			}

			// update missiles and if collision minus lives and remove the missile
			for (int i = 0; i < barriers.size(); i++) {
				Barrier barrier = barriers.get(i);
				if(barrier.collideTop(hero)) {
					System.out.println("zzzz");
					hero.y -= 3;
				}
				if(barrier.collideBottom(hero)) {
					System.out.println("ddd");
					hero.y += 8;
				}
				if (barrier.collidewith(hero)) {
					gameStop = true;
					hero.speed = 0;
				} else {
					int barrierNotCollide = 0;
					for (Barrier bar : barriers) {
						if (!bar.collidewith(hero)) {
							barrierNotCollide++;
						}
					}
					if (barrierNotCollide == barriers.size()) {
						gameStop = false;
						hero.speed = 3;
					}
				}
				if (keyH.startGame && countLives > 0 && !this.gameStop) {
					barrier.update();
				}
			}

			// update missiles and if collision minus lives and remove the missile
			for (int i = 0; i < electricBarriers.size(); i++) {
				ElectricBarrier ele = electricBarriers.get(i);
				if (ele.collidewith(hero)) {
//					stopMusic();
					if (countSound > 1) {
						stopMusic();
					}
					countSound++;
					electricBarriers.remove(i);
					countLives--;
					i--;
					if (countLives != 0) {
						playSoundEffect(3);
					}
				}

				if (keyH.startGame && countLives > 0 && !this.gameStop) {
					ele.update();
				}
			}

			// update missiles and if collision minus lives and remove the missile
			for (int i = 0; i < missiles.size(); i++) {
				Missile missile = missiles.get(i);
				if (missile.collidewith(hero)) {
//					stopMusic();
					if (countSound > 1) {
						stopMusic();
					}
					countSound++;
					missiles.remove(i);
					countLives--;
					i--;
					if (countLives != 0) {
						playSoundEffect(3);
					}
				}

				if (keyH.startGame && countLives > 0) {
					missile.move();
				}
			}

			// update trackMissiles and if collision minus lives and remove the trackMissile
			for (int i = 0; i < trackMissiles.size(); i++) {
				TrackMissile trackMissile = trackMissiles.get(i);
				if (trackMissile.collidewith(hero)) {
//					stopMusic();
					if (countSound > 1) {
						stopMusic();
					}
					countSound++;

					trackMissiles.remove(i);
					countLives--;
					i--;
					if (countLives != 0) {
						playSoundEffect(3);
					}
				}
				if (keyH.startGame && countLives > 0) {
					trackMissile.move();
				}

				if (trackMissile.y < hero.y && countLives > 0) {
					trackMissile.moveup();
				} else if (countLives > 0) {
					trackMissile.movedown();
				}
			}
			if (countLives > 0) {
				hero.update();
			}
			repaint();

			// make the thread wait a little bit set to update 60 times per second
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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		hero.draw(g2);
		if (!keyH.startGame) {
			g2.setColor(Color.yellow);
			g2.setFont(new Font("MV Boli", Font.PLAIN, 30));
			g2.drawString("Please hitted the enter to start the game!", 60, 100);
			g2.setColor(Color.BLACK);
		} else {

			// draw all of the coins
			for (Coin coin : coins) {
				coin.draw(g2);
			}

			for (Barrier barrier : barriers) {
//			if (barrier.ifcollision()) {
////				hero.speed=0;
//				barrier.update();
//				barrier.draw(g2);
//			} else {
//				barrier.update();
//				barrier.draw(g2);
//
//			}

				barrier.draw(g2);
			}

			for (ElectricBarrier ele : electricBarriers) {
//			if (ele.ifcollision()) {
//				g2.setColor(Color.red);
//				g2.setFont(new Font("MV Boli", Font.PLAIN, 45));
//				g2.drawString("Game Over!", 150, 100);
//				g2.setColor(Color.BLACK);
//				// stop a little bit
////				try {
////					Thread.sleep(3000);
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////				this.loadfile(1);
////				hero.x=10;
////				hero.y=500;
////				ele.resetcollision();
//				ele.update();
//				ele.draw(g2);
//			} else {
//				ele.update();
//				ele.draw(g2);
//
//			}
				ele.draw(g2);
			}
			// draw all of the missiles
			for (Missile missile : missiles) {
				missile.draw(g2);
			}

			// draw all of the trackMissile
			for (TrackMissile trackMissile : trackMissiles) {
				trackMissile.draw(g2);
			}
		}
		if (keyH.startGame) {
			// drawing the data for the upper left of the screen
			g2.setColor(Color.white);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g2.drawString("Level: " + countLevel, 10, 20);
			g2.setColor(Color.red);
			String liv = "HP: " + countLives;
			g2.drawString(liv, 100, 20);
			g2.setColor(Color.yellow);
			g2.drawString("Coins: " + countCoins, 190, 20);
			g2.setColor(Color.black);
			if (!playingMusic) {
//				playMusic(1);
				playingMusic = true;
			}
			if (this.countLives == 0) {
				frameCount++;
				g2.setColor(Color.red);
				g2.setFont(new Font("MV Boli", Font.PLAIN, 70));
				g2.drawString("Game Over!", 205, 300);
				g2.setColor(Color.BLACK);
				hero.speed = 0;
				if (frameCount == 1) {
					stopMusic();
					playSoundEffect(2);
				}
				if (frameCount == 300) {
					this.countLevel = 1;
					this.countCoins = 0;
					this.countLives = 3;
					frameCount = 0;
					this.coins.clear();
					this.barriers.clear();
					this.electricBarriers.clear();
					this.seconds = 0;
					hero.x = 10;
					hero.y = 528;
					stopMusic();
					stopMusic();
					playingMusic = false;
//					playSoundEffect(3);
					this.loadfile(this.countLevel);
				}
				// how can I make my game stop for 3 second and also show game over
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}

			}
			g2.dispose();

		}

	}

	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();

	}

	public void stopMusic() {
		sound.stop();
	}

	public void playSoundEffect(int i) {

		sound.setFile(i);
		sound.play();
	}

	public int getlevel() {
		return keyH.level;
	}

	public void goUpOneLevel() {
		this.countLevel++;
		this.loadfile(this.countLevel);
	}
}
