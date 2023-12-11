package group6.View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import javax.swing.JComboBox;

public class AttackView extends JFrame{
// Attribut för AttackView

// Panels
private JPanel mainPanel;
private JPanel rollPanelJPanel;
private JPanel planetPanelJPanel;
// Buttons
private JButton rollDiceJButton;
private JTextField howManySoldiers;
private JTextField attackingFrom;
private JTextField attackThisPlanet;
//ComboBoxes
private JComboBox<String> planetToAttackComboBox;
private JComboBox<Integer> soldierComboBox;
// Attributes
private String planetName;
private Color playerColor;
private Integer[] soldiersOnPlanet;
private String[] attackablePlanets;

// Alternativa grejer:
    // private JLabel soldiersOnAttackingPlanet;
    // private JLabel soldiersOnDefendingPlanet;





// Konstruktor Planet attackingPlayersPlanet
public AttackView(String planetName, Color playerColor, Integer[] soldiersOnPlanet, String[] attackablePlanets){
    this.planetName = planetName;
    this.playerColor = playerColor;
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
      this.attackingFrom = new JTextField("Attacking from: " + planetName);
      this.attackThisPlanet = new JTextField("Choose a planet to attack:");
      this.planetToAttackComboBox = new JComboBox<String>(this.attackablePlanets);



      // add to panel
      this.planetPanelJPanel.add(attackingFrom);
      this.planetPanelJPanel.add(attackThisPlanet);
      this.planetPanelJPanel.add(planetToAttackComboBox);
      
    return this.planetPanelJPanel;
}
  // public void addComponents(){
  //   this.attackPanel.add(chooseAmountSoldiers);add(chooseOpponent);add(rollDice); 
  //   add(attackingPlayerImage);add(defendingPlayerImage); 
  //   add(soldiersOnDefendingPlanet);add(soldiersOnAttackingPlanet);
  // }
  private JPanel initRollPanel(){
    this.rollPanelJPanel = new JPanel();
    this.rollPanelJPanel.setPreferredSize(new Dimension(200,200));
    this.rollDiceJButton = new JButton("Roll");
    this.soldierComboBox = new JComboBox<Integer>(this.soldiersOnPlanet);
    this.howManySoldiers = new JTextField("With how many Soldiers?");

    // add to Panels
    this.rollPanelJPanel.add(this.howManySoldiers);
    this.rollPanelJPanel.add(this.rollDiceJButton);
    this.rollPanelJPanel.add(this.soldierComboBox);

    


    return this.rollPanelJPanel;
}

 
  public void setController(ActionListener e){

  }


}

    



