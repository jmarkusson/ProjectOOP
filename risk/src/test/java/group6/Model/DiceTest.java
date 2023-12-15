package group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    @Test
    void testDiceRollRange() {
        Dice dice = new Dice();
        int numberOfDice = 5;
        Integer[] rolls = dice.rollDice(numberOfDice);

        for (int roll : rolls) {
            assertTrue(roll >= 1 && roll <= 6, "Each dice roll should be between 1 and 6.");
        }
    }

    @Test
    void testDiceRollSorting() {
        Dice dice = new Dice();
        int numberOfDice = 5;
        Integer[] rolls = dice.rollDice(numberOfDice);

        for (int i = 0; i < rolls.length - 1; i++) {
            assertTrue(rolls[i] >= rolls[i + 1], "Dice rolls should be sorted in descending order.");
        }
    }
}

