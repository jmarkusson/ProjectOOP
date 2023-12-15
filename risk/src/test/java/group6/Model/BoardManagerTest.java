package group6.Model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import group6.Model.RiskModels.BoardManager;
import group6.Model.RiskModels.ObserverManager;
import group6.Model.RiskModels.PlayerManager;

import java.awt.Point;
import java.util.ArrayList;

public class BoardManagerTest {

    private BoardManager boardManager;
    String[] testPlanetNames = {"Earth", "Mars"};
    String[] testSolarSystems = {"SolarSystem1,2,Earth", "SolarSystem2,4,Mars"};
    Point[][] testPlanetPositions = {{new Point(0, 0), new Point(1, 1)}, {new Point(2, 2), new Point(3, 3)}};
    Point[] testSolarPositions = {new Point(4, 4), new Point(5, 5)};
    String[] testAdjacencies = {"Earth,Mars", "Mars,Earth"};

    void setUp() {

        PlayerManager playerManager = new PlayerManager(new PlayerOwnership());
        ObserverManager observerManager = new ObserverManager();
        boardManager = new BoardManager(playerManager, observerManager);
        boardManager.loadBoard(testPlanetNames, testSolarSystems, testAdjacencies, testPlanetPositions, testSolarPositions);
    }

    @Test
    void testInitializationOfBoard() {
        
        setUp();

        // Get the planets from the board
        Planet earth = boardManager.getPlanetByName("Earth");
        Planet mars = boardManager.getPlanetByName("Mars");

        // Assertions to check if the data was set correctly
        assertArrayEquals(testPlanetNames, boardManager.getPlanetNames(), "Planet names should be set correctly.");
        assertArrayEquals(testPlanetPositions, boardManager.getPlanetPositions(), "Planet positions should be set correctly.");
        assertArrayEquals(testSolarPositions, boardManager.getSolarPositions(), "Solar positions should be set correctly.");

        assertTrue(mars.getAdjecentPlanets().contains(earth), "Mars should be adjacent to Earth");
        assertTrue(earth.getAdjecentPlanets().contains(mars), "Earth should be adjacent to Mars");
    }

    @Test
    void testGetPlanetNames() {
        setUp();
        assertArrayEquals(testPlanetNames, boardManager.getPlanetNames(), "The returned planet names should match the initialized data.");
    }

    @Test
    void testGetPlanetPositions() {
        setUp();
        assertArrayEquals(testPlanetPositions, boardManager.getPlanetPositions(), "The returned planet positions should match the initialized data.");
    }

    @Test
    void testGetSolarPositions() {
        setUp();
        assertArrayEquals(testSolarPositions, boardManager.getSolarPositions(), "The returned solar positions should match the initialized data.");
    }

    @Test
    void testGetPlanets() {
        setUp();
        ArrayList<Planet> planets = boardManager.getPlanets();

        // Check the number of planets
        assertEquals(testPlanetNames.length, planets.size(), "The number of planets should match the initialized data.");

        // Check each planet's properties
        for (int i = 0; i < testPlanetNames.length; i++) {
            Planet planet = planets.get(i);
            assertEquals(testPlanetNames[i], planet.getName(), "Planet name should match.");
        }
    }

    @Test
    void testGetPlanetByName() {
        setUp();
        for (String planetName : testPlanetNames) {
            Planet planet = boardManager.getPlanetByName(planetName);
            assertNotNull(planet);
            assertEquals(planetName, planet.getName(), "Planet name should match the name provided.");
        }
    }
}
