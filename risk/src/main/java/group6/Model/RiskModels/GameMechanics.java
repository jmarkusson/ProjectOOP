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
    private PlayerManager playerManager;
    private BoardManager boardManager;
    private Battle battle;
    
    

    public GameMechanics(PlayerManager playerManager, BoardManager boardManager, Dice dice){
        this.playerManager = playerManager;
        this.boardManager = boardManager;
        this.battle = new Battle(new Dice());
    }

    public void fortifyPlanet(Planet originPlanet, Planet fortifyPlanet, int soldiers, Player owner){
        boardManager.removeSoldiersFromPlanet(originPlanet, soldiers);
        boardManager.addSoldiersToPlanet(fortifyPlanet, soldiers);
    }

    public void reinforcePlanet(Planet planet, int soldiers){
        boardManager.addSoldiersToPlanet(planet, soldiers);
        playerManager.removeReinforceableSoldiers(playerManager.getCurrentPlayer(), soldiers);
        playerManager.addSoldiers(playerManager.getCurrentPlayer(), soldiers);
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

    public void attackPlanet(Planet attackingPlanet, Planet defendingPlanet, int amountOfSoldiers) {

        BattleResult result = battle.conductBattle(attackingPlanet, defendingPlanet, amountOfSoldiers);

        Player attackingPlayer = playerManager.getOwner(attackingPlanet);
        Player defendingPlayer = playerManager.getOwner(defendingPlanet);

        int attackingSoldiersLost = result.getAttackingSoldiersLost();
        int defendingSoldiersLost = result.getDefendingSoldiersLost();

        playerManager.removeSoldiers(attackingPlayer, attackingSoldiersLost);
        playerManager.removeSoldiers(defendingPlayer, defendingSoldiersLost);

        if(boardManager.getSoldiers(defendingPlanet) == 0){
            playerManager.assignOwnership(defendingPlanet, attackingPlayer);
            playerManager.removeOwnership(defendingPlanet, defendingPlayer);
            boardManager.addSoldiersToPlanet(defendingPlanet, result.getRemainingAttackers());
            boardManager.removeSoldiersFromPlanet(attackingPlanet, result.getRemainingAttackers());
        }
    }
  
}
