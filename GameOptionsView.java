import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOptionsView extends JFrame {

    private JPanel mainPanel;
    private GridLayout mainLayout;
    	
	private JButton startGameButton;
    private JButton quitButton;

    private JPanel initPanel;
    private GridLayout iniLayout;

    private JComboBox nmbrOfPlayers;
    private JButton nextButton;

    private ActionListener controller;


    GameOptionsView()
    {     
        setTitle("Risk-Options");
		setPreferredSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		setVisible(true);
		toFront();
    }

   protected void mainView(int nmbrOfPlayers) {
        mainPanel = new JPanel();
        mainLayout = new GridLayout(nmbrOfPlayers + 2, 1, 5, 5); 
        mainPanel.setLayout(mainLayout);

        // Create JTextFields for each player
        for (int i = 0; i < nmbrOfPlayers; i++) {
            JTextField playerNameField = new JTextField();
            playerNameField.setBorder(BorderFactory.createTitledBorder("Player " + (i + 1) + " Name"));
            playerNameField.setActionCommand("textfield");
            mainPanel.add(playerNameField);

            playerNameField.addActionListener(controller);
        }

        startGameButton = new JButton("Start Game");
        quitButton = new JButton("Quit");

        startGameButton.setActionCommand("StartGame");
        quitButton.setActionCommand("Quit Game");

        startGameButton.addActionListener(controller);
        quitButton.addActionListener(controller);

        mainPanel.add(startGameButton);
        mainPanel.add(quitButton);

        removeAll();
        add(mainPanel);
    }

    protected void initView(){
        initPanel = new JPanel();
        iniLayout = new GridLayout(3, 1, 5, 5);
        initPanel.setLayout(iniLayout);
        String[] playersOpiton = {"2 Players", "3 Players", "4 Players"};

        nmbrOfPlayers = new JComboBox<String>(playersOpiton);
        nmbrOfPlayers.setSelectedItem(0);
        nmbrOfPlayers.setActionCommand("combobox");
        nmbrOfPlayers.addActionListener(controller);

        nextButton = new JButton("Next");
        nextButton.setActionCommand("NEXT");

        nextButton.addActionListener(controller);

        initPanel.add(nextButton);
        initPanel.add(nmbrOfPlayers);

        add(initPanel);
        setVisible(true);
		toFront();
        
    }

    public void setController(ActionListener controller){
        this.controller = controller;
    }
    
}
 