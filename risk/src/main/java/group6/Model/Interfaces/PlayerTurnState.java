package group6.Model.Interfaces;

import group6.Model.Planet;
import group6.Model.Player;

public interface PlayerTurnState {
    void placeSoldiers(Player player, int numberOfSoldiers, Planet planet);
    void attack(Player player, Planet fromPlanet, Planet toPlanet);
}
