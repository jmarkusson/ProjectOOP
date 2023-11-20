package group6.View;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PlayerStateView extends JPanel {
    private ArrayList<String> states;
    private JButton currentStateButton;
    private JButton nextButton;
  

    public PlayerStateView() {
     
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setPreferredSize(new Dimension(400, 100)); 

  
        states = new ArrayList<>();
        states.add("Place Soldiers");
        states.add("Attack");
        states.add("Move Soldiers");
       
        currentStateButton = new JButton(states.get(0));
        currentStateButton.setEnabled(false);
        add(currentStateButton);

        nextButton = new JButton("Next");
        nextButton.setActionCommand("next");
        add(nextButton);

    }

    public void addController(ActionListener controller){
        nextButton.addActionListener(controller);
    }


    public void updateStateView(int currentStateIndex) {
        currentStateButton.setText(states.get(currentStateIndex));
    }

}
