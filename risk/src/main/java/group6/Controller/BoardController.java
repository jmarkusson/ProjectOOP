package group6.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import group6.Model.GameStateReinforce;
import group6.Model.RiskModel;
import group6.Model.Interfaces.GameState;
import group6.Model.Interfaces.GameStateObserver;
import group6.View.BoardView;

public class BoardController implements ActionListener, GameStateObserver{
    
    private RiskModel model;
    private BoardView view;
    private GameState gameState;
    
    protected BoardController(RiskModel model, BoardView view){
        this.model = model;
        this.view = view;
        model.setGameStateObserver(this);
        view.initializePlanetButtons(this);
        gameState = new GameStateReinforce();
        view.getCurrentPlayerLabel().setText(model.getCurrentPlayer().getName()+ "'s TURN");
        view.getCurrentPlayerLabel().setForeground(model.getCurrentPlayer().getColor());
        model.attach(view);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!model.isOwnedCurrentPlayer(model.getPlanetByName(e.getActionCommand()))){
            JOptionPane.showMessageDialog(view, "You do not own this planet", "WRONG PLANET", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        gameState.initState(model, e.getActionCommand());
        }
    


    @Override
    public void actOnStateChange() {
        gameState = gameState.changeState();
        model.changeGameStateIndex();
        view.updateCurrentStateLabel(model.getCurrentGameState());
        
    }
}
