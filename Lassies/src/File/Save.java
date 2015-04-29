package File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import GameStates.GameStateManager;


public class Save {

	private static int currentlvl;
	
	public static void save(){
		
		currentlvl = GameStateManager.currentState;
		try{
		FileOutputStream savefile = new FileOutputStream("currentlevel.sav");
		
		ObjectOutputStream save = new ObjectOutputStream(savefile);
		
		save.writeObject(currentlvl);
		
		save.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
public static void saveSpecified(int state){
		
		currentlvl = state;
		try{
		FileOutputStream savefile = new FileOutputStream("currentlevel.sav");
		
		ObjectOutputStream save = new ObjectOutputStream(savefile);
		
		save.writeObject(currentlvl);
		
		save.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
