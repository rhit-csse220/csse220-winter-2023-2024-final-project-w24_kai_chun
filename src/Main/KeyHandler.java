package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean startGame=false;
	public boolean upPressed;
	public int level=1;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP)
		{
			upPressed = true;
		}
		if(code == KeyEvent.VK_ENTER) {
//			System.out.println("kkk");
			startGame = true;
		}
		if(e.getKeyCode()==85) {
//    		runApp(levelNumb+1);
//			panel.loadfile(panel.getlevel());
			level++;
    	}
    	if(e.getKeyCode()==68) {
//    		runApp(levelNumb-1);
    		level--;
    	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP)
		{
			upPressed = false;
		}
	}

}
