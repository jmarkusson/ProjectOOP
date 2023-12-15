package group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

class PlayerTest {

    @Test
    void testSoldiersAndBonusTroopsHandling() {
        Player player = new Player("Alice", Color.RED, 0);
        player.setSoldiers(10);
        assertEquals(10, player.getSoldiers(), "Soldiers should be set correctly");

        player.addSoldiers(5);
        assertEquals(15, player.getSoldiers(), "Adding soldiers should work correctly");

        player.setBonusSoldiers(3);
        player.setReinforceableSoldiers(player.getBonusSoldiers());
        assertEquals(3, player.getReinforceableSoldiers(), "Bonus soldiers should be set correctly");
    }

    @Test
    void testOwnedPlanetsHandling() {
        Player player = new Player("Alice", Color.RED, 0);
        Planet earth = new Planet("Earth");
        Planet mars = new Planet("Mars");
        List<Planet> ownedPlanets = new ArrayList<>();
        ownedPlanets.add(earth);
        ownedPlanets.add(mars);
        player.setPlanetsOwned(ownedPlanets);

        assertTrue(player.getPlanetsOwned().contains(earth) && player.getPlanetsOwned().contains(mars), "Owned planets should be set correctly");
    }

    @Test
    void testFortifySoldiersHandling() {
        Player player = new Player("Alice", Color.RED, 0);
        player.addFortifySoldiers(5);
        assertEquals(5, player.getFortifySoldiers(), "Fortify soldiers should be added correctly");

        boolean result = player.removeFortifySoldiers(3);
        assertTrue(result, "Removing fortify soldiers should return true");
        assertEquals(2, player.getFortifySoldiers(), "Fortify soldiers should be removed correctly");

        result = player.removeFortifySoldiers(5);
        assertFalse(result, "Removing more fortify soldiers than available should return false");
    }

    @Test
    void testPlayerColorAndName() {
        Player player = new Player("Alice", Color.RED, 0);
        assertEquals("Alice", player.getName(), "Player name should be set correctly");
        assertEquals(Color.RED, player.getColor(), "Player color should be set correctly");

        player.setName("Bob");
        assertEquals("Bob", player.getName(), "Player name should be changeable");

        player.setColor(Color.BLUE);
        assertEquals(Color.BLUE, player.getColor(), "Player color should be changeable");
    }
}

