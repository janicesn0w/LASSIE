package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Audio.Audio;
import Entity.Dog;
import Entity.HUD;
import File.Save;
import Main.GameLoop;
import Map.Background;
import Map.TilesMap;

public class Level3 extends GameState{

	private TilesMap tilemap;
	
	private Background bg;
	
	private HUD hud;
	
	private Dog Lassie;
	
	public Level3()
	{
		init();
	}	
	
	public void init()
	{
		tilemap = new TilesMap(30);
		tilemap.loadTiles("/Tilesets/tileset3.gif");
		tilemap.loadMap("/Maps/level3.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(1);
		
		bg = new Background("/Backgrounds/level3.jpg", 0.1);
		
		Lassie = new Dog(tilemap);
		Lassie.setPosition(100, 100);
		
		Audio.music[GameStateManager.currentState-2].play();
		
		hud = new HUD();
	
		if (Level.lastState < GameStateManager.currentState)	Save.save();
	}

	
	public void update() 
	{
		Lassie.update();
		tilemap.setPosition(GameLoop.WIDTH / 2 - Dog.getx(),
			GameLoop.HEIGHT / 2 - Dog.gety());

	}

	public void draw(Graphics2D g) 
	{
		bg.draw(g);
		tilemap.draw(g);
		Lassie.draw(g);
		
		hud.draw(g,Color.WHITE);
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

}
