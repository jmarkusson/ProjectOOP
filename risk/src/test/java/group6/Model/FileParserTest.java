package group6.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group6.Model.RiskModels.FileParser;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;

class FileParserTest {

    private FileParser fileParser;

    void setUp() {
        fileParser = new FileParser();
    }

    @Test
    void testParseFile() {
        setUp();
        fileParser.parseFile();

        // Assuming these getters exist and return the parsed data
        String[] planetNames = fileParser.getPlanetNamesArray();
        String[] solarSystems = fileParser.getSolarsystemsArray();
        String[] adjacencies = fileParser.getAdjacenciesArray();
        Point[][] planetPoints = fileParser.getPlanetPointsArray();
        Point[] solarPoints = fileParser.getSolarPointsArray();

        assertNotNull(planetNames);
        assertNotNull(solarSystems);
        assertNotNull(adjacencies);
        assertNotNull(planetPoints);
        assertNotNull(solarPoints);

        assertTrue(planetNames.length > 0, "There should be at least one planet name");
        assertTrue(solarSystems.length > 0, "There should be at least one solar system");
        assertTrue(adjacencies.length > 0, "There should be at least one adjacency");
        assertTrue(planetPoints.length > 0, "There should be at least one set of planet points");
        assertTrue(solarPoints.length > 0, "There should be at least one solar point");

        // Check if the data is parsed correctly
        assertEquals("Dagobah", planetNames[0], "The first planet name should be Earth");
        assertEquals("Zephyrion Expanse,4,Coruscant,Tatooine,Hoth,Dagobah", solarSystems[0], "The first solar system should be Zephyrion Expanse,4,Coruscant,Tatooine,Hoth,Dagobah");
        assertEquals("Coruscant,Dagobah,Mustafar", adjacencies[0], "The first adjacency should be Coruscant,Dagobah,Mustafar");
    }
}

