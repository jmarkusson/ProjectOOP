package group6.Model;

import group6.Model.Interfaces.PlayerTurnState;

public class AttackingState implements PlayerTurnState {

    @Override
    public void placeSoldiers(Player player, int numberOfSoldiers, Planet planet) {

        throw new UnsupportedOperationException("Unimplemented method 'placeSoldiers'");
    }

    @Override
    public void attack(Player player, Planet fromPlanet, Planet toPlanet) {

        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    
}