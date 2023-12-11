package group6.Model;

import group6.Controller.FortifyController;
import group6.Model.Interfaces.GameState;
import group6.View.FortifyView;

public class GameStateFortify implements GameState {

    String[] ownedAdjecentPlanets;
    Integer[] soldiersArray;
    FortifyView fortifyView;
    FortifyController fortifyController;

    @Override
    public void initState(RiskModel model, String planet) {

        ownedAdjecentPlanets = model.getOwnedAdjecentPlanets(planet);

        soldiersArray = new Integer[model.getPlanetsSoldiers(planet)];

        for (int i = 0; i < model.getPlanetsSoldiers(planet); i++) {
            
            soldiersArray[i] = i+1;
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
