package GameStates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Audio.Audio;
import Entity.Dog;
import Entity.HUD;
import Main.GameLoop;
import Map.Background;
import Map.TilesMap;

public class Level1 extends GameState {
		
	public static int level=1;
	private TilesMap tilemap;
	private Background bg;
	private HUD hud;
	private Dog Lassie;
	private Audio bgMusic;
	
	
	public Level1(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}	
	
	public void init()
	{
		tilemap = new TilesMap(30);
		tilemap.loadTiles("/Tilesets/lassietileset.gif");
		tilemap.loadMap("/Maps/ver1.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(1);
		
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
		
		Lassie = new Dog(tilemap);
		Lassie.setPosition(100, 200);
		
		bgMusic = new Audio("/Music/level1.mp3");
		bgMusic.play();
		
		hud = new HUD(Lassie);
	}

	
	public void update() 
	{
		Lassie.update();
		tilemap.setPosition(GameLoop.WIDTH / 2 - Dog.getx(),
			GameLoop.HEIGHT / 2 - Dog.gety());
		
		//set background scrolling
		bg.setPosition(tilemap.getxpos(),  tilemap.getypos());
		

	}

	public void draw(Graphics2D g) 
	{
		bg.draw(g);
		tilemap.draw(g);
		Lassie.draw(g);
		
		hud.draw(g);
	}
	
	public void keyPressed(int p)
	{
		switch(p){
		case KeyEvent.VK_UP:
			Lassie.setJump(true);
			break;
		case KeyEvent.VK_DOWN:
			Lassie.setDown(true);
			break;
		case KeyEvent.VK_LEFT:
			Lassie.setLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			Lassie.setRight(true);
			break;
		case KeyEvent.VK_E:
			Lassie.setGliding(true);
		}		
	}
	
	public void keyReleased(int r)
	{
		switch(r){
		case KeyEvent.VK_UP:
			Lassie.setJump(false);
			break;
		case KeyEvent.VK_DOWN:
			Lassie.setDown(false);
			break;
		case KeyEvent.VK_LEFT:
			Lassie.setLeft(false);
			break;
		case KeyEvent.VK_RIGHT:
			Lassie.setRight(false);
			break;
		case KeyEvent.VK_E:
			Lassie.setGliding(false);
			break;
		}
	}
	
	public int getLevel(){ return level=1;}

}
