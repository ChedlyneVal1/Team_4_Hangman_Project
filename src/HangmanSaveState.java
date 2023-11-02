import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HangmanSaveState implements Serializable {

	private static String FILENAME = "Hangman.ser";
	
	//word
	private String prevWord;
	//number of guesses
	private int numOfGuesses;
	//letters guessed
	//hangman state
	
	public String getPrevWord() {return prevWord;}
	
	public void setPrevWord(String inWord) {prevWord = inWord;}
	
	public void saveGameState() {
		try {
			removeSaveFile();
			FileOutputStream file = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Save the file
            out.writeObject(this);
            
            out.close();
            file.close();
		} catch (Exception ex) {
			System.out.println("Exception was caught");
		}
	}
	
	public void loadGameState() {
		try {
			FileInputStream file = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(file);
             
            // Save the file
			HangmanSaveState inCopy = (HangmanSaveState)in.readObject();
			
			in.close();
            file.close();
            
            copyGameState(inCopy);
            
            //removeSaveFile();
		} catch (Exception ex) {
			System.out.println("Exception was caught");
		}
	}
	
	private void copyGameState(HangmanSaveState copy) {
		this.setPrevWord(copy.getPrevWord());
	}
	
	private void removeSaveFile() {
		File f = new File(FILENAME);
		if(f.exists()) {
			f.delete();
		}
	}
	
	public boolean isPrevSavedGame() {
		File f = new File(FILENAME);
		return f.exists();
	}
	
	public void save(String word) {
		prevWord = word;
	}
	
	public void cleanup() {
		removeSaveFile();
	}
}
