package GameStates;

import java.io.*;

public class Save{

public static void save(){

// Create some data objects for us to save.
int currentlvl = GameStateManager.currentState;

try{  // Catch errors in I/O if necessary.
// Open a file to write to, named SavedObj.sav.
FileOutputStream saveFile=new FileOutputStream("SaveObj.sav");

// Create an ObjectOutputStream to put objects into save file.
ObjectOutputStream save = new ObjectOutputStream(saveFile);

// Now we do the save.
save.writeObject(currentlvl);

// Close the file.
save.close(); // This also closes saveFile.
}
catch(Exception exc){
exc.printStackTrace(); // If there was an error, print the info.
}
}
}