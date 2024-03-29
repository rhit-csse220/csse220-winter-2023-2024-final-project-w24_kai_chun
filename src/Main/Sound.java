package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[6];

	public Sound() {
		soundURL[0] = getClass().getResource("/sound/smb_coin.wav");
		soundURL[1] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[2] = getClass().getResource("/sound/smb_gameover.wav");
		soundURL[3] = getClass().getResource("/sound/smb_mariodie.wav");
		soundURL[4] = getClass().getResource("/sound/smb_warning.wav");
		soundURL[5] = getClass().getResource("/sound/smb_1-up.wav");

	}
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void play() {
		clip.start();
		
	}
	public void loop(){
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void  stop() {
		clip.stop();
	}
}
