import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HangmanInput implements ActionListener {
	private JPanel inputPanel;
	private JLabel inputLabel;
	
	private JTextField textField;
	
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
		//TODO update this to ensure it can only accept 1 letter at a time
		textField = new JTextField(1);
		textField.setBounds(10, 10, 40, 75);
		textField.setVisible(true);
		textField.addActionListener(this);
		textField.setFont(new Font("Serif", Font.BOLD, 40));
		
		//TODO this section is probably not needed.
		inputLabel = new JLabel();
    		inputLabel.setBounds(0, 0, 450, 165);
    		inputLabel.setVisible(true);
    	
    		inputPanel = new JPanel();
    		inputPanel.setLayout(null);;
    		inputPanel.setBackground(Color.GRAY);
    		inputPanel.setBounds(350, 400, 450, 165);
    		inputPanel.setVisible(true);
    		inputPanel.add(textField);
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

	/**
	 * A method to determine when an action is performed, specifically, when text is input into the text box.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == textField) {
			String s = e.getActionCommand();
			
			if(s.length() == 1) {
				this.hmf.guessLetter(s);
			}
			
			textField.setText("");
		}     	
	}
}
