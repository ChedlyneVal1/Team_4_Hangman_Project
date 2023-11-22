import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class HangmanFrame extends JFrame implements ActionListener {
    private static JPanel gameStartPanel;
    private static JLabel gameStartLabel;
    private static JPanel btnTogglePanel;
    private static JLabel btnToggleLabel;
    private static JButton btnToggle;
    private static JButton btnReplay;
    private static JButton btnExit;
    private static JButton btnOptions;
    private static JTextArea gameInstructions;
	private static JRadioButton phraseButton;
	private static JRadioButton animalButton;
	private static JRadioButton cartoonsButton;
	private static JRadioButton moviesButton;
	private static JRadioButton scienceButton;
	private static JRadioButton sportsButton;
	private static JPanel radioPanel;

    private HangmanDrawing drawing;
    private HangmanInput input;
    private HangmanWordPanel word;
    private WordGeneration wordGen = WordGeneration.getInstance();
    private HangmanSaveState saveState;
    
    private int difficulty = 0; //0 = easy, 1 = medium, 2 = hard


    private ImageIcon image = new ImageIcon("start.png");
    
    private boolean initialStart = true;

    private enum gameState {
    	start,
    	inGame,
    	pause;
    }
    
    private gameState gState;
    
    private enum configState {
    	newGame,
    	replayGame,
    	resumeGame;
    }
    
    public HangmanFrame() {
    	saveState = new HangmanSaveState();
    	if(saveState.isPrevSavedGame()) {
    		saveState.loadGameState();
    	}
    	gState = gameState.start;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getSource().getClass() == btnToggle.getClass()) {
	        if(e.getSource() == btnToggle){
	            if (btnToggle.getText().equals("Play")){
	            	
	            	configState curState = configState.newGame;
	            	
	            	if(initialStart) {
	            		if(saveState.isPrevSavedGame()) {
			            	// Ask player if they would like to replay the last saved game?
			            	if(this.checkPrevSaveState())
			            		curState = configState.resumeGame;
	            		}
		            	initialStart = false;
	            	}
	            	
	                configGameUI(curState);
	                gState = gameState.inGame;
	            } else if (btnToggle.getText().equals("Resume")) {
	            	configGameUI(configState.resumeGame);
	            } else {
	            	gState = gameState.pause;
	            	this.savePrevGame(false);
	            	configMainUI();
	            }
	        } else if (e.getSource() == btnReplay) {
	        	replayPressed();
	        } else if (e.getSource()== btnExit) {
	        	//Ask if the user wants to save where they left off
	        	this.quitPressed();
	        } else if (e.getSource() == btnOptions) {
	        	showOptions();
	        }
    	} else if(e.getSource().getClass() == btnToggle.getClass()) {
    		
    	}

    }
    
    private void showOptions() {
    	Object[] options = {"Difficulty", "Theme"};
        var option = JOptionPane.showOptionDialog(this,
                "Options",
                "Options",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        if(option == 0) {
        	difficultySelection();
        } else {
        	openThemeWindow();
        }
    }
    
    private void difficultySelection() {
    	Object[] options = {"Easy", "Medium", "Hard"};
        difficulty = JOptionPane.showOptionDialog(this,
                "Select Difficulty:\n\nEasy - up to 5 incorrect guesses allowed.\n\nMedium - up to 2 incorrect guesses allowed.\n\nHard - only 1 incorrect guess allowed.",
                "Difficulty Selection",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }


    public static void main(String[] args) throws Exception
    {	
        HangmanFrame hmF = new HangmanFrame();
        hmF.createLabels();
        hmF.createButtons();
        hmF.createPanels();
        hmF.configFrame();
        hmF.createTextArea();
    }


    private void createPanels()
    {
        //panel that appears on start up
        gameStartPanel = new JPanel();
        gameStartPanel.setLayout(null);
        gameStartPanel.setBackground(Color.WHITE);
        gameStartPanel.setBounds(0, 50, 800, 550);
        gameStartPanel.setVisible(true);
        gameStartPanel.add(gameStartLabel);


        //panel that holds the label holding the buttons
        btnTogglePanel = new JPanel();
        btnTogglePanel.setLayout(null);
        btnTogglePanel.setBackground(Color.WHITE);
        btnTogglePanel.add(btnToggle);
        btnTogglePanel.add(btnReplay);
        btnTogglePanel.add(btnOptions);
        btnTogglePanel.add(btnExit);
        btnTogglePanel.setBounds(0, 0, 800, 50);
        btnTogglePanel.setVisible(true);
       
    }


    private void createLabels()
    {
        //label that holds start game image
        gameStartLabel = new JLabel();
        gameStartLabel.setBounds(0, 0, 800, 550);
        gameStartLabel.setVisible(true);
        gameStartLabel.setIcon(image);//image needs to be designed


        //label that will hold the buttons
        //TODO I don't think any of this is necessary
        //btnToggleLabel = new JLabel();
        //btnToggleLabel.setBounds(0, 0, 800, 50);
        //btnToggleLabel.setOpaque(true);
        //btnToggleLabel.setVisible(true);
    }


    private void createButtons(){
    	String playResumeButton = "Play";
    	if(gState == gameState.pause)
    		playResumeButton = "Resume";
    	
        //initiates button that toggles between Play and Back
        btnToggle = new JButton(playResumeButton);
        btnToggle.setBounds(340, 15, 100, 30);
        btnToggle.setVisible(true);
        btnToggle.addActionListener(this);
        
        //initiates button that Replays
        btnReplay = new JButton("Replay");
        btnReplay.setBounds(225, 15, 100, 30);
        btnReplay.setVisible(false);
        btnReplay.addActionListener(this);

        btnExit = new JButton("Exit");
        btnExit.setBounds(570, 15, 100, 30);
        btnExit.setVisible(true);
        btnExit.addActionListener(this);
        
        btnOptions = new JButton("Options");
        btnOptions.setBounds(455, 15, 100, 30);
        btnOptions.setVisible(true);
        btnOptions.addActionListener(this);
    }

    private void createTextArea()
    {
        gameInstructions = new JTextArea();
        gameInstructions.setBounds(25, 450, 800, 100);
        gameStartLabel.add(gameInstructions);
        gameInstructions.setText("Game Instructions: Hangman is a simple word guessing game. Players try to figure out an unknown word \n" +
                "   or phrase by guessing letters. If too many letters, which do not appear in the word are guessed, the\n" +
                "   player is hanged (and loses). ");
        gameInstructions.setFont(new Font("ariel", Font.ITALIC, 14));
        gameInstructions.setEditable(false);
        gameInstructions.setVisible(true);
    }

    private void configFrame()
    {  //configures components on HangmanFrame


        this.setLayout(null);
        this.add(gameStartPanel);
        this.add(btnTogglePanel);
        this.setTitle("Hangman");
        this.setSize(800, 600);

        this.setResizable(false);
        this.setVisible(true);
    }

    private void drawHangman()
    {
    	drawing = new HangmanDrawing(this);
    	drawing.setBounds(0, 50, 350, 550);
        drawing.setVisible(true);
        this.add(drawing);
    }

    
   /**
    * A method to create the other objects needed for the game to function.
    */

    private void createComponents(String inWord, int numOfGuesses) {
		drawHangman();
        word = new HangmanWordPanel(this);
    	input = new HangmanInput(this);
    	
    	if(inWord != "") {
    		word.setWord(inWord);
    	}
    	
    	if(numOfGuesses>0) {
    		for(Character c : saveState.getCorrectlyGuessedLetters()) {
    			word.checkGuess(c.toString());
    		}
    		for(String s : saveState.getIncorrectlyGuessedLetters()) {
    			word.checkGuess(s);
    		}
    	}
    }
    
    public void guessLetter(String s) {
    	word.checkGuess(s);
    }
    

    public boolean checkPrevSaveState() {
    	String resumeQuestion;

    	if(gState == gameState.pause)
			 resumeQuestion = "Would you like to resume your paused game?";
    	else
	         resumeQuestion = "Would you like to resume your previously saved game?";
    	
    	// Create a dialog window asking the user if they want to
    	// resume the previous game.
    	Object[] options = {"Yes",
    	                    "No"};
    	int n = JOptionPane.showOptionDialog(this,
    		resumeQuestion,
    	    "Resume",
    	    JOptionPane.YES_NO_OPTION,
    	    JOptionPane.QUESTION_MESSAGE,
    	    null,
    	    options,
    	    options[1]);  	
    	
		saveState.cleanup();
    	
    	return(n==0);
    }
    
    public void updateIncorrectGuesses(ArrayList<String> s) {
    	input.updateIncorrectGuesses(s);
    }
    
    /**
     * A method to remove the UI elements of the Game Screen and add the UI elements of the Main Screen.
     */
    private void configMainUI() {
    	hideGameUI();
    	addMainUI();
    }
    
    /**
     * A method to remove the UI elements of the Main Screen and add the UI elements of the Game Screen.
     */
    private void configGameUI(configState cfgState) {
    	hideMainUI();
    	
    	String newWord = "";
    	
    	switch(cfgState) {
    		case newGame:
    			newWord = wordGen.genaratedWord();
    			break;
    		case replayGame:
    			newWord = word.getWord();
    			break;
    		case resumeGame:
    			newWord = saveState.getPrevWord();
    			break;
    		default:
    	}

    	int guesses = 0;
    	
    	if (cfgState==configState.resumeGame) {
    		guesses = saveState.getNumOfGuesses();
    		difficulty = saveState.getDifficulty();
    		
    	}
    	
    	createComponents(newWord, guesses);
    	addGameUI();
    }
    
    /**
     * A method to add the UI elements to the Main Screen.
     */
    private void addMainUI() {
    	createLabels();
    	createButtons();
        createPanels();
        createTextArea();
        configFrame();
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     * A method to add the UI elements to the Game Screen.
     */
    private void addGameUI() {
    	drawing.addUI();
    	input.addUI();
    	word.addUI();
    	
    	this.revalidate();
    	this.repaint();
    }
    
    /**
     * A method to remove the UI elements of the Main Screen.
     */
    private void hideMainUI() {
    	this.remove(gameStartPanel);
    	btnToggle.setText("Back");
        btnReplay.setVisible(true);
        btnOptions.setVisible(false);
    }
    
    /**
     * A method to remove the UI elements of the Game Screen.
     */
    private void hideGameUI() {
    	drawing.removeUI();
    	input.removeUI();
    	word.removeUI();
    	
    	this.remove(btnTogglePanel);
        btnReplay.setVisible(false);
        btnOptions.setVisible(true);
        drawing.resetHangman();
    }
    
    
    public void checkWinCondition() {
    	if(word.checkWinCondition()) {
    		win();
    	}
    }
    
    public void win() {
    	// Create a dialog window asking the user if they want to
    	// start over with a new word.
    	Object[] options = {"New word",
    	                    "Same word"};
    	int n = JOptionPane.showOptionDialog(this,
    	    "You won! Play again?",
    	    "You won!",
    	    JOptionPane.YES_NO_CANCEL_OPTION,
    	    JOptionPane.QUESTION_MESSAGE,
    	    null,
    	    options,
    	    options[1]);
    	
    	resetGame(n);
    }
    
    public void showLostScreen() {
    	// Create a dialog window asking the user if they want to
    	// start over with a new word.
    	Object[] options = {"New word",
    	                    "Same word"};
    	int n = JOptionPane.showOptionDialog(this,
    	    "You Lost! Play again?",
    	    "You lost!",
    	    JOptionPane.YES_NO_CANCEL_OPTION,
    	    JOptionPane.QUESTION_MESSAGE,
    	    null,
    	    options,
    	    options[1]);
    	
    	resetGame(n);
    }
    
    private void resetGame(int n) {
    	if(n==0) {
    		// Reset game ui
    		configMainUI();
    		configGameUI(configState.newGame);
    	} else if(n==1) {
    		// Reset game ui
    		configMainUI();
    		configGameUI(configState.replayGame);
    	}
    }
    
    /**
     * A method to handle replaying the game
     */
    private void replayPressed() {
    	// Create a dialog window asking the user if they want to
    	// start over with a new word.
    	Object[] options = {"New word",
    	                    "Same word",
    	                    "Cancel"};
    	int n = JOptionPane.showOptionDialog(this,
    	    "How do you want to restart?",
    	    "Replay",
    	    JOptionPane.YES_NO_CANCEL_OPTION,
    	    JOptionPane.QUESTION_MESSAGE,
    	    null,
    	    options,
    	    options[2]);
    	
    	if(n==0) {
    		// Reset game ui
    		configMainUI();
    		configGameUI(configState.newGame);
    	} else if(n==1) {
    		// Reset game ui
    		configMainUI();
    		configGameUI(configState.replayGame);
    	}
    }
    	
	/**
     * A method to handle saving the game
     */
    private void quitPressed() {
    	boolean quitGame = true;
    	if(gState != gameState.start) {
	    	// Create a dialog window asking the user if they want to
	    	// save their progress with the current game.
	    	Object[] options = {"Yes",
	    	                    "No",
	    	                    "Cancel"};
	    	int n = JOptionPane.showOptionDialog(this,
	    	    "Would you like to save before quitting?",
	    	    "Quit",
	    	    JOptionPane.YES_NO_CANCEL_OPTION,
	    	    JOptionPane.QUESTION_MESSAGE,
	    	    null,
	    	    options,
	    	    options[2]);
	    	
	    	if(n==0)
	    		this.savePrevGame(true);
	    	else if(n==2)
	    		quitGame = false;

    	}
    	
    	// See if we want to exit the game
    	if(quitGame)
    		this.dispose();
    }
    
    private void savePrevGame(boolean saveToFile) {
    	saveState.save(word.getWord(), word.getCorrectGuesses(), word.getIncorrectGuesses(),
    			difficulty, 0);
    	if(saveToFile)
    		saveState.saveGameState();
    }
    
    public void updateHangman() {
    	if(difficulty == 0) {
    		drawing.updateHangman();
    	} else if (difficulty == 1) {
    		drawing.updateHangman();
    		drawing.updateHangman();
    	} else {
    		drawing.updateHangman();
    		drawing.updateHangman();
    		drawing.updateHangman();
    	}
    }
    
    private void openThemeWindow() {
    	String[] themes = {"None", "Animals", "Cartoons and Superheroes", "Movies", "Science", "Sports"};

    	Object selected = JOptionPane.showInputDialog(null, "Choose a theme", "Selection", JOptionPane.DEFAULT_OPTION, null, themes, "0");
    	if ( selected != null ){//null if the user cancels.
    		WordGeneration.Theme myTheme = WordGeneration.Theme.NONE;
    		switch(selected.toString()) {
    			case "None":
    				myTheme = WordGeneration.Theme.NONE;
    				break;
    			case "Animals":
    				myTheme = WordGeneration.Theme.ANIMALS;
    				break;
    			case "Cartoons and Superheroes":
    				myTheme = WordGeneration.Theme.CARTOONS;
    				break;
    			case "Movies":
    				myTheme = WordGeneration.Theme.MOVIES;
    				break;
    			case "Science":
    				myTheme = WordGeneration.Theme.SCIENCE;
    				break;
    			case "Sports":
    				myTheme = WordGeneration.Theme.SPORTS;
    				break;
    		}
    		wordGen.loadTheme(myTheme);
    	}
    }
    
}