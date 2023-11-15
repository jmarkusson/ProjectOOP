

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenuView extends JFrame{
    private JPanel mainPanel;
    private GridLayout mainLayout;
    	
	private JButton newGameButton;
    private JButton quitButton;



    MainMenuView()
    {
        setTitle("Java-Risk");
		setPreferredSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
        add(mainMenu());

		pack();
		setVisible(true);
		toFront();
    }

    protected JPanel mainMenu()
    {
		mainPanel = new JPanel();

		mainLayout = new GridLayout(3, 1, 5, 5);
		mainPanel.setLayout(mainLayout);
		
		newGameButton = new JButton("New Game");
		quitButton = new JButton("Quit");

		newGameButton.setActionCommand("New Game");
		quitButton.setActionCommand("Quit Game");
		
		mainPanel.add(newGameButton);
		mainPanel.add(quitButton);
		
		return mainPanel;
    }

	public void setController(ActionListener e){
		
		newGameButton.addActionListener(e);
		quitButton.addActionListener(e);
	}
}
