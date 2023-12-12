package group6.Model;

import java.util.ArrayList;
import java.util.List;

import group6.Model.Interfaces.PlanetObserver;

public class ReinforceModel {
     private List<PlanetObserver> planetObservers = new ArrayList<>();

    public void attachPlanetObserver(PlanetObserver observer) {
        planetObservers.add(observer);
    }

    public void detachPlanetObserver(PlanetObserver observer) {
        planetObservers.remove(observer);
    }

    public void notifyPlanetObservers(String planetName, int newSoldierCount) {
        for (PlanetObserver observer : planetObservers) {
            observer.updatePlanetsSoldiers(planetName, newSoldierCount);
        }
    }

    public void reinforcePlanet(Player currentPlayer, Planet planet, int soldiers) {
        planet.addSoldiers(soldiers);
        currentPlayer.removeReinforceableSoldiers(soldiers);
        notifyPlanetObservers(planet.getName(), planet.getSoldiers());
    }


}
