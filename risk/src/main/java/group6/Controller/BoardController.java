package group6.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Point;

import group6.Model.GameStateAttack;
import group6.Model.GameStateFortify;
import group6.Model.GameStateReinforce;
import group6.Model.RiskModel;
import group6.Model.Interfaces.GameState;
import group6.View.BoardView;

public class BoardController implements ActionListener{
    
    private RiskModel model;
    private BoardView view;
    private String[] planetNames;
    private Point[][] planetPositions;
    private Point[] solarPositions;
    private GameState currentGameState;
    private int gameStateIndex;
    private ArrayList<GameState> gameStates;
    
    

    protected BoardController(RiskModel model, BoardView view){
        this.model = model;
        this.view = view;
        this.planetNames = model.getPlanetNames();
        this.planetPositions = model.getPlanetPositions();
        this.solarPositions = model.getSolarPositions();
        gameStates = new ArrayList<>();
        gameStates.add(new GameStateReinforce());
        gameStates.add(new GameStateAttack());
        gameStates.add(new GameStateFortify());
        gameStateIndex = 0;
        view.initializePlanetButtons(this);
        setCurrentGameState(gameStateIndex);
    }



    public void setCurrentGameState(int gameStateIndex){
        currentGameState = gameStates.get(gameStateIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("NEXT")){
            gameStateIndex = (gameStateIndex + 1) % 3;
            view.updateCurrentStateLabel(gameStateIndex);
            setCurrentGameState(gameStateIndex);
        }
        else {
        currentGameState.initState(model, e.getActionCommand());
        }
    }
}