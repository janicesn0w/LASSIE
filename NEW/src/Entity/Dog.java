package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Audio.Audio;
import GameStates.GameStateManager;
import Main.MainWindow;
import Map.TilesMap;

@SuppressWarnings("serial")
public class Dog extends Objects{
	
	private Audio sfx;
	private boolean flinching;
	private long flinchTimer;
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {2, 8, 1, 2, 4, 2, 5};
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	
	public Dog(TilesMap tilemap)
	{
		super(tilemap);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		glideSpeed = 0.8;
				
		facingRight = true;
				
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream
					("/Sprites/Player/Lassie.gif")
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 6) {
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
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
		sfx = new Audio("/SFX/jump.mp3");
	}

	public void draw(Graphics2D g) {
		setMapPosition();
		
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		if(facingRight) {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2),
				(int)(y + ymap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2 + width),
				(int)(y + ymap - height / 2),
				-width,
				height,
				null
			);
			
		}
		
	}
	
	public void move()
	{
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		//jumping
		if(jumping && !falling) {
			dy = jumpStart;
			falling = true;
			sfx.play();
		}
		
		// falling
		if(falling) {
			
			if(dy > 0) dy += fallSpeed;
			else dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
	}
	
	public void checktimeUp(){
		if (timeUp){
			Audio.music[GameStateManager.currentState-2].stop();
			Audio die = new Audio("/SFX/die.mp3");
			die.play();
			Object[] options = {"Restart","Main Menu", "Quit"};
			int n = JOptionPane.showOptionDialog(null,
				    "Time's up!!!!!",
				    "You Need To Be Faster",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.INFORMATION_MESSAGE,
				    null,     
				    options, 
				    options[0]);
			
			switch (n){
			case 0:
				GameStateManager.setState(GameStateManager.currentState);
				break;
			case 1:
				GameStateManager.setState(GameStateManager.MENU);
				break;
			case 2:
				System.exit(0);
				break;
			}
		}
	}
	
	public void checkDead(){
		if (dead) {
			Audio.music[GameStateManager.currentState-2].stop();
			HUD.timer.stop();
			Audio die = new Audio("/SFX/die.mp3");
			die.play();
			Object[] options = {"Restart","Main Menu", "Quit"};
			int n = JOptionPane.showOptionDialog(null,
				    "You Killed LASSIE!",
				    "Oh No",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.INFORMATION_MESSAGE,
				    null,     
				    options, 
				    options[0]);
			
			switch (n){
			case 0:
				GameStateManager.setState(GameStateManager.currentState);
				break;
			case 1:
				GameStateManager.setState(GameStateManager.MENU);
				break;
			case 2:
				System.exit(0);
				break;
			
			}
			
		}
	}
		
	public void checkLevel(){
		if (nextLevel){
			Audio.music[GameStateManager.currentState-2].stop();
			HUD.timer.stop();
			Audio nextlevel = new Audio("/SFX/nextlevel.mp3");
			nextlevel.play();
			if (GameStateManager.currentState != 6){
				Object[] options = {"Next level","Restart","Main Menu","Quit"};
				int n = JOptionPane.showOptionDialog(MainWindow.window,
						"Keep Up!! You are one step closer to your home.",
						"Lassie",
						JOptionPane.CLOSED_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null,     
						options, 
						options[0]);
			
				switch (n){
				case 0:
					GameStateManager.setState(GameStateManager.currentState+1);
					break;
				case 1:
					GameStateManager.setState(GameStateManager.currentState);
					break;
				case 2:
					GameStateManager.setState(GameStateManager.MENU);
					break;
				case 3:
					System.exit(0);
					break;
				}
			}
			else{
				Object[] options = {"Restart","Main Menu", "Quit"};
				int n = JOptionPane.showOptionDialog(null,
					    "You reached your home!",
					    "Congratulations!",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.INFORMATION_MESSAGE,
					    null,     
					    options, 
					    options[0]);
				
				switch (n){
				case 0:
					GameStateManager.setState(GameStateManager.currentState);
					break;
				case 1:
					GameStateManager.setState(GameStateManager.MENU);
					break;
				case 2:
					System.exit(0);
					break;
				
				}
			}
		}
	}
		
	public void update() 
	{
		move();
		timeEnd();
		checktimeUp();
		checkDead();
		checkLevel();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dy >0){
			if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}
		
		animation.update();
		
		
		//set direction
		if(right) facingRight = true;
		if(left) facingRight = false;
		
		}
		
}


