package File;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load {

	private static int currentlvl;
	
	@SuppressWarnings("resource")
	public static int load(){
		//load file
		try{
		FileInputStream loadfile = new FileInputStream("currentlevel.sav");
		
		ObjectInputStream load = new ObjectInputStream(loadfile);
		
		currentlvl = (int)load.readObject();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return currentlvl;
	}
	
}
