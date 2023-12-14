package group6.Model.RiskModels;

public class BattleResult {
    private final int attackingSoldiersLost;
    private final int defendingSoldiersLost;
    private final int remainingAttackers;

    public BattleResult(int attackingSoldiersLost, int defendingSoldiersLost, int remainingAttackers) {
        this.attackingSoldiersLost = attackingSoldiersLost;
        this.defendingSoldiersLost = defendingSoldiersLost;
        this.remainingAttackers = remainingAttackers;
    }

    // Getters
    public int getAttackingSoldiersLost() { 
        return this.attackingSoldiersLost; 
    }

    public int getDefendingSoldiersLost() {
         return this.defendingSoldiersLost; 
    }

    public int getRemainingAttackers() {
         return this.remainingAttackers; 
    }
}