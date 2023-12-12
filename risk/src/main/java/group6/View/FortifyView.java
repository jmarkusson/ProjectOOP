package group6.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FortifyView extends JFrame {

    String[] adjecentPlanets;
    Integer[] amountOfSoldiers;
    String planetName;
    
    JPanel firstPanel;
    GridLayout mainLayout;
    JComboBox<String> adjecentPlanetsBox;
    JComboBox<Integer> amountOfSoldiersBox;

    public String getPlanetName() {
        return planetName;
    }

    JButton fortifyButton;
    JLabel planetSelectLabel;
    JLabel soldierSelectLabel;

    public FortifyView(String planetName, String[] adjecentPlanets, Integer[] amountOfSoldiers){

        this.planetName = planetName;
        this.adjecentPlanets = adjecentPlanets;
        this.amountOfSoldiers = amountOfSoldiers;
        
        setTitle("Forify "+ planetName);
		setPreferredSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

        add(this.firstPanel());

        pack();
		setVisible(true);
		toFront();
        
    }

    public JComboBox<String> getAdjecentPlanetsBox() {
        return adjecentPlanetsBox;
    }

    public JComboBox<Integer> getAmountOfSoldiersBox() {
        return amountOfSoldiersBox;
    }

    public JPanel firstPanel(){

        this.firstPanel = new JPanel();
		this.mainLayout = new GridLayout(3, 2, 5, 5);
		firstPanel.setLayout(mainLayout);

        planetSelectLabel = new JLabel("<html>Select a country<br>to fortify: </html>");
        planetSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.fortifyButton = new JButton("Fortify");
        fortifyButton.setActionCommand("fortify");

        this.adjecentPlanetsBox = new JComboBox<String>(adjecentPlanets);

        soldierSelectLabel = new JLabel("<html>Select how<br>many soldiers: </html>");
        soldierSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.amountOfSoldiersBox = new JComboBox<Integer>(amountOfSoldiers);
        amountOfSoldiersBox.setSelectedIndex(0);
    
        firstPanel.add(planetSelectLabel);
        firstPanel.add(adjecentPlanetsBox);
        firstPanel.add(soldierSelectLabel);
        firstPanel.add(amountOfSoldiersBox);
        firstPanel.add(fortifyButton);

        return this.firstPanel;
    }

    public void addController(ActionListener controller){
        this.adjecentPlanetsBox.addActionListener(controller);
        this.fortifyButton.addActionListener(controller);
    }
    
    
}
