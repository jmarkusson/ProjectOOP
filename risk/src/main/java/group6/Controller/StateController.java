package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


import group6.Model.RiskModels.ModelFacade;
import group6.View.BoardView;

public class StateController implements ActionListener {

    ModelFacade modelFacade;
    BoardView view;

    public StateController(ModelFacade model, BoardView view){

        this.modelFacade = model;
        this.view = view;
        view.addActionListenertostateButton(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(!modelFacade.isReinforceDone() && modelFacade.getCurrentGameState() == "REINFORCE"){
            JOptionPane.showMessageDialog(view, "You still have troops to reinforce", "HOLD UP", JOptionPane.ERROR_MESSAGE);
               return; 
       }
       
       else{
           if (modelFacade.getCurrentGameState() == "ATTACK"){
               view.setNextButtonLabel("NEXT PLAYER");
           }
           else if(modelFacade.getCurrentGameState() == "FORTIFY"){
               modelFacade.nextPlayer();
               view.setNextButtonLabel("NEXT STATE");
               view.getCurrentPlayerLabel().setText(modelFacade.getCurrentPlayer().getName() + "'s TURN");
               view.getCurrentPlayerLabel().setForeground(modelFacade.getCurrentPlayer().getColor());
             }
           
           modelFacade.changeState();
           
        }
    
    } 
}
