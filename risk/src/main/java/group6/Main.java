package group6;

import group6.Controller.MainMenuController;
import group6.Model.RiskModels.ModelFacade;
import group6.View.MainMenuView;

public class Main {
    public static void main(String[] args) {
        MainMenuView view = new MainMenuView();
        ModelFacade model = new ModelFacade();
        new MainMenuController(model, view);
    }
}