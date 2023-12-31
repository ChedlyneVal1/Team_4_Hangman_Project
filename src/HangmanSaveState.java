import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class HangmanSaveState implements Serializable {

	private static String FILENAME = "Hangman.ser";
	
	//word
	private String prevWord;
	//number of guesses
	private int numOfGuesses;
	//letters guessed
	private ArrayList<Character> correctlyGuessedLetters;
	//letters guessed
	private ArrayList<String> incorrectlyGuessedLetters;
	// difficulty setting
	private int difficulty;
	// theme setting
	private WordGeneration.Theme theme;
	
	
	// Getters and Setters
	
	public String getPrevWord() {return prevWord;}
	
	private void setPrevWord(String inWord) {prevWord = inWord;}
	
	public int getNumOfGuesses() {return numOfGuesses;}
	
	private void setNumOfGuesses(int guesses) {numOfGuesses = guesses;}

	public ArrayList<Character> getCorrectlyGuessedLetters() {return correctlyGuessedLetters;}
	
	private void setCorrectlyGuessedLetters(ArrayList<Character> correctGuesses) {correctlyGuessedLetters = correctGuesses;}
	
	public ArrayList<String> getIncorrectlyGuessedLetters() {return incorrectlyGuessedLetters;}
	
	private void setIncorrectlyGuessedLetters(ArrayList<String> incorrectGuesses) {incorrectlyGuessedLetters = incorrectGuesses;}
	
	public int getDifficulty() {return difficulty;}
	
	private void setDifficulty(int inDifficulty) {difficulty = inDifficulty;}
	
	public WordGeneration.Theme getTheme() {return theme;}
	
	private void setTheme(WordGeneration.Theme inTheme) {theme = inTheme;}
	
	// End of Getters and Setters
	
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
			System.out.println(ex.getMessage());
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
		save(copy.getPrevWord(), copy.getCorrectlyGuessedLetters(), copy.getIncorrectlyGuessedLetters(),
				copy.getDifficulty(), copy.getTheme());
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
	
	public void save(String word, ArrayList<Character> correctGuesses, ArrayList<String> incorrectGuesses,
			int savedDiff, WordGeneration.Theme savedTheme) {
		setPrevWord(word);
		setNumOfGuesses(correctGuesses.size() + incorrectGuesses.size());
		setCorrectlyGuessedLetters(correctGuesses);
		setIncorrectlyGuessedLetters(incorrectGuesses);
		setDifficulty(savedDiff);
		setTheme(savedTheme);
	}
	
	public void cleanup() {
		removeSaveFile();
	}
}
