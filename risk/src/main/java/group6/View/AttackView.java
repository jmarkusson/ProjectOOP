package group6.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JComboBox;

public class AttackView extends JFrame{
// Attribut för AttackView

// Panels
private JPanel mainPanel;
private JPanel rollPanelJPanel;
private JPanel planetPanelJPanel;
// Buttons
private JButton rollDiceJButton;
private JLabel howManySoldiers;
private JLabel attackingFrom;
private JLabel attackThisPlanet;
//ComboBoxes
private JComboBox<String> planetToAttackComboBox;
private JComboBox<Integer> soldierComboBox;
// Attributes
private String planetName;
private Integer[] soldiersOnPlanet;
private String[] attackablePlanets;


// Konstruktor Planet attackingPlayersPlanet
public AttackView(String planetName, Color playerColor, Integer[] soldiersOnPlanet, String[] attackablePlanets){
    this.planetName = planetName;
    this.soldiersOnPlanet = soldiersOnPlanet;
    this.attackablePlanets = attackablePlanets;
    setTitle("Attack-View");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setPreferredSize(new Dimension(500,200));
    setBackground(playerColor);
    // MainPanel init.
    // Three different panels in order to get desired design.
    add(initMainPanel());
    this.mainPanel.add(initPlanetInfo());
    this.mainPanel.add(initRollPanel());
    setLocation(400, 300);
    pack();
    setVisible(true);
    toFront();
}
  private JPanel initMainPanel(){
      this.mainPanel = new JPanel(new FlowLayout());
      
    return this.mainPanel;
}
  //ImageIcon attackingImage, ImageIcon defendingImage
  private JPanel initPlanetInfo(){
      this.planetPanelJPanel = new JPanel();
      this.planetPanelJPanel.setPreferredSize(new Dimension(200,200));
      this.attackingFrom = new JLabel("Attacking from: " + planetName);
      this.attackThisPlanet = new JLabel("Choose a planet to attack:");
      this.planetToAttackComboBox = new JComboBox<String>(this.attackablePlanets);

      this.planetPanelJPanel.add(attackingFrom);
      this.planetPanelJPanel.add(attackThisPlanet);
      this.planetPanelJPanel.add(planetToAttackComboBox);
      
    return this.planetPanelJPanel;
}

  private JPanel initRollPanel(){
    this.rollPanelJPanel = new JPanel();
    this.rollPanelJPanel.setPreferredSize(new Dimension(200,200));
    this.rollDiceJButton = new JButton("ROLL");
    this.rollDiceJButton.setActionCommand("ROLL");
    this.soldierComboBox = new JComboBox<Integer>(this.soldiersOnPlanet);
    this.howManySoldiers = new JLabel("With how many Soldiers?");

    // add to Panels
    this.rollPanelJPanel.add(this.howManySoldiers);
    this.rollPanelJPanel.add(this.rollDiceJButton);
    this.rollPanelJPanel.add(this.soldierComboBox);

    return this.rollPanelJPanel;
}

 
  public void setController(ActionListener e){
      this.rollDiceJButton.addActionListener(e);
  }

  public JComboBox<Integer> getSoldiersComboBox(){
      return this.soldierComboBox;
  }

  public JComboBox<String> getPlanetToAttackComboBox(){
      return this.planetToAttackComboBox;
  }

  public String getPlanetName(){
      return this.planetName;
  }

}

    



