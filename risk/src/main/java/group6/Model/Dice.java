package group6.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dice {

    private Random random;

    public Dice() {
        this.random = new Random();
    }

    // Method to roll dice
    public Integer[] rollDice(int numberOfDice) {
    Random random = new Random();
    Integer[] rolls = new Integer[numberOfDice];
    for (int i = 0; i < numberOfDice; i++) {
        rolls[i] = random.nextInt(6) + 1;
    }
    return rolls;
    }
    public List<Integer> roll(int numberOfDice) {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            // Dice roll (1-6)
            rolls.add(random.nextInt(6) + 1); 
        }
        // Sort in descending order
        Collections.sort(rolls, Collections.reverseOrder()); 
        return rolls;
    }

    // Method to simulate an attack, returning a single list with both attack and defend rolls
    // Called by model
    public List<Integer> rollDices(int numberOfAttackDice, int numberOfDefendDice) {
        List<Integer> combinedRolls = new ArrayList<>();

        // Roll and add attack dice
        combinedRolls.addAll(roll(numberOfAttackDice));

        // Roll and add defend dice
        combinedRolls.addAll(roll(numberOfDefendDice));

        return combinedRolls;
    }
}

