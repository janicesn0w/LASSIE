package GameStates;

import java.awt.Graphics2D;

public class GameStateManager {
	
	public static GameState[] gameStates;
	public static int currentState;
	
	public static final int numStates = 2;
	public static final int MENU = 0;
	public static final int LEVEL1 = 1;
	
	public GameStateManager() {
		
		gameStates = new GameState[numStates];
		
		currentState = MENU;
		loadState(currentState);	
	}
	
	public static void unloadState(int state){
		gameStates[state] = null;
	}
	
	public static void loadState(int state){
		if (state == MENU) gameStates[state] = new Menu();
		if (state == LEVEL1) gameStates[state] = new Level1();
	}
	
	public static void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void update() {
		try{
			gameStates[currentState].update();
		}catch(Exception e){}
	}
	
	public void draw(Graphics2D g) {
		try{
			gameStates[currentState].draw(g);
		}catch(Exception e){} 
	}
	
	public static int getState(){ return currentState;}
	
	public void keyPressed(int p) {
		gameStates[currentState].keyPressed(p);
	}
	
	public void keyReleased(int r) {
		gameStates[currentState].keyReleased(r);
	}
}
