package group6.Model.Interfaces;

import group6.Model.Planet;
import group6.Model.Player;

public class PlacingSoldiersState implements PlayerTurnState{

    @Override
    public void placeSoldiers(Player player, int numberOfSoldiers, Planet planet) {
        
    }

    @Override
    public void attack(Player player, Planet fromPlanet, Planet toPlanet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }
    
}
