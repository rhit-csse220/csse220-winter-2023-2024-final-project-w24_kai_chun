package Main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setTitle("The Best Game in The World");
//		frame.setLocation(-10, 0);
		frame.setResizable(false);
		frame.setSize(1600, 920);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		panel.loadfile(1);
		frame.add(panel);
		
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		panel.startGameThread();
		ImageIcon img = new ImageIcon("Mario_Series_Logo.svg-removebg-preview.png");
		frame.setIconImage(img.getImage());
	}

}
