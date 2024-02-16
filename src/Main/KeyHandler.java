package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean startGame=false;
	public boolean upPressed;
	public int level=1;
	public boolean restartTheGame=true;
	public boolean newGame;
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
		if(code == KeyEvent.VK_R) {
			restartTheGame=false;
			this.newGame = true;
			
		}
		if(code == KeyEvent.VK_ENTER) {
			startGame = true;
		}
		if(e.getKeyCode()==85) {
			level++;
    	}
    	if(e.getKeyCode()==68) {
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
