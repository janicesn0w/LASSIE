package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {
	
	private Dog player;
	
	private BufferedImage image;
	private Font font;
	
	public HUD(Dog p) {
		player = p;
		try {
			image = ImageIO.read(
					getClass().getResourceAsStream("/HUD/hud.gif")
					);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void draw(Graphics2D g) {
		
		g.setFont(font);
		g.drawString("Scores: ", 250, 10);
	}

}
