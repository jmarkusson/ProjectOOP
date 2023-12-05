package group6.Model;
import group6.Controller.ReinforceController;
import group6.Model.Interfaces.GameState;
import group6.View.ReinforceView;

public class GameStateReinforce implements GameState{

    @Override
    public void initState(RiskModel model, String planet) {
        
        Integer[] soldiersArray = new Integer[model.getCurrentPlayersFortifySoldiers() + 1];

        for (int i = 0; i < model.getCurrentPlayersFortifySoldiers(); i++) {
            soldiersArray[i] = i;
        }

       ReinforceView fortifyView = new ReinforceView(planet, soldiersArray);
       ReinforceController controller = new ReinforceController(model, fortifyView);
    }
    
}