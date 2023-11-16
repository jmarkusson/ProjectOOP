package group6.View;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class AttackView extends JFrame{
private JComboBox soldiersAttack;
 
AttackView(String[] nrOfSoldiers){
 
    this.soldiersAttack = new JComboBox<String>(nrOfSoldiers); 
    this.soldiersAttack.setSelectedItem('2');
    this.soldiersAttack.setActionCommand("combobox");
    add(soldiersAttack);

    pack();
    setVisible(true);
    toFront();

    
}
  public void setcontroller(ActionListener e){
        



    }
}
    



