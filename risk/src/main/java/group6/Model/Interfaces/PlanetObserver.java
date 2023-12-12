package group6.Model.Interfaces;

import java.awt.Color;

public interface PlanetObserver {
    void updatePlanetsSoldiers(String planetName, int newSoldiersCount);
    void updatePlanetColor(String planetName, Color  color);
}
