package group6.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReinforceView extends JFrame {
    
    JButton addButton;
    JComboBox<Integer> soldiersAmountBox;
    JPanel mainPanel;
    GridLayout mainLayout;
    String planetName;
    public ReinforceView(String planetName, Integer[] soldiersAmount){
        this.planetName = planetName;
        setTitle("Reinforce "+this.planetName);
		setPreferredSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

        mainPanel = new JPanel();

		mainLayout = new GridLayout(3, 1, 5, 5);
		mainPanel.setLayout(mainLayout);

        addButton = new JButton("Add soldiers");
        addButton.setActionCommand("addsoldiers");
       
        soldiersAmountBox = new JComboBox<Integer>(soldiersAmount);
        soldiersAmountBox.setSelectedItem(0);
        soldiersAmountBox.setActionCommand("combobox");

        mainPanel.add(soldiersAmountBox);
        mainPanel.add(addButton);
        
        add(mainPanel);

		pack();
		setVisible(true);
		toFront();
    }

    public String getplanetName(){
        return planetName;
    }
    public void addController(ActionListener controller){
        soldiersAmountBox.addActionListener(controller);
        addButton.addActionListener(controller);
    }
}
