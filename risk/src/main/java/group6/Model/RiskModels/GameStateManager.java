package group6.Model.RiskModels;

import group6.Model.Interfaces.GameStateObserver;

public class GameStateManager {

    ObserverManager observerManager;

    private String[] gamestates = {"REINFORCE", "ATTACK", "FORTIFY"};
    private int currentGameState = 0;
    
    public GameStateManager(ObserverManager observerManager){
        this.observerManager = observerManager;
    }

    public void changeState(){
        observerManager.notifyGameStateChange();
    }

    public void changeGameStateIndex(){
        currentGameState = (currentGameState + 1) % 3;
    }
    
    public String getCurrentGameState(){
        return gamestates[currentGameState];
    }

    public void setGameStateObserver(GameStateObserver gameStateObserver){
        observerManager.setGameStateObserver(gameStateObserver);
    }
}