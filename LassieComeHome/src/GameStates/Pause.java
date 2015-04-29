package GameStates;

import javax.swing.JOptionPane;

import Entity.HUD;
import Main.GameLoop;

public abstract class Pause extends GameState{
	
	GameStateManager gsm;
	
	@SuppressWarnings("deprecation")
	public static void pauseMenu(){
		switch (GameStateManager.currentState) {
		
		case 2:
		Level1.bgMusic.stop();
		break;
		
		case 3:
		Level2.bgMusic.stop();
		break;
		
		case 4:
		Level3.bgMusic.stop();
		break;
		
		case 5:
		Level4.bgMusic.stop();
		break;
		
		case 6:
		Level5.bgMusic.stop();
		break;
		}
		HUD.timer.stop();
		GameLoop.thread.suspend();
		Object[] options = {"Resume","Restart", "Main Menu"};
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
			GameLoop.thread.resume();
			break;
		
		case 1:
			HUD.timer.stop();
			GameStateManager.setState(GameStateManager.currentState);
			break;
		case 2:
			GameLoop.thread.resume();
			GameStateManager.setState(GameStateManager.MENU);
			switch (GameStateManager.currentState) {
			
			case 0:
			Level1.bgMusic.stop();
			Level2.bgMusic.stop();
			Level3.bgMusic.stop();
			Level4.bgMusic.stop();
			Level5.bgMusic.stop();
			break;
			}
			break;
	}
	}
	

}
