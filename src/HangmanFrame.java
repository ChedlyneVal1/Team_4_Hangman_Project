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
    
    private HangmanDrawing drawing;
    private HangmanInput input;
    private HangmanWordPanel word;



    private ImageIcon image = new ImageIcon("start.png");


    @Override
    public void actionPerformed(ActionEvent e) {    	
        if(e.getSource() == btnToggle){
            if (btnToggle.getText().equals("Play")){      
                configGameUI();
                
            }else {
            	configMainUI();
            }
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
    private void createComponents() {
    	drawing = new HangmanDrawing(this);
    	word = new HangmanWordPanel(this);
    	input = new HangmanInput(this);
    	
    	word.setWord("Hello");//TODO delete or update.
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
    private void configGameUI() {
    	hideMainUI();
    	
    	createComponents();
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
    }
    
    /**
     * A method to remove the UI elements of the Game Screen.
     */
    private void hideGameUI() {
    	drawing.removeUI();
    	input.removeUI();
    	word.removeUI();
    	
    	this.remove(btnTogglePanel);
    }
    
}