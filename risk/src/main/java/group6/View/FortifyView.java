package group6.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FortifyView extends JFrame {
    
    JButton addButton;
    DefaultComboBoxModel soldiersAmountBox;
    JPanel mainPanel;
    GridLayout mainLayout;

    public FortifyView(String planetName, String[] soldiersAmount){
        setTitle("Forify "+planetName);
		setPreferredSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

        mainPanel = new JPanel();

		mainLayout = new GridLayout(3, 1, 5, 5);
		mainPanel.setLayout(mainLayout);

        addButton = new JButton("Add soldiers");
        addButton.setActionCommand("addsoldiers");
       
        soldiersAmountBox = new DefaultComboBoxModel<String>(soldiersAmount);
        soldiersAmountBox.setSelectedItem(0);
       // soldiersAmountBox.setActionCommand("combobox");

       // mainPanel.add(soldiersAmountBox);
        mainPanel.add(addButton);

		pack();
		setVisible(true);
		toFront();
    }

    public void addController(ActionListener controller){
       // soldiersAmountBox.addActionListener(controller);
        addButton.addActionListener(controller);
    }
}
