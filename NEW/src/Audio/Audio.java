package Audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio{
	
	private Clip clip;
	
	private int lastFrame;
	
	public static boolean playing = false;
	
	private static Audio level1 = new Audio("/Music/level1.mp3");
	private static Audio level2 = new Audio("/Music/level2.mp3");
	private static Audio level3 = new Audio("/Music/level3.mp3");
	private static Audio level4 = new Audio("/Music/level1.mp3");
	private static Audio level5 = new Audio("/Music/level1.mp3");
	public static Audio[] music = {level1,level2,level3,level4,level5};
	
	public Audio(String path) {
		
		try {
			
			AudioInputStream a =
				AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
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
	
	public void play() {
		if(clip == null) return;
		stop();
		playing = true;
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		if(clip.isRunning()){
			clip.stop();
			lastFrame = clip.getFramePosition();
		}
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
	public void resume(){
		clip.setFramePosition(lastFrame);
		clip.start();
	}
	
}

