package group6.View;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOptionsView extends JFrame {

    JPanel mainPanel;
    private GridLayout mainLayout;
    	
	private JButton startGameButton;
    private JButton quitButton;

    private JPanel initPanel;
    private GridLayout iniLayout;

    private JComboBox nmbrOfPlayers;
    private JButton nextButton;

    private ActionListener controller;

    public ArrayList<JTextField> textfields = new ArrayList<>();


    public GameOptionsView()
    {     
        setTitle("Risk-Options");
		setPreferredSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		setVisible(true);
		toFront();
    }

   public void mainView(int nmbrOfPlayers) {
        mainPanel = new JPanel();
        mainLayout = new GridLayout(nmbrOfPlayers + 2, 1, 5, 5); 
        mainPanel.setLayout(mainLayout);

        for (int i = 0; i < nmbrOfPlayers; i++) {
            JTextField playerNameField = new JTextField();
            playerNameField.setPreferredSize(new Dimension(100, 10));
            playerNameField.setBorder(BorderFactory.createTitledBorder("Player " + (i + 1) + " Name"));
            mainPanel.add(playerNameField);
            textfields.add(playerNameField);
        }

        startGameButton = new JButton("Start Game");
        quitButton = new JButton("Quit");

        startGameButton.setActionCommand("StartGame");
        quitButton.setActionCommand("Quit Game");

        startGameButton.addActionListener(controller);
        quitButton.addActionListener(controller);

        mainPanel.add(startGameButton);
        mainPanel.add(quitButton);

        setContentPane(mainPanel);
        revalidate();
    }

    public void initView() {
        initPanel = new JPanel();
        initPanel.setLayout(new BorderLayout());
    
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 5, 5));
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
    
        String[] playersOption = {"2 Players", "3 Players", "4 Players"};
    
        nmbrOfPlayers = new JComboBox<String>(playersOption);
        nmbrOfPlayers.setSelectedItem(0);
        nmbrOfPlayers.setActionCommand("combobox");
        nmbrOfPlayers.addActionListener(controller);
    
        nextButton = new JButton("Next");
        nextButton.setActionCommand("NEXT");
        nextButton.addActionListener(controller);
    
        buttonPanel.add(nextButton);
    
        centerPanel.add(createHeadingLabel("Select Number of Players:"));
        centerPanel.add(nmbrOfPlayers);
    
        initPanel.add(centerPanel, BorderLayout.CENTER);
        initPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        setContentPane(initPanel);
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
        toFront();
    }
    
    private JLabel createHeadingLabel(String text) {
        JLabel headingLabel = new JLabel(text);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        headingLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(10, 10, 10, 10) 
        ));
        return headingLabel;
    }

    public void setController(ActionListener controller){
        this.controller = controller;
    }

}
 