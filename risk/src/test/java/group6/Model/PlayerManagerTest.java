package group6.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group6.Model.RiskModels.PlayerManager;
import java.awt.Color;

public class PlayerManagerTest {

    private PlayerManager playerManager;

    void setUp() {
        playerManager = new PlayerManager(new PlayerOwnership());
    }

    void initPlayers() {
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player1");
        playerNames.add("Player2");

        ArrayList<Color> playerColors = new ArrayList<>();
        playerColors.add(Color.RED);
        playerColors.add(Color.BLUE);

        playerManager.initPlayers(playerNames, playerColors);
    }

    @Test
    void testInitPlayers() {
        setUp();
        initPlayers();

        assertNotNull(playerManager.getPlayers());
        assertEquals(2, playerManager.getPlayers().size());
        assertEquals("Player1", playerManager.getPlayers().get(0).getName());
        assertEquals(Color.RED, playerManager.getPlayers().get(0).getColor());
    }

    @Test
    void testOwnershipAssignments() {
        setUp();
        Player player = new Player("Player1", Color.RED, 0);
        Planet planet = new Planet("Earth");

        playerManager.assignOwnership(planet, player);
        assertEquals(player, playerManager.getOwner(planet));

        playerManager.removeOwnership(planet, player);
        assertNull(playerManager.getOwner(planet));
    }

    @Test
    void testTurnManagement() {
        setUp();
        initPlayers();

        // Assuming two players have been initialized
        Player firstPlayer = playerManager.getCurrentPlayer();
        playerManager.nextPlayer();
        Player secondPlayer = playerManager.getCurrentPlayer();

        assertNotEquals(firstPlayer, secondPlayer);
        playerManager.nextPlayer();
        assertEquals(firstPlayer, playerManager.getCurrentPlayer());
    }

    @Test
    void testReinforcementAndSoldierManagement() {
        setUp();
        Player player = new Player("Player1", Color.RED, 0);
        player.setSoldiers(10);
        player.setReinforceableSoldiers(5);

        player.addSoldiers(3);
        assertEquals(13, player.getSoldiers());

        player.removeSoldiers(2);
        assertEquals(11, player.getSoldiers());

        player.removeReinforceableSoldiers(3);
        assertEquals(2, player.getReinforceableSoldiers());
    }


    
}
