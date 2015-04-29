package GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Audio.Audio;
import Map.Background;

public class Menu extends GameState {
	
	private Background bg;
	
	private Font titleFont;
	private Font menuFont;
	private Color titleColor;
	
	private int currentChoice = 0;
	private String[] menu = {"Start", "Level", "Quit"};
	
	public Menu()
	{
		init();
	}

	
	public void init() {
		try{
			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.animation(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			
			menuFont = new Font("Arial", Font.PLAIN, 12);
			
			Audio.music[GameStateManager.currentState].play();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	public void update() 
	{
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) 
	{
		bg.draw(g);
		
		g.setFont(titleFont);
		g.setColor(titleColor);
		g.drawString("Lassie Come Home", 30, 70);
		
		g.setFont(menuFont);
		for (int counter=0; counter < menu.length; counter++){
			if (counter == currentChoice) g.setColor(Color.GREEN);
			else g.setColor(Color.BLACK);
			g.drawString(menu[counter], 145, 140 + counter * 15);
		}
	}
	
	public void select()
	{
		switch (this.currentChoice){
		case 0: 
			Audio.music[GameStateManager.currentState].stop();
			GameStateManager.setState(GameStateManager.LEVEL1);
			break;
		case 1: 
			GameStateManager.setState(GameStateManager.LEVEL);
			break;
		case 2:
			System.exit(0);
			break;
		}
	}
	
	public void keyPressed(int p)
	{
		if (p == KeyEvent.VK_ENTER)	select();
		if (p == KeyEvent.VK_UP){
			currentChoice--;
			if (currentChoice <0)	currentChoice = menu.length - 1;
		}
		if (p == KeyEvent.VK_DOWN){
			currentChoice++;
			if (currentChoice == menu.length)	currentChoice = 0;
		}	
	}
	
	public void keyReleased(int r){}
}
