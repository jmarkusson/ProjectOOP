package group6.Model.Interfaces;

import group6.Model.RiskModel;

public interface GameState {
     void initState(RiskModel model, String planet);
     GameState changeState();
     String gameStateString();
}
