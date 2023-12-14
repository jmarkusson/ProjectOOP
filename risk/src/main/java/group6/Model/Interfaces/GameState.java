package group6.Model.Interfaces;

import group6.Model.RiskModel;
import group6.Model.RiskModels.ModelFacade;

public interface GameState {
     void initState(ModelFacade model, String planet);
     GameState changeState();
     String gameStateString();
}
