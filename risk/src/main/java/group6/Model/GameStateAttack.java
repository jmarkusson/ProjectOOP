package group6.Model;

import group6.Controller.AttackController;
import group6.Model.Interfaces.GameState;
import group6.View.AttackView;

public class GameStateAttack implements GameState {

    @Override
    public void initState(RiskModel model, String planet) {
        model.getPlanetByName(planet);
        

        AttackView attackVw = new AttackView();
        AttackController attackCtrl = new AttackController(attackVw, model);

    }

    @Override
    public int getIndex() {
        return 1;
    }
    
}
