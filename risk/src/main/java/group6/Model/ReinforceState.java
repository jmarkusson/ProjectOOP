package group6.Model;

import group6.Model.Interfaces.PlayerTurnState;

public class ReinforceState implements PlayerTurnState{

    @Override
    public void placeSoldiers(Player player, int numberOfSoldiers, Planet planet) {
        
    }

    @Override
    public void attack(Player player, Planet fromPlanet, Planet toPlanet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void fortify(Player player, Planet planet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fortify'");
    }
    
}
