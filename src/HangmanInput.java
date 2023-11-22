import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HangmanInput implements ActionListener {
	private JPanel inputPanel;
	private JLabel inputLabel;
	private JLabel inputRuleText;
	private JLabel incorrectGuessesRuleText;
	private JLabel incorrectGuesses;
	
	private JTextField inputTextField;
	
	
	private HangmanFrame hmf;
	
	/**
	 * A constructor for the HangmanInput class.
	 * @param hmf A HangmanFrame holding the underlying JFrame.
	 */
	HangmanInput(HangmanFrame hmf) {
		this.hmf = hmf;
		init();
	}
	
	/**
	 * A method to initialize the JPanels and JLabels associated with the HangmanDrawing class.
	 */
	private void init() {
		inputTextField = new JTextField(1);	
		inputTextField.setBounds(60, 60, 40, 60);
		inputTextField.setVisible(true);
		inputTextField.addActionListener(this);
		inputTextField.setFont(new Font("Serif", Font.BOLD, 40));
		
		inputLabel = new JLabel();
		inputLabel.setBounds(0, 0, 450, 165);
		inputLabel.setVisible(true);    	
		
		inputRuleText = new JLabel();
		inputRuleText.setLayout(null);
		inputRuleText.setBackground(Color.GRAY);
		inputRuleText.setBounds(20, 10, 200, 60);
		inputRuleText.setVisible(true);
		inputRuleText.setFont(new Font("Serif", Font.BOLD, 20));
		inputRuleText.setForeground(Color.BLACK);
		inputRuleText.setText("Guess a letter:");
		
		incorrectGuesses = new JLabel();
		incorrectGuesses.setLayout(null);
		incorrectGuesses.setBackground(Color.GRAY);
		incorrectGuesses.setBounds(200, 25, 200, 100);
		incorrectGuesses.setVisible(true);
		incorrectGuesses.setFont(new Font("Serif", Font.BOLD, 12));
		incorrectGuesses.setForeground(Color.BLACK);
		
		incorrectGuessesRuleText = new JLabel();
		incorrectGuessesRuleText.setLayout(null);
		incorrectGuessesRuleText.setBackground(Color.GRAY);
		incorrectGuessesRuleText.setBounds(200, 15, 175, 50);
		incorrectGuessesRuleText.setVisible(true);
		incorrectGuessesRuleText.setFont(new Font("Serif", Font.BOLD, 20));
		incorrectGuessesRuleText.setForeground(Color.BLACK);
		incorrectGuessesRuleText.setText("Incorrect Guesses:");
		
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		inputPanel.setBackground(Color.GRAY);
		inputPanel.setBounds(350, 400, 450, 165);
		inputPanel.setVisible(true);
		inputLabel.add(incorrectGuesses);
		inputPanel.add(inputTextField);
		inputPanel.add(inputRuleText);   	
		inputPanel.add(incorrectGuessesRuleText);
		
		inputPanel.setComponentZOrder(incorrectGuesses, 0);
		inputPanel.setComponentZOrder(incorrectGuessesRuleText, 1);
		inputPanel.setComponentZOrder(inputRuleText, 2);
	}
	
	/**
	 * A method to add the UI to the underlying JFrame contained in the HangmanFrame class.
	 */
	public void addUI() {
		this.hmf.add(inputPanel);
	}
	
	/**
	 * A method to remove the UI from the underlying HangmanFrame class.
	 */
	public void removeUI() {
		this.hmf.remove(inputPanel);
	}
	
	public void updateIncorrectGuesses(ArrayList<String> s) {
		String wrongLetters = "<html>";
		
		for(int i = 0; i < s.size(); ++i) {
			if(i == 13) {
				wrongLetters = wrongLetters + "<br>";
			}
			wrongLetters = wrongLetters + s.get(i);
			
			if(i < s.size() - 1) {
				wrongLetters = wrongLetters + ", ";
			}
		}
		wrongLetters = wrongLetters + "</html>";
		
		incorrectGuesses.setText(wrongLetters);
	}
	
	/**
	 * A method to determine when an action is performed, specifically, when text is input into the text box.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == inputTextField) {
			String s = e.getActionCommand();
			
			if(s.length() == 1) {
				this.hmf.guessLetter(s);
			}
			else if (s.length() > 1){
				JOptionPane.showMessageDialog(this.hmf, "You may only guess one letter at a time!", "Too many letters!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			inputTextField.setText("");
			
			this.hmf.checkWinCondition();
		}     	
	}
}
