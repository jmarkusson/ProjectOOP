package group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

class SolarSystemTest {

    @Test
    void testSolarSystemProperties() {
        Planet earth = new Planet("Earth");
        Planet mars = new Planet("Mars");
        SolarSystem solarSystem = new SolarSystem("SolarSystem1", 2, new ArrayList<>(Arrays.asList(earth, mars)));

        assertEquals("SolarSystem1", solarSystem.getName(), "Name should be set correctly");
        assertEquals(2, solarSystem.getExtraTroops(), "Extra troops should be set correctly");
        assertTrue(solarSystem.getPlanetsinSolarsystem().contains(earth) && solarSystem.getPlanetsinSolarsystem().contains(mars), "Planets should be part of the solar system");
    }
}
