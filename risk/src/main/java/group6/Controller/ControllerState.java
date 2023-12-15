package group6.Controller;

import group6.Model.RiskModels.ModelFacade;

public interface ControllerState {
     void initState(ModelFacade model, String planet);
     ControllerState changeState();
     String gameStateString();
}
