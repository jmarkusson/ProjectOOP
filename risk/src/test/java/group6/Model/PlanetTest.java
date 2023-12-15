package group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

class PlanetTest {

    @Test
    void testAddAndRemoveSoldiers() {
        Planet planet = new Planet("Earth");
        planet.addSoldiers(5);
        assertEquals(5, planet.getSoldiers(), "Adding soldiers should increase the count");

        planet.removeSoldiers(3);
        assertEquals(2, planet.getSoldiers(), "Removing soldiers should decrease the count");
    }

    @Test
    void testSetAdjacencies() {
        Planet planet = new Planet("Earth");
        Planet mars = new Planet("Mars");
        Planet venus = new Planet("Venus");

        planet.setAdjacencies(new ArrayList<>(Arrays.asList(mars, venus)));
        assertTrue(planet.getAdjecentPlanets().contains(mars) && planet.getAdjecentPlanets().contains(venus), "Adjacencies should be set correctly");
    }
}

