package group6.Controller;

import group6.Model.RiskModels.ModelFacade;
import group6.View.FortifyView;

public class GameStateFortify implements GameState {

    String[] ownedAdjecentPlanets;
    Integer[] soldiersArray;
    FortifyView fortifyView;
    FortifyController fortifyController;

    @Override
    public void initState(ModelFacade model, String planet) {

        ownedAdjecentPlanets = model.getOwnedAdjecentPlanets(planet);

        soldiersArray = new Integer[model.getPlanetsSoldiers(planet) - 1];

        for (int i = 1; i < model.getPlanetsSoldiers(planet); i++) {
            
            soldiersArray[i-1] = i;
        }


        fortifyView = new FortifyView(planet, ownedAdjecentPlanets, soldiersArray);
        fortifyController = new FortifyController(model, fortifyView);

    }

    @Override
    public GameState changeState() {
        return new GameStateReinforce();
    }

    @Override
    public String gameStateString() {
        return "FORTIFY";
    }

   
    
}
