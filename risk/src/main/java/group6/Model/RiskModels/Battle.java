package group6.Model.RiskModels;

import group6.Model.Dice;
import group6.Model.Planet;

public class Battle {
    private Dice dice;

    public Battle(Dice dice) {
        this.dice = dice;
    }

    public BattleResult conductBattle(Planet attackingPlanet, Planet defendingPlanet, int amountOfSoldiers) {
        int remainingAttackers = amountOfSoldiers;
        int defendingSoldiersLost = 0;
        int attackingSoldiersLost = 0;

        while (remainingAttackers > 0 && defendingPlanet.getSoldiers() > 0) {
            Integer[] attackRolls = dice.rollDice(Math.min(remainingAttackers, 3));
            Integer[] defendRolls = dice.rollDice(Math.min(defendingPlanet.getSoldiers(), 2));

            for (int i = 0; i < Math.min(attackRolls.length, defendRolls.length); i++) {
                if (attackRolls[i] > defendRolls[i]) {
                    defendingPlanet.removeSoldiers(1);
                    defendingSoldiersLost++;
                } else {
                    remainingAttackers--;
                    attackingSoldiersLost++;
                    attackingPlanet.removeSoldiers(1);
                }
                
            }
        }

        return new BattleResult(attackingSoldiersLost, defendingSoldiersLost, remainingAttackers);
    }
}
