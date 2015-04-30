package Audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio{
	
	private Clip clip;
	//to record the last frame of the music to resume
	private int lastFrame;
		
	public static Audio level1 = new Audio("/Music/level1.mp3");	//level1 background music
	public static Audio level2 = new Audio("/Music/level2.mp3");	//level2 background music
	public static Audio level3 = new Audio("/Music/level3.mp3");	//level3 background music
	public static Audio level4 = new Audio("/Music/level4.mp3");	//level4 background music
	public static Audio level5 = new Audio("/Music/level5.mp3");	//level5 background music
	
	public static Audio[] music = {level1,level2,level3,level4,level5};
	
	public Audio(String path) {
		//load music
		try {
			
			AudioInputStream a =
				AudioSystem.getAudioInputStream(getClass().getResource(path));
			AudioFormat baseFormat = a.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream b = AudioSystem.getAudioInputStream(decodeFormat, a);
			clip = AudioSystem.getClip();
			clip.open(b);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//play music
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	//stop music
	public void stop() {
		if(clip.isRunning()){
			clip.stop();
			lastFrame = clip.getFramePosition();
		}
	}
	//close music
	public void close() {
		stop();
		clip.close();
	}
	//resume music 
	public void resume(){
		clip.setFramePosition(lastFrame);
		clip.start();
	}
	//loop music continuously
	public void loop(){
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
}

