package group6.Model;

import javax.swing.JFrame;

import group6.Controller.AttackController;
import group6.Model.Interfaces.GameState;
import group6.View.AttackView;

public class GameStateAttack implements GameState {
    private Integer[] soldiersOnPlanetArray;
    private String[] attackablePlanets;

    @Override
    public void initState(RiskModel model, String planet) {
        model.getPlanetByName(planet);
        this.soldiersOnPlanetArray = new Integer[model.getPlanetsSoldiers(planet) - 1];
        
        for (int i = 0; i < model.getPlanetsSoldiers(planet)-1; i++) {
            
            soldiersOnPlanetArray[i] = i+1;
        }
        this.attackablePlanets = model.getUnownedAdjecentPlanets(planet);

        AttackView attackVw = new AttackView(planet, model.getPlayerColor(model.getCurrentPlayer()), soldiersOnPlanetArray, attackablePlanets);
        AttackController attackCtrl = new AttackController(attackVw, model);

    }

    @Override
    public GameState changeState() {
        return new GameStateFortify();
    }

    @Override
    public String gameStateString() {
        return "ATTACK";
    }

    
}
