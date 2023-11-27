package group6.Model;

import java.util.ArrayList;

import group6.Model.Interfaces.GameState;
import group6.View.FortifyView;

public class GameStateFortify implements GameState{

    @Override
    public void initState(RiskModel model, Planet planet) {
        int soldiers = model.getCurrentPlayer().getReinforceableSoldiers();
        //String[] soldiersAmountList = {soldiers - ()}

        //FortifyView fortifyView = new FortifyView(planet.getName(), soldiersAmountList);
    }
    
}