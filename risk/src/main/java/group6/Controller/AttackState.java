package group6.Controller;

import group6.Model.RiskModels.ModelFacade;
import group6.View.AttackView;


public class AttackState implements ControllerState {
    private Integer[] soldiersOnPlanetArray;
    private String[] attackablePlanets;

    @Override
    public void initState(ModelFacade modelFacade, String planet) {
        int planetSoldiers = modelFacade.getPlanetsSoldiers(planet);

        this.soldiersOnPlanetArray = new Integer[planetSoldiers - 1];
        
        for (int i = 0; i < planetSoldiers -1; i++) {
            
            soldiersOnPlanetArray[i] = i+1;
        }
        this.attackablePlanets = modelFacade.getUnownedAdjecentPlanets(planet);

        AttackView attackVw = new AttackView(planet, modelFacade.getPlayerColor(modelFacade.getCurrentPlayer()), soldiersOnPlanetArray, attackablePlanets);
        new AttackController(attackVw, modelFacade);

    }

    @Override
    public ControllerState changeState() {
        return new FortifyState();
    }

    @Override
    public String gameStateString() {
        return "ATTACK";
    }

    
}
