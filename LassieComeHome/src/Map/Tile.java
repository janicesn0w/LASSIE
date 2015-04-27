package Map;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage img;
	private int type;
	
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public Tile(BufferedImage subimg, int type) 
	{
		this.img = subimg;
		this.type = type;
	}
	
	public BufferedImage getImage() { return img;}
	public int getType() { return type;}

}
