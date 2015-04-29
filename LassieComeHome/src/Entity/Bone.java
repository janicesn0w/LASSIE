package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Audio.Audio;
import GameStates.*;
import Main.MainWindow;
import Map.TilesMap;

@SuppressWarnings("serial")
public class Bone extends Objects {
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1};
	
	public Bone(TilesMap tilemap){
		super(tilemap);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
				
		facingRight = true;
				
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream
					("/Sprites/Player/bone.jpg")
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 1; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 0) {
						bi[j] = spritesheet.getSubimage(j * width,i * height,width,height);
					}
					else {
						bi[j] = spritesheet.getSubimage(j * width * 2,i * height,width,height);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void draw (Graphics2D g) {
		setMapPosition();
		
		g.drawImage(				
				animation.getImage(),
				-350,
				-350,
				null);
		
	}
	
	public void update() {
		setPosition(xtemp, ytemp);
		
		width = 30;
		
		
		
		animation.update();
	}
	

}
