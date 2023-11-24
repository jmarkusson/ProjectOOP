package group6.Model.Interfaces;

import group6.Model.Planet;
import group6.Model.RiskModel;

public interface GameState {
     void initState(RiskModel model, Planet planet);
}
