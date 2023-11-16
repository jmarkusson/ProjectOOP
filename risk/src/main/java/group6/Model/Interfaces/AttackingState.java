package group6.Model.Interfaces;

import group6.Model.Planet;
import group6.Model.Player;

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