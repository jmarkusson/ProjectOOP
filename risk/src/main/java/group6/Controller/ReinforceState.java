package group6.Controller;
import group6.Model.RiskModels.ModelFacade;
import group6.View.ReinforceView;

public class ReinforceState implements ControllerState{

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
    public ControllerState changeState() {
        return new AttackState();
    }

    @Override
    public String gameStateString() {
        return "REINFORCE";
    }
    
}