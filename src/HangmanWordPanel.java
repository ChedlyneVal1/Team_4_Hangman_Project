import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HangmanWordPanel {
	private JPanel wordPanel;
	private JLabel wordLabel;
	private JLabel dashLabel;
	
	private HangmanFrame hmf;
	
	private String currentWord;
	
	private ArrayList<Character> letters;
	private ArrayList<Character> guessedLetters;
	private ArrayList<Boolean> correctLetters;
	
	private Font font;
	
	/**
	 * A constructor for the HangmanWordPanel class.
	 * @param hmf A HangmanFrame holding the underlying JFrame.
	 */
	HangmanWordPanel(HangmanFrame hmf) {
		this.hmf = hmf;
		init();
	}
	
	/**
	 * A constructor for the HangmanWordPanel class.
	 * @param hmf A HangmanFrame holding the underlying JFrame.
	 * @param word A String containing the first selected word to be guessed.
	 */
	HangmanWordPanel(HangmanFrame hmf, String word) {
		this.hmf = hmf;
		this.currentWord = word;
		init();
		drawDashes();
		makeWordArray();
	}
	
	/**
	 * A method to initialize the JPanels and JLabels associated with the HangmanWordPanel class.
	 */
	private void init() {
		font = new Font("Serif", Font.BOLD, 40);
		
		letters = new ArrayList<Character>();
		guessedLetters = new ArrayList<Character>();
		correctLetters = new ArrayList<Boolean>();
		
		dashLabel = new JLabel();
		dashLabel.setBounds(20, 140, 450, 100);
		dashLabel.setVisible(true);
		dashLabel.setFont(font);
		
		wordLabel = new JLabel();
		wordLabel.setBounds(20, 100, 450, 100);
		wordLabel.setVisible(true);
		wordLabel.setFont(font);
    	
    		wordPanel = new JPanel();
    		wordPanel.setLayout(null);
    		wordPanel.setBackground(Color.LIGHT_GRAY);
    		wordPanel.setBounds(350, 50, 450, 350);
    		wordPanel.setVisible(true);
    		wordPanel.add(dashLabel);
    		wordPanel.add(wordLabel);    	
	}
	
	/**
	 * A method to add the UI to the underlying JFrame contained in the HangmanFrame class.
	 */
	public void addUI() {
		this.hmf.add(wordPanel);
	}
	
	/**
	 * A method to remove the UI from the underlying HangmanFrame class.
	 */
	public void removeUI() {
		this.hmf.remove(wordPanel);
	}
	
	/**
	 * A method to return the currently selected word.
	 * @return A String containing the currently selected word.
	 */
	public String getWord() {
		//TODO call the word class to get a new word.
		
		//TODO delete
		return this.currentWord;
	}
	
	/**
	 * A method to set a new word to be guessed.
	 * @param word A String containing the new word to be guessed.
	 */
	public void setWord(String word) {
		this.currentWord = word;
		this.currentWord = this.currentWord.toLowerCase();
		makeWordArray();
		drawDashes();
	}
	
	/**
	 * A method that returns the length of the currently selected word.
	 * @return An int containing the length of the currently selected word.
	 */
    private int getWordLength() {
    	return this.currentWord.length();
    }
	
	/**
	 * A method to convert the word string to an ArrayList<Character>, and to initialize the values for the associated correctLetters ArrayList<Character>.
	 */
	private void makeWordArray() {
		letters.clear();
		correctLetters.clear();
		for (char i : this.currentWord.toCharArray()) {
			this.letters.add(i);
			this.correctLetters.add(false);
		}
	}
    
    private void displayGuessedLetters() {
    	//TODO
    }
    
    /**
     * A method to display the correct number of dashes based on the length of the current word.
     */
    private void drawDashes() {
    	String dashes = "";
    	
    	for(int i = 0; i < getWordLength(); ++i) {
    		dashes = dashes + "_  ";
    	}
    	
    	dashLabel.setText(dashes);
    }
    
    /**
     * A method to draw the letters that were correctly guessed.
     */
    private void drawCorrectGuesses() {
    	String guess = "";
    	for(int i = 0; i < letters.size(); ++i) {
    		if(correctLetters.get(i) == true) {
    			guess = guess + letters.get(i).toString() + "  ";
    		}
    		else {
    			guess = guess + "   ";
    		}
    	}
    	
    	wordLabel.setText(guess);
    }
    
    /**
     * A method to check if a guessed letter is part of the current word.
     * @param s A String containing the currently guessed letter.
     */
    public void guessLetter(String s) {
    	char currLetter = s.toLowerCase().toCharArray()[0];
    	int position = currentWord.indexOf(currLetter);
    	
    	guessedLetters.add(currLetter);
    	    	
    	if(position != -1) {
    		for(int i = 0; i < letters.size(); ++i) {
    			if(letters.get(i) == currLetter) {
    				correctLetters.set(i, true);
    			}
    		}
    		
    		drawCorrectGuesses();    		
    	}
    	else {
    		//TODO draw the hangman image (in HangmanDrawing).
    	}
    	
    	displayGuessedLetters();
    }
}
