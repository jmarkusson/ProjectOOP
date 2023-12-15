package group6.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import group6.Model.Interfaces.GameStateObserver;
import group6.Model.RiskModels.ModelFacade;
import group6.View.BoardView;

public class BoardController implements ActionListener, GameStateObserver{
    
    private ModelFacade modelFacade;
    private BoardView view;
    private GameState gameState;
    
    protected BoardController(ModelFacade modelFacade, BoardView view){
        this.modelFacade = modelFacade;
        this.view = view;
        modelFacade.setGameStateObserver(this);
        view.initializePlanetButtons(this);
        gameState = new GameStateReinforce();
        view.getCurrentPlayerLabel().setText(modelFacade.getCurrentPlayer().getName()+ "'s TURN");
        view.getCurrentPlayerLabel().setForeground(modelFacade.getCurrentPlayer().getColor());
        modelFacade.attach(view);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!modelFacade.isOwnedCurrentPlayer(modelFacade.getPlanetByName(e.getActionCommand()))){
            JOptionPane.showMessageDialog(view, "You do not own this planet", "WRONG PLANET", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        gameState.initState(modelFacade, e.getActionCommand());
        }
    


    @Override
    public void actOnStateChange() {
        gameState = gameState.changeState();
        modelFacade.changeGameStateIndex();
        view.updateCurrentStateLabel(modelFacade.getCurrentGameState());
        
    }
}
