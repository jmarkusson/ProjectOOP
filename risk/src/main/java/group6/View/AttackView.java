package group6.View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Position;

import group6.Model.Planet;


import javax.swing.JComboBox;

public class AttackView extends JFrame{
// Attribut för AttackView
private ImageIcon yoda; 
private ImageIcon darthVader;
private JComboBox chooseAmountSoldiers;
private JComboBox chooseOpponent;
private JPanel attackPanel;
private JPanel defendPanel;
private JButton rollDice;
private JButton attackingPlayerConfirmSoldiersButton; 
private JButton defendingPlayerConfirmSolidersButton;
private JButton oneDice;
private JButton twoDices;
private JButton threeDices;
private JLabel attackingPlayerImage; 
private JLabel defendingPlayerImage;
private JLabel soldiersOnAttackingPlanet;
private JLabel soldiersOnDefendingPlanet;
private JCheckBox chooseAmountDice; 




// Konstruktor Planet attackingPlayersPlanet
public AttackView(){
    setTitle("Attack-View");

    setPreferredSize(new Dimension(500,200));
    // MainPanel init.
   

    add(initAttackComponents());

    pack();
    setVisible(true);
    toFront();
  }
  //ImageIcon attackingImage, ImageIcon defendingImage
  private JPanel initAttackComponents(){
    //Panels
    this.attackPanel = new JPanel();
    attackPanel.setLayout(new GridLayout(6,10,2,2));
    // Behövs en knapp för antal trupper.
    // 





    //Panels
  
    //Buttons
    this.rollDice = new JButton("roll");
    this.oneDice = new JButton("1 dice");
    this.twoDices = new JButton("2 dices");
    this.threeDices = new JButton("3 dices");
    //Attacking player confirmation button
    
    this.attackingPlayerConfirmSoldiersButton = new JButton("confirm");
    attackingPlayerConfirmSoldiersButton.setForeground(Color.RED);
    //Defending player confirmation button
    this.defendingPlayerConfirmSolidersButton = new JButton("confirm");
    defendingPlayerConfirmSolidersButton.setForeground(Color.BLUE);
    //Labels
    this.yoda = new ImageIcon("/Users/alfredpettersson/Desktop/Skola/OOPP/Project-OOP/risk/src/main/resources/textfiles/Images/transparentHead.png");
    Image yodaTransform = yoda.getImage();
    Image yodaResize = yodaTransform.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    this.darthVader = new ImageIcon("/Users/alfredpettersson/Desktop/Skola/OOPP/Project-OOP/risk/src/main/resources/textfiles/Images/ef0c6c257aa3d8b6fa14c82491ddd205c619215d.jpg");
    Image darthVaderTransform = darthVader.getImage();
    Image darthVaderReSize = darthVaderTransform.getScaledInstance(120, 200, Image.SCALE_SMOOTH);
    
     

    this.attackingPlayerImage = new JLabel(new ImageIcon(yodaResize));
    this.defendingPlayerImage = new JLabel(new ImageIcon(darthVaderReSize));
    this.soldiersOnAttackingPlanet = new JLabel();
    this.soldiersOnDefendingPlanet = new JLabel();

    // Checkboxes
    this.chooseAmountDice = new JCheckBox("How many dices?");
    // add all components:

    // All setters for actions commanded should be implemented here:
    this.attackingPlayerConfirmSoldiersButton.setActionCommand(getName());
    this.
    attackPanel.add(attackingPlayerImage);
    attackPanel.add(defendingPlayerImage);
    attackPanel.add(oneDice);
    attackPanel.add(twoDices);
    attackPanel.add(threeDices);
    attackPanel.add(rollDice);
    attackPanel.add(attackingPlayerConfirmSoldiersButton);
    attackPanel.add(defendingPlayerConfirmSolidersButton, BorderLayout.EAST);

    attackPanel.add(rollDice, BorderLayout.SOUTH);


    
    return this.attackPanel;
  }
  // public void addComponents(){
  //   this.attackPanel.add(chooseAmountSoldiers);add(chooseOpponent);add(rollDice); 
  //   add(attackingPlayerImage);add(defendingPlayerImage); 
  //   add(soldiersOnDefendingPlanet);add(soldiersOnAttackingPlanet);
  // }

  public void playerToFace(){

  }
  public void planetToFace(){

  }
    private JPanel initDefendComponents(){
        return this.defendPanel;
    }
  public void setController(ActionListener e){
        this.chooseAmountDice.addActionListener(e);
        this.rollDice.addActionListener(e);



    } 
  public JComboBox getButton(){
    return this.chooseAmountSoldiers;
  }



  }

    



