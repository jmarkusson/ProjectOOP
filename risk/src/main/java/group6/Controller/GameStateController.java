package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import group6.Model.RiskModel;
import group6.View.BoardView;

public class GameStateController implements ActionListener {

    RiskModel model;
    BoardView view;

    public GameStateController(RiskModel model, BoardView view){

        this.model = model;
        this.view = view;
        view.addActionListenertostateButton(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(!model.isReinforceDone() && model.getCurrentGameState() == "REINFORCE"){
            JOptionPane.showMessageDialog(view, "You still have troops to reinforce", "HOLD UP", JOptionPane.ERROR_MESSAGE);
               return; 
       }
       
       else{
           if (model.getCurrentGameState() == "ATTACK"){
               view.setNextButtonLabel("NEXT PLAYER");
           }
           else if(model.getCurrentGameState() == "FORTIFY"){
               model.nextPlayer();
               view.setNextButtonLabel("NEXT STATE");
               view.getCurrentPlayerLabel().setText(model.getCurrentPlayer().getName() + "'s TURN");
               view.getCurrentPlayerLabel().setForeground(model.getCurrentPlayer().getColor());
             }
           
           model.changeState();
           
        }
    
    } 
}
