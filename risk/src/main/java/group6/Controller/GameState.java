package group6.Controller;

import group6.Model.RiskModels.ModelFacade;

public interface GameState {
     void initState(ModelFacade model, String planet);
     GameState changeState();
     String gameStateString();
}
