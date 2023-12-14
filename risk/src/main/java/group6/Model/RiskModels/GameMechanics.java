package group6.Model.RiskModels;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import group6.Model.Planet;
import group6.Model.Player;

public class GameMechanics {
    
    PlayerManager playerManager;
    

    public GameMechanics(PlayerManager playerManager){

        this.playerManager = playerManager;
    }
    public void fortifyPlanet(Planet originPlanet, Planet fortifyPlanet, int soldiers, Player owner){
        originPlanet.removeSoldiers(soldiers);
        fortifyPlanet.addSoldiers(soldiers);
    }

    public void attackPlanet(Planet planetAttackingFrom, Planet planetToAttack, int amountOfSoldiers){


        int remainingAttackers = amountOfSoldiers;
        int defendingSoldiersLost = 0;
        int attackingSoldiersLost = 0;

        while (remainingAttackers > 0 && planetToAttack.getSoldiers() > 0) {
            int attackingDice = Math.min(remainingAttackers, 3);
            int defendingDice = Math.min(planetToAttack.getSoldiers(), 2);

            Integer[] attackRolls = rollDice(attackingDice);
            Integer[] defendRolls = rollDice(defendingDice);

            Arrays.sort(attackRolls, Collections.reverseOrder());
            Arrays.sort(defendRolls, Collections.reverseOrder());

            for (int i = 0; i < Math.min(attackingDice, defendingDice); i++) {
                int attackValue = attackRolls[i];
                int defendValue = defendRolls[i];

                if (attackValue > defendValue) {
                    planetToAttack.removeSoldiers(1);
                    defendingSoldiersLost++;
                } else {
                    remainingAttackers--;
                    attackingSoldiersLost++;
                    planetAttackingFrom.removeSoldiers(1);
                }
            }
        }

        Player attackingPlayer = playerManager.getOwner(planetAttackingFrom);
        Player defendingPlayer = playerManager.getOwner(planetToAttack);
        attackingPlayer.setSoldiers(attackingPlayer.getSoldiers() - attackingSoldiersLost);
        defendingPlayer.setSoldiers(defendingPlayer.getSoldiers() - defendingSoldiersLost);

        if (planetToAttack.getSoldiers() == 0) {
            playerManager.assignOwnership(planetToAttack, attackingPlayer);
            playerManager.removeOwnership(planetToAttack, defendingPlayer);
            planetToAttack.addSoldiers(remainingAttackers);
            planetAttackingFrom.removeSoldiers(remainingAttackers);
        }

       
    }
    
    
    private Integer[] rollDice(int numberOfDice) {
        Random random = new Random();
        Integer[] rolls = new Integer[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            rolls[i] = random.nextInt(6) + 1;
        }
        return rolls;
    }
}
