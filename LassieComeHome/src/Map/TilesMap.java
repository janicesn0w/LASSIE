package Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Entity.Bone;
import Entity.Dog;
import Main.GameLoop;
import Map.Tile;

public class TilesMap {
	
	Dog Lassie;
	Bone bone;
	
	private double xpos;
	private double ypos;
	
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;
	
	private double tween;
	
	private int[][] map;
	private int tileSize;
	private int numRow;
	private int numCol;
	private int width;
	private int height;
	
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TilesMap(int tilesize)
	{
		this.tileSize = tilesize;
		numRowsToDraw = GameLoop.HEIGHT / tileSize + 2;
		numColsToDraw = GameLoop.WIDTH / tileSize + 2;
		tween = 0.07;
	}

	public void draw(Graphics2D g) 
	{
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++){
				if(row >= numRow) break;
					for(int col = colOffset; col < colOffset + numColsToDraw; col++) {
						if(col >= numCol) break;
						if(map[row][col] == 0) continue;
					
						int rc = map[row][col];
						int r = rc / numTilesAcross;
						int c = rc % numTilesAcross;
					
						g.drawImage(tiles[r][c].getImage(),
								(int)xpos + col * tileSize, 
								(int)ypos + row * tileSize, 
								null);
					}
		}
	}

	public void loadTiles(String path) 
	{
		try {

			tileset = ImageIO.read(getClass().getResourceAsStream(path));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimg;
			for(int col = 0; col < numTilesAcross; col++) {
				subimg = tileset.getSubimage(col * tileSize, 0,	tileSize, tileSize);
				tiles[0][col] = new Tile(subimg, Tile.NORMAL);
				subimg = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimg, Tile.BLOCKED);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public void loadMap(String path)
	{
		try {
			InputStream in = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numCol = Integer.parseInt(br.readLine());
			numRow = Integer.parseInt(br.readLine());
			map = new int[numRow][numCol];
			width = numCol * tileSize;
			height = numRow * tileSize;
			
			xMin = GameLoop.WIDTH - width;
			xMax = 0;
			yMin = GameLoop.HEIGHT - height;
			yMax = 0;
			
			String delims = "\\s+";
			for(int row = 0; row < numRow; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCol; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getType(int row, int col) 
	{
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y) 
	{
		System.out.println(this.xpos);
		System.out.println((x - this.xpos) * tween);
		
		this.xpos += (x - this.xpos) * tween;
		this.ypos += (y - this.ypos) * tween;
		
		System.out.println(this.xpos + "\n==========");
		
		fixBounds();
		
		colOffset = (int)-this.xpos / tileSize;
		rowOffset = (int)-this.ypos / tileSize;	
	}

	public void setTween(int i) 
	{
		this.tween = i;
	}
	
	private void fixBounds() 
	{
		if(xpos < xMin) xpos = xMin;
		if(ypos < yMin) ypos = yMin;
		if(xpos > xMax) xpos = xMax;
		if(ypos > yMax) ypos = yMax;
	}

	public double getxpos() { return xpos;}
	public double getypos() { return ypos;}

	public int getTileSize() { return tileSize;	}
	public int getRow(){ return numRow;}
	public int getCol(){ return numCol;}
	
}
