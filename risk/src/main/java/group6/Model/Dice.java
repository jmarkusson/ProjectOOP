package group6.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dice {

    private final Random random = new Random();

    // Method to roll dice
    public Integer[] rollDice(int numberOfDice) {
        Integer[] rolls = new Integer[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            rolls[i] = random.nextInt(6) + 1;
        } 
        Arrays.sort(rolls, Collections.reverseOrder());
        return rolls;   
    }
}

