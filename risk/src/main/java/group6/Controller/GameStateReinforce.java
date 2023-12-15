package group6.Controller;
import group6.Model.RiskModels.ModelFacade;
import group6.View.ReinforceView;

public class GameStateReinforce implements GameState{

    @Override
    public void initState(ModelFacade modelFacade, String planet) {
    

        Integer[] soldiersArray = new Integer[modelFacade.getCurrentPlayersReinforcableSoldier()+1];

        for (int i = 0; i <= modelFacade.getCurrentPlayersReinforcableSoldier(); i++) {
            soldiersArray[i] = i;
        }

        ReinforceView reinforceView = new ReinforceView(planet, soldiersArray);
        new ReinforceController(modelFacade, reinforceView);
    }

    @Override
    public GameState changeState() {
        return new GameStateAttack();
    }

    @Override
    public String gameStateString() {
        return "REINFORCE";
    }
    
}