package group6.Model.RiskModels;

import java.awt.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

import group6.Model.Planet;
import group6.Model.Player;
import group6.Model.Dice;

public class GameMechanics {
    private Dice dice;
    private PlayerManager playerManager;
    

    public GameMechanics(PlayerManager playerManager){

        this.playerManager = playerManager;
    }
    public void fortifyPlanet(Planet originPlanet, Planet fortifyPlanet, int soldiers, Player owner){
        originPlanet.removeSoldiers(soldiers);
        fortifyPlanet.addSoldiers(soldiers);
    }

    public void reinforcePlanet(Planet planet, int soldiers){
        planet.addSoldiers(soldiers);
    }

    public Boolean isReinforceDone(){
        Boolean reinforceDone = false;
    
        if(playerManager.getCurrentPlayersReinforcableSoldier() == 0){
            reinforceDone = true;
            // Set Reinforcable Soldier back to the amount of bonustroops so it is correct at the start of the next round
            playerManager.resetReinforcableSoldierForNextTurn(playerManager.getCurrentPlayer());

        }

        return reinforceDone;
    }

    public void attackPlanet(Planet planetAttackingFrom, Planet planetToAttack, int amountOfSoldiers) {
        int remainingAttackers = amountOfSoldiers;
        int defendingSoldiersLost = 0;
        int attackingSoldiersLost = 0;

            while (remainingAttackers > 0 && planetToAttack.getSoldiers() > 0) {
            int attackingDice = Math.min(remainingAttackers, 3);
            int defendingDice = Math.min(planetToAttack.getSoldiers(), 2);
            //dice rolls
            Integer[] attackRolls = rollDice(attackingDice);
            Integer[] defendRolls = rollDice(defendingDice);

            Arrays.sort(attackRolls, Collections.reverseOrder());
            Arrays.sort(defendRolls, Collections.reverseOrder());

            for (int i = 0; i < Math.min(attackingDice, defendingDice); i++) {
                int attackValue = attackRolls[i];
                int defendValue = defendRolls[i];

                handleBattleResult(attackValue, defendValue, planetAttackingFrom, planetToAttack,
                        remainingAttackers, defendingSoldiersLost, attackingSoldiersLost);
            }
        }
        updatePlayersSoldiers(planetAttackingFrom, planetToAttack, 
        attackingSoldiersLost, defendingSoldiersLost, 
        remainingAttackers);
    }
        
    

    private void handleBattleResult(int attackValue, int defendValue, Planet planetAttackingFrom,
                                Planet planetToAttack, int remainingAttackers, int defendingSoldiersLost,
                                int attackingSoldiersLost) {
        if (attackValue > defendValue) {
            planetToAttack.removeSoldiers(1);
            defendingSoldiersLost++;
        } else {
            remainingAttackers--;
            attackingSoldiersLost++;
            planetAttackingFrom.removeSoldiers(1);
        }
    }

    private void updatePlayersSoldiers(Planet planetAttackingFrom, Planet planetToAttack, int attackingSoldiersLost, int defendingSoldiersLost, int remainingAttackers) {
        Player attackingPlayer = playerManager.getOwner(planetAttackingFrom);
        Player defendingPlayer = playerManager.getOwner(planetToAttack);

        attackingPlayer.setSoldiers(attackingPlayer.getSoldiers() - attackingSoldiersLost);
        defendingPlayer.setSoldiers(defendingPlayer.getSoldiers() - defendingSoldiersLost);

        if (planetToAttack.getSoldiers() == 0) {
            handleSuccessfulAttack(planetAttackingFrom, planetToAttack, remainingAttackers,
                    attackingPlayer, defendingPlayer);
        }
    }

    private void handleSuccessfulAttack(Planet planetAttackingFrom, Planet planetToAttack,
                                   int remainingAttackers, Player attackingPlayer,
                                   Player defendingPlayer) {
        playerManager.assignOwnership(planetToAttack, attackingPlayer);
        playerManager.removeOwnership(planetToAttack, defendingPlayer);
        planetToAttack.addSoldiers(remainingAttackers);
        planetAttackingFrom.removeSoldiers(remainingAttackers);
    }
    private Integer[] rollDice(int dices){
        return this.dice.rollDice(dices);

    }
  
}
