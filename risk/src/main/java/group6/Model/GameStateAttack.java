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
        this.soldiersOnPlanetArray = new Integer[model.getPlanetsSoldiers(planet)];
        for (int i = 0; i < model.getCurrentPlayersFortifySoldiers(); i++) {
            int soldier = 1;
            this.soldiersOnPlanetArray[i] = soldier;
            soldier ++;
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
