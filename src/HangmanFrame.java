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



    ImageIcon image = new ImageIcon("start.png");


    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == btnToggle){
            if (btnToggle.getText().equals("Play")){
                btnToggle.setText("Back");
                gameStartLabel.setVisible(false);
            }else {
                btnToggle.setText("Play");
                gameStartLabel.setVisible(true);
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
        btnToggleLabel = new JLabel();
        btnToggleLabel.setBounds(0, 0, 800, 50);
        btnToggleLabel.setOpaque(true);
        btnToggleLabel.setVisible(true);
    }


    private void createButtons(){


        //initiates button that toggles between Play and Back
        btnToggle = new JButton("Play");
        btnToggle.setBounds(340, 15, 100, 30);
        btnToggle.setVisible(true);
        btnToggle.addActionListener(this);


    }


    private void configFrame()
    {   //configures components on HangmanFrame
        this.setLayout(null);
        this.add(gameStartPanel);
        this.add(btnTogglePanel);
        this.setTitle("Hangman");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
    }
}


