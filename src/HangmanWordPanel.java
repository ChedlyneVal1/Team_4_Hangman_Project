import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;



public class HangmanWordPanel {
	private JPanel wordPanel;
	private JLabel[] charLabel;
	private JLabel[] dashLabel;
	private HangmanFrame hmf;
	private String currentWord;
	private int numLetters = 0;
	private int trackSpaces = 0;
	private ArrayList<String> words;
	private ArrayList<Character> letters;
	private ArrayList<Character> guessedLetters;
	private ArrayList<Character> correctlyGuessedLetters;
	private ArrayList<String> incorrectLetters;
	private ArrayList<Boolean> correctLetters;
	private JTextArea dashTextArea;

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
		makeWordArray();
	}

	/**
	 * A method to initialize the JPanels, JLabels and JTextAreas associated with the HangmanWordPanel class.
	 */
	private void init()
	{
		font = new Font("Serif", Font.BOLD, 40);


		letters = new ArrayList<Character>();
		incorrectLetters = new ArrayList<String>();
		guessedLetters = new ArrayList<Character>();
		correctLetters = new ArrayList<Boolean>();
		words = new ArrayList<String>();
		correctlyGuessedLetters = new ArrayList<Character>();

		wordPanel = new JPanel();
		wordPanel.setLayout(null);
		wordPanel.setBackground(Color.LIGHT_GRAY);
		wordPanel.setBounds(350, 50, 450, 350);
		wordPanel.setVisible(true);

		dashTextArea = new JTextArea();
		dashTextArea.setBounds(50, 25, 350, 50);
		dashTextArea.setBackground(Color.LIGHT_GRAY);
		wordPanel.add(dashTextArea);
		dashTextArea.setText("Guess this word or phrase:");
		dashTextArea.setFont(new Font("Serif", Font.BOLD, 20));
		dashTextArea.setEditable(false);
		dashTextArea.setVisible(true);



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
		return this.currentWord;
	}

	/**
	 * A method to set a new word to be guessed.
	 * @param word A String containing the new word to be guessed.
	 */
	public void setWord(String word) {
		this.currentWord = word;
		makeWordArray();
	}


	/**
	 * A method that returns the length of the currently selected word.
	 * @return An int containing the length of the currently selected word.
	 */
	private int getWordLength() {
		return this.currentWord.length();
	}

	/**
	 * A method to return the correctly guessed letters.
	 * @return An array containing the all correctly guessed letters.
	 */
	public ArrayList<Character> getCorrectGuesses() {

		return this.correctlyGuessedLetters;
	}

	public ArrayList<String> getIncorrectGuesses() {

		return this.incorrectLetters;
	}
	
	public void setIncorrectGuesses(ArrayList<String> incorrectGuesses) {

		this.incorrectLetters = incorrectGuesses;
	}
	
	/**
	 * A method to convert a phrase into a ArrayList<String>, and to initialize the values for the associated correctLetters ArrayList<Character>.
	 */
	private void makeWordArray() {
		letters.clear();
		correctLetters.clear();

		String[] arrayOfWords = currentWord.trim().split(" +");

		for (int i = 0; i < arrayOfWords.length; i++)
		{
			words.add(arrayOfWords[i]);
			numLetters  = numLetters + arrayOfWords[i].length();
		}

		charLabel = new JLabel[numLetters]; // Create empty array of labels
		dashLabel = new JLabel[numLetters]; // Create empty array of labels

		for (int i = 0; i <= numLetters - 1; i++){
			dashLabel[i] = new JLabel();
			charLabel[i] = new JLabel();
			
			this.correctLetters.add(false);
		}

		formatLabels();

		for (char i : this.currentWord.toCharArray()) {
			this.letters.add(i);
		}
	}


	private void displayGuessedLetters() {
		//TODO
	}

	/**
	 * A method to draw the dashes for the current phrase and to create the labels for the anwser
	 */
	private void formatLabels()
	{

		int myLetterCnt = 0;
		//       int wordCnt = 0;
		int xPos = 30;
		int yPos = 100;
		int width = 25;
		int height = 65;

		this.hmf.setVisible(true);
		trackSpaces = 11;
		int row = 1;

		for (int l = 0; l < words.size(); l++)
		{
			if (words.get(l).length() <= trackSpaces) {
				trackSpaces -= words.get(l).length();
			}else if ((words.get(l).length() > trackSpaces))
				{
					if (row == 1) {
						yPos +=60;
						xPos = 40;
						row = row + 1;
						trackSpaces = 12;
						trackSpaces -= words.get(l).length();
					}else{
						yPos +=60;
						xPos = 50;
						row = row + 1;
						trackSpaces = 12;
						trackSpaces -= words.get(l).length();
					}
				}

				for (int k = 0; k < words.get(l).length(); k++)
			{
				charLabel[myLetterCnt].setText(" ");
				charLabel[myLetterCnt].setFont(new java.awt.Font("Dialog", 1, 36)); //36
				charLabel[myLetterCnt].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				charLabel[myLetterCnt].setVerticalAlignment(javax.swing.SwingConstants.TOP);
				charLabel[myLetterCnt].setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
				charLabel[myLetterCnt].setVerifyInputWhenFocusTarget(false);
				charLabel[myLetterCnt].setVerticalTextPosition(javax.swing.SwingConstants.TOP);
				charLabel[myLetterCnt].setBounds(xPos, yPos+10, width, height);
				charLabel[myLetterCnt].setVisible(false);
				charLabel[myLetterCnt].setVisible(true);


				hmf.add(charLabel[myLetterCnt]);
				dashLabel[myLetterCnt].setText("_");
				dashLabel[myLetterCnt].setFont(new java.awt.Font("Dialog", 1, 36)); //36
				dashLabel[myLetterCnt].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				dashLabel[myLetterCnt].setVerticalAlignment(javax.swing.SwingConstants.TOP);
				dashLabel[myLetterCnt].setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
				dashLabel[myLetterCnt].setVerifyInputWhenFocusTarget(false);
				dashLabel[myLetterCnt].setVerticalTextPosition(javax.swing.SwingConstants.TOP);
				dashLabel[myLetterCnt].setBounds(xPos, yPos, width, height);
				dashLabel[myLetterCnt].setVisible(false);
				dashLabel[myLetterCnt].setVisible(true);


				wordPanel.add(dashLabel[myLetterCnt]);
				wordPanel.add(charLabel[myLetterCnt]);

				xPos += 30;
				myLetterCnt++;
			}
			xPos += 20;

		}

		hmf.setVisible(false);
		hmf.setVisible(true);
	}
	
	public boolean checkWinCondition() {
		if(this.correctLetters.contains(false)) {
			return false;
		}
		return true;
	}

	/**
	 * A method to check if the guess is correct and sets the letter in its correct location
	 */
	public void checkGuess(String aGuess)
	{
		boolean isCorrect = false;
		int ltrIdx = 0;
		String usedLetters = "";
		String charStr;
		boolean alreadyGuessed = false;
		if(guessedLetters.contains(aGuess.charAt(0))){
			JOptionPane.showMessageDialog(this.hmf, "Letter " + aGuess + " has already been guessed!");
			alreadyGuessed = true;
		}
		else {
			guessedLetters.add(aGuess.charAt(0));
			usedLetters = usedLetters + " " + aGuess;
		}

		if (!alreadyGuessed) {
			for (int i = 0; i < words.size(); i++)
			{
				for (int j = 0; j < words.get(i).length(); j++)
				{
					charStr = words.get(i).substring(j, j + 1);
	
	
					if (aGuess.equalsIgnoreCase(charStr))
					{
						charLabel[ltrIdx].setText(charStr);
						int x = charLabel[ltrIdx].getX();
						int y = charLabel[ltrIdx].getY();
						int w = charLabel[ltrIdx].getWidth();
						int h = charLabel[ltrIdx].getHeight();
						charLabel[ltrIdx].setFont(new Font("Dialog", 1, 22));
						charLabel[ltrIdx].setBounds(x, y, w, h);
						if(!isCorrect)
							correctlyGuessedLetters.add(aGuess.charAt(0));
						isCorrect = true;
						correctLetters.set(ltrIdx, true);
					}
								
					ltrIdx++;
				}
			}
			
			if(!isCorrect) {
				incorrectGuessCheck(aGuess);
			}
		}
	}
	
	private void incorrectGuessCheck(String s) {
		if (!incorrectLetters.stream().anyMatch(str -> str.equals(s.toLowerCase())) && checkLetterBounds(s))
		{
			incorrectLetters.add(s.toLowerCase());
			Collections.sort(incorrectLetters, String.CASE_INSENSITIVE_ORDER);
			this.hmf.updateIncorrectGuesses(incorrectLetters);
			
			hmf.updateHangman();
		}
	}
	
	private boolean checkLetterBounds(String s) {
		if (s.toLowerCase().toCharArray()[0] >= 'a' && s.toLowerCase().toCharArray()[0] <= 'z') {
			return true;
		}
		return false;
	}
}



