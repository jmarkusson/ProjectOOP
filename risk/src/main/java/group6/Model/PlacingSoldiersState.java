package group6.Model;

import group6.Model.Interfaces.PlayerTurnState;

public class PlacingSoldiersState implements PlayerTurnState{

    @Override
    public void placeSoldiers(Player player, int numberOfSoldiers, Planet planet) {
        
    }

    @Override
    public void attack(Player player, Planet fromPlanet, Planet toPlanet) {
        System.out.println("Place your soldiers first");
    }
    
}
