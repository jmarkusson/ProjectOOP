package group6.Model;

import group6.Model.Interfaces.PlayerTurnState;

public class FortifyState implements PlayerTurnState{

    @Override
    public void placeSoldiers(Player player, int numberOfSoldiers, Planet planet) {
        if (player.removeFortifySoldiers(numberOfSoldiers)) {
            planet.addSoldiers(numberOfSoldiers);
        }
        else{
            System.out.println("You only got "+player.getFortifySoldiers()+" left to place");
        }
    }

    @Override
    public void attack(Player player, Planet fromPlanet, Planet toPlanet) {
        System.out.println("Your in the fortify state");
    }

    @Override
    public void fortify(Player player, Planet planet) {
        System.out.println("Your in the fortify state");
    }
    
}
