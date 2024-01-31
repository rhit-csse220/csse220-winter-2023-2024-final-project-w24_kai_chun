package mainApp;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameViewer {

	public void run() {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("The Best Game in The World");
		frame.setLocation(-10, 0);
		frame.setResizable(false);
		frame.setSize(1550, 920);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("Jetpack-Joyride.jpg");
		frame.setIconImage(img.getImage());

		GameComponent gameComponent = new GameComponent();
		frame.add(gameComponent);
		
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GameViewer GameViewer = new GameViewer();
		GameViewer.run();
		//GameComponent gamecomponet = new GameComponent();
		//gamecomponet.repaint();
//		LevelLoader loader = new LevelLoader();
//		try {
//			char[][] level = loader.loadLevel("Level1.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}
