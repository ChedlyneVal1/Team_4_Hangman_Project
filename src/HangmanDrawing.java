import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HangmanDrawing {
	
	private JPanel drawingPanel;
	private JLabel drawingLabel;
	
	private HangmanFrame hmf;
	
	/**
	 * A constructor for the HangmanDrawing class.
	 * @param hmf A HangmanFrame holding the underlying JFrame.
	 */
	HangmanDrawing(HangmanFrame hmf) {
		this.hmf = hmf;
		init();
	}
	
	/**
	 * A method to initialize the JPanels and JLabels associated with the HangmanDrawing class.
	 */
	private void init() {
		//create the drawingLabel
		drawingLabel = new JLabel();
        drawingLabel.setBounds(0, 50, 350, 550);
        drawingLabel.setVisible(true);		
		
        //create the drawingPanel
		drawingPanel = new JPanel();
    	drawingPanel.setLayout(null);
    	drawingPanel.setBackground(Color.WHITE);
    	drawingPanel.setBounds(0, 50, 350, 550);
        drawingPanel.setVisible(true);
        drawingPanel.add(drawingLabel);
	}
	
	/**
	 * A method to add the UI to the underlying JFrame contained in the HangmanFrame class.
	 */
	public void addUI() {
		this.hmf.add(drawingPanel);
	}
	
	/**
	 * A method to remove the UI from the underlying HangmanFrame class.
	 */
	public void removeUI() {
		this.hmf.remove(drawingPanel);
	}
	
	private void updateHangman() {
		//TODO
	}
	
	private void resetHangman() {
		//TODO
	}

}
