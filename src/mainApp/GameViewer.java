package mainApp;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class GameViewer {
	
	public void main() {
	JFrame frame = new JFrame();
	frame.setSize(500, 500);
	frame.setTitle("The Best Game in The World");
	frame.setLocation(-10,0);
	frame.setSize(1900,1900);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ImageIcon img = new ImageIcon("Jetpack-Joyride.jpg");
	frame.setIconImage(img.getImage());
	
	GameComponent gameComponent = new GameComponent();
	frame.add(gameComponent);
	}
	
	public static void main(String[] args) {
		GameViewer GameViewer = new GameViewer();
		GameViewer.main();
	}

}
