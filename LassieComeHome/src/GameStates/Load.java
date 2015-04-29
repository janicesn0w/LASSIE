package GameStates;

import java.io.*;
import java.util.ArrayList;

public class Load{
	
	public static int currentlvl = 0;
	
public static int load(){

// Create the data objects for us to restore.


// Wrap all in a try/catch block to trap I/O errors.
try{
// Open file to read from, named SavedObj.sav.
FileInputStream saveFile = new FileInputStream("SaveObj.sav");

// Create an ObjectInputStream to get objects from save file.
ObjectInputStream save = new ObjectInputStream(saveFile);

// Now we do the restore.
// readObject() returns a generic Object, we cast those back
// into their original class type.
// For primitive types, use the corresponding reference class.
currentlvl = (Integer) save.readObject();

// Close the file.
save.close(); // This also closes saveFile.
}
catch(Exception exc){
exc.printStackTrace(); // If there was an error, print the info.
}

// Print the values, to see that they've been recovered.
System.out.println("\nRestored Object Values:\n");
System.out.println("\tCurrent Level: " + currentlvl);
System.out.println();

return currentlvl;

// All done.
}
}
