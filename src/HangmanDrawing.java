import java.awt.*;

import javax.swing.*;

public class HangmanDrawing extends JPanel {
	
	private JPanel drawingPanel;
	private JLabel drawingLabel;
	private JTextArea incorrectGuessArea;
	
	private HangmanFrame hmf;
	
	private int incorrectGuesses;
	
	/**
	 * A constructor for the HangmanDrawing class.
	 * @param hmf A HangmanFrame holding the underlying JFrame.
	 */
	HangmanDrawing(HangmanFrame hmf) {
		incorrectGuesses = 0;
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
		this.setVisible(false); 
	}
	
	public void updateHangman() {
		incorrectGuesses++;
        repaint();
        if (incorrectGuesses >= 6)
        {
        	hmf.showLostScreen();
        }
	}
	public void setIncorrectGuesses(int guesses) {
		incorrectGuesses = guesses;
	}
	public int getIncorrectGuesses() {
		return incorrectGuesses;
	}
	public void resetHangman() {
		incorrectGuesses = 0;
	}

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        g.setColor(Color.BLACK);
	        g.drawLine(10,10, 150, 10); //horizontal line
	        g.drawLine(150,10, 150, 50);//small straight line
	        g.drawLine(10,10, 10, 300); //vertical line
	        g.drawLine(0,300, 20, 300);
	        //hangman figure based on the number of incorrect guesses
	        if (incorrectGuesses >= 1) {
	            // head
	            g.drawOval(125,52 , 50, 50);
	        }
	        if (incorrectGuesses >= 2) {
	            // body
	            g.drawLine(150, 100, 150, 230);
	        }
	        if (incorrectGuesses >= 3) {
	            // left arm
	            g.drawLine(150, 120, 65, 150);
	        }
	        if (incorrectGuesses >= 4) {
	            // right arm
	            g.drawLine(150, 120, 230, 150);
	        }
	        if (incorrectGuesses >= 5) {
	            // left leg
	            g.drawLine(150, 230, 80, 270);
	        }
	        if (incorrectGuesses >= 6) {
	            // right leg
	            g.drawLine(150, 230, 230, 270);
	        }
	    }
	    
	}


