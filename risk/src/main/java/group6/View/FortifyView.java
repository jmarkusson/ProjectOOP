package group6.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FortifyView extends JFrame {

    String[] adjecentPlanets;
    Integer[] amountOfSoldiers;
    String planetName;
    
    JPanel firstPanel;
    GridLayout mainLayout;
    JComboBox<String> adjecentPlanetsBox;
    JComboBox<Integer> amountOfSoldiersBox;
    JButton fortifyButton;
    JLabel header;

    public FortifyView(String planetName, String[] adjecentPlanets, Integer[] amountOfSoldiers){

        this.planetName = planetName;
        this.adjecentPlanets = adjecentPlanets;
        this.amountOfSoldiers = amountOfSoldiers;
        
        setTitle("Forify "+ planetName);
		setPreferredSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

        add(this.firstPanel());

        pack();
		setVisible(true);
		toFront();
        
    }

    public JPanel firstPanel(){

        this.firstPanel = new JPanel();
		this.mainLayout = new GridLayout(3, 1, 5, 5);
		firstPanel.setLayout(mainLayout);

        header = new JLabel("Select a country to fortify from: "+this.planetName);
        this.fortifyButton = new JButton("Fortify");
        fortifyButton.setActionCommand("fortify");

        this.adjecentPlanetsBox = new JComboBox<String>(adjecentPlanets);

        this.amountOfSoldiersBox = new JComboBox<Integer>(amountOfSoldiers);
        amountOfSoldiersBox.setSelectedIndex(0);
    
        firstPanel.add(header);
        firstPanel.add(adjecentPlanetsBox);
        firstPanel.add(amountOfSoldiersBox);
        firstPanel.add(fortifyButton);

        return this.firstPanel;
    }

    public void addController(ActionListener controller){
        this.adjecentPlanetsBox.addActionListener(controller);
        this.fortifyButton.addActionListener(controller);
    }
    
    
}
