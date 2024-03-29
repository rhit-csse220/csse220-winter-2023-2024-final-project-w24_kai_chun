package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	public static int level = 1;
	static Panel panel;
	Sound sound;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("The Best Game in The World");
		frame.setResizable(false);
		frame.setSize(1600, 920);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new Panel();
		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 85) {
					panel.coins.clear();
					panel.barriers.clear();
					panel.electricBarriers.clear();
					panel.hero.x = 10;
					panel.hero.y = 500;
					try {
						if (panel.countLevel < 4) {
							panel.countLevel++;
						}
						runApp(panel.countLevel);
					} catch (FileNotFoundException | InvalidLevelFormatException e1) {
						System.out.println("Level " + (panel.countLevel) + " does not exist. Going back to level 1");
						level = 1;
						e1.printStackTrace();
					}

				}
				if (e.getKeyCode() == 68) {
					panel.coins.clear();
					panel.barriers.clear();
					panel.electricBarriers.clear();
					panel.hero.x = 10;
					panel.hero.y = 500;
					try {
						if (panel.countLevel > 0) {
							panel.countLevel--;
						}
						runApp(panel.countLevel);
					} catch (FileNotFoundException | InvalidLevelFormatException e1) {
						System.out.println("Level " + (panel.countLevel) + " does not exist. Going back to level 1");
						level = 1;
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		
		panel.loadfile(1);
		frame.add(panel);
		System.out.println("");
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		panel.startGameThread();
		ImageIcon img = new ImageIcon("Mario_Series_Logo.svg-removebg-preview.png");
		frame.setIconImage(img.getImage());
	}

	protected static void runApp(int i) throws FileNotFoundException, InvalidLevelFormatException {
		System.out.println(panel.countLevel);
		panel.loadfile(panel.countLevel);
	}
}
