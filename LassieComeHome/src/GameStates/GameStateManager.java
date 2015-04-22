package GameStates;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
	
	private static ArrayList<GameState> gameStates;
	private static int currentState;
	
	public static final int MENU = 0;
	public static final int LEVEL1 = 1;
	
	public GameStateManager() {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = MENU;
		gameStates.add(new Menu(this));
		gameStates.add(new Level1(this));
		
	}
	
	public static void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	/*public static void getState(){
		
	}*/
	
	public void keyPressed(int p) {
		gameStates.get(currentState).keyPressed(p);
	}
	
	public void keyReleased(int r) {
		gameStates.get(currentState).keyReleased(r);
	}
}
