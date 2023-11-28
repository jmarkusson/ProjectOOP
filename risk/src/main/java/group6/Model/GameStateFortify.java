package group6.Model;


import group6.Model.Interfaces.GameState;
import group6.View.FortifyView;

public class GameStateFortify implements GameState{

    @Override
    public void initState(RiskModel model, String planet) {
        
        Integer[] soldiersArray = new Integer[model.getCurrentPlayersFortifySoldiers() + 1];

        for (int i = 0; i < model.getCurrentPlayersFortifySoldiers(); i++) {
            soldiersArray[i] = i;
        }

       FortifyView fortifyView = new FortifyView(planet, soldiersArray);
    }
    
}