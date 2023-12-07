package group6.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import group6.Model.GameStateReinforce;
import group6.Model.RiskModel;
import group6.Model.Interfaces.GameState;
import group6.View.BoardView;

public class BoardController implements ActionListener{
    
    private RiskModel model;
    private BoardView view;
    private GameState gameState;
    
    protected BoardController(RiskModel model, BoardView view){
        this.model = model;
        this.view = view;
        view.initializePlanetButtons(this);
        gameState = new GameStateReinforce();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getActionCommand().equals("NEXT")){
            if(gameState.gameStateString() == "REINFORCE" && !model.isReinforceDone()){
                 JOptionPane.showMessageDialog(view, "You still have troops to reinforce", "HOLD UP", JOptionPane.ERROR_MESSAGE);
                    return; 
            }
            else{
                view.updateCurrentStateLabel(gameState.gameStateString());
                gameState = gameState.changeState();

                if (gameState.gameStateString() == "ATTACK"){
                    view.setNextButtonLabel();
                }
                else if(gameState.gameStateString() == "FORTIFY"){
                    model.nextPlayer();
                }
            }
        }
        else {
            if(!model.isOwnedCurrentPlayer(model.getPlanetByName(e.getActionCommand()))){
                JOptionPane.showMessageDialog(view, "You do not own this planet", "WRONG PLANET", JOptionPane.ERROR_MESSAGE);
                return; 
            }
            gameState.initState(model, e.getActionCommand());
        }
    }
}
