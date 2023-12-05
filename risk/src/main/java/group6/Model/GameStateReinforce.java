package group6.Model;
import group6.Controller.ReinforceController;
import group6.Model.Interfaces.GameState;
import group6.View.ReinforceView;

public class GameStateReinforce implements GameState{

    @Override
    public void initState(RiskModel model, String planet) {
        // check if country is owned??
        
        Integer[] soldiersArray = new Integer[model.getCurrentPlayersReinforcableSoldier() + 1];

        for (int i = 0; i < model.getCurrentPlayersReinforcableSoldier(); i++) {
            soldiersArray[i] = i;
        }

       ReinforceView reinforceView = new ReinforceView(planet, soldiersArray);
       ReinforceController controller = new ReinforceController(model, reinforceView);
    }

    @Override
    public int getIndex() {
        return 0;
    }
    
}