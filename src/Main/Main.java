package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	public static int level = 1;
	static Panel panel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setTitle("The Best Game in The World");
//		frame.setLocation(-10, 0);
		frame.setResizable(false);
		frame.setSize(1600, 920);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new Panel();
		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

				int code = e.getKeyCode();
				if (e.getKeyCode() == 85) {
					panel.coins.clear();
					panel.barriers.clear();
					panel.electricBarriers.clear();
					panel.hero.x=10;
					panel.hero.y=500;
					try {
						level++;
						runApp(level);
					} catch (FileNotFoundException | InvalidLevelFormatException e1) {
						System.out.println("Level " + (level) + " does not exist. Going back to level 1");
						level = 1;
						e1.printStackTrace();
					}
//					panel.loadfile(panel.getlevel());

				}
				if (e.getKeyCode() == 68) {
//		    		runApp(levelNumb-1);
					panel.coins.clear();
					panel.barriers.clear();
					panel.electricBarriers.clear();
					panel.hero.x=10;
					panel.hero.y=500;
					try {
						level--;
						runApp(level);
					} catch (FileNotFoundException | InvalidLevelFormatException e1) {
						System.out.println("Level " + (level) + " does not exist. Going back to level 1");
						level = 1;
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.loadfile(1);
		frame.add(panel);

		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		panel.startGameThread();
		ImageIcon img = new ImageIcon("Mario_Series_Logo.svg-removebg-preview.png");
		frame.setIconImage(img.getImage());
	}

	protected static void runApp(int i) throws FileNotFoundException, InvalidLevelFormatException {
		if(level>=2) {
			level=2;
		}
		if(level<=1) {
			level=1;
		}
		System.out.println(level);
		panel.loadfile(level);
	}

}
