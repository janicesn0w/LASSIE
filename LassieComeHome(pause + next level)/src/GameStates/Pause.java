package GameStates;

import javax.swing.JOptionPane;

public abstract class Pause extends GameState{
	
	public static void pauseMenu(){
		Object[] options = {"Resume","Restart", "Quit"};
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
		break;
		
		case 1:
			GameStateManager.setState(GameStateManager.currentState);
			break;
		case 2:
			System.exit(0);
			break;
	}
	}
	

}
