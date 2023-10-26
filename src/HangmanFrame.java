import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HangmanFrame extends JFrame implements ActionListener {
    private static JPanel gameStartPanel;
    private static JLabel gameStartLabel;
    private static JPanel btnTogglePanel;
    private static JLabel btnToggleLabel;
    private static JButton btnToggle;
    private static JButton btnReplay;
    private static JButton btnExit;

    private HangmanDrawing drawing;
    private HangmanInput input;
    private HangmanWordPanel word;
    private WordGeneration wordGen = WordGeneration.getInstance();
    private String wordString;


    private ImageIcon image = new ImageIcon("start.png");


    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	
        if(e.getSource() == btnToggle){
            if (btnToggle.getText().equals("Play")){      
                configGameUI(true);
                
            }else {
            	configMainUI();
            }
        } else if (e.getSource() == btnReplay) {
        	replayPressed();
        } else if (e.getSource()== btnExit) {
            this.dispose();
        }

    }


    public static void main(String[] args) throws Exception
    {
        HangmanFrame hmF = new HangmanFrame();
        hmF.createLabels();
        hmF.createButtons();
        hmF.createPanels();
        hmF.configFrame();
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


        //initiates button that toggles between Play and Back
        btnToggle = new JButton("Play");
        btnToggle.setBounds(340, 15, 100, 30);
        btnToggle.setVisible(true);
        btnToggle.addActionListener(this);
        
        //initiates button that Replays
        btnReplay = new JButton("Replay");
        btnReplay.setBounds(225, 15, 100, 30);
        btnReplay.setVisible(false);
        btnReplay.addActionListener(this);

        btnExit = new JButton("Exit");
        btnExit.setBounds(455, 15, 100, 30);
        btnExit.setVisible(true);
        btnExit.addActionListener(this);



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
    
    
   /**
    * A method to create the other objects needed for the game to function.
    */
    private void createComponents(boolean newWord) {
    	drawing = new HangmanDrawing(this);
        word = new HangmanWordPanel(this);
    	input = new HangmanInput(this);
    	
    	if(newWord)
    		wordString = wordGen.genaratedWord();
    		
    	word.setWord(wordString);
    }
    
    public void guessLetter(String s) {
    	word.guessLetter(s);
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
    private void configGameUI(boolean newWord) {
    	hideMainUI();
    	
    	createComponents(newWord);
    	addGameUI();
    }
    
    /**
     * A method to add the UI elements to the Main Screen.
     */
    private void addMainUI() {
    	createLabels();
    	createButtons();
        createPanels();
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
    	
    	if(n!=2) {
    		// Reset game ui
    		configMainUI();
    		configGameUI(n==0);
    	}
    	
    }
    
}