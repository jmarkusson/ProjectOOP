package group6;

import group6.Controller.MainMenuController;
import group6.Model.RiskModel;
import group6.View.MainMenuView;

public class Main {
    public static void main(String[] args) {
        MainMenuView view = new MainMenuView();
        RiskModel model = new RiskModel();
        

        MainMenuController controller = new MainMenuController(model, view);
    }
}