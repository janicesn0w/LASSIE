package Audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio{
	
	private Clip clip;
	
	public static boolean playing = false;
	
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
		if(clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
}

