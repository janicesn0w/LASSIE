package GameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import Audio.Audio;
import Entity.Dog;
import Entity.HUD;
import File.Save;
import Main.GameLoop;
import Map.Background;
import Map.TilesMap;

public class Level2 extends GameState{

	private TilesMap tilemap;
	
	private Background bg;
	
	private HUD hud;
	
	private Dog Lassie;
		
	public Level2()
	{
		init();
	}	
	//load the map, background image, music and the dog
	public void init()
	{
		tilemap = new TilesMap(30);
		tilemap.loadTiles("/Tilesets/tileset2.gif");
		tilemap.loadMap("/Maps/level2.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(1);
		
		bg = new Background("/Backgrounds/level2.jpeg", 0.1);
		
		Lassie = new Dog(tilemap);
		Lassie.setPosition(100, 100);
		
		Audio.music[GameStateManager.currentState-2].play();
		
		hud = new HUD();
		//save file automatically
		if (Level.lastState < GameStateManager.currentState)	Save.save();
	}

	//update the status of the dog and redraw its position
	public void update() 
	{
		Lassie.update();
		tilemap.setPosition(GameLoop.WIDTH / 2 - Dog.getx(),
			GameLoop.HEIGHT / 2 - Dog.gety());

	}
	//draw the background image,map,dog and timer
	public void draw(Graphics2D g) 
	{
		bg.draw(g);
		tilemap.draw(g);
		Lassie.draw(g);
		
		hud.draw(g,Color.BLACK);
	}
	//keys to move the dog and pause the game
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
		}		
	}
	
	@SuppressWarnings("deprecation")
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
		case KeyEvent.VK_P:
			if (GameStateManager.currentState > 1){
				Audio.music[GameStateManager.currentState-2].stop();
				HUD.timer.stop();
				GameLoop.thread.suspend();
				Object[] options = {"Resume","Restart","Main Menu", "Quit"};
				int n = JOptionPane.showOptionDialog(null,
						"Paused",
						"Lassie",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null,     
						options, 
						options[0]);
		
			switch (n){
			case 0:
				HUD.timer.start();
				Audio.music[GameStateManager.currentState-2].resume();
				GameLoop.thread.resume();
				break;
			case 1:
				GameStateManager.setState(GameStateManager.currentState);
				GameLoop.thread.resume();
				break;
			case 2:
				GameStateManager.setState(GameStateManager.MENU);
				GameLoop.thread.resume();
				break;
			case 3:
				System.exit(0);
				break;
			}
			}
			break;
		}
	}

}
