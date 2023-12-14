package group6.Model.RiskModels;

import java.util.ArrayList;
import java.util.List;

import group6.Model.Player;
import group6.Model.Interfaces.PlanetObserver;
import group6.Model.Interfaces.PlayerObserver;

public class ObserverManager {
    private static final ObserverManager observerManager = new ObserverManager();
    private PlanetObserver planetObserver;
    
    private List<PlanetObserver> planetObservers = new ArrayList<>();
    private List<PlayerObserver> playerObservers = new ArrayList<>();
    public static ObserverManager getInstance(){
        return observerManager;
    }

    public void attach(PlanetObserver planetObserver) {
        planetObservers.add(planetObserver);
    }


    public void detach(PlanetObserver planetObserver) {
        planetObservers.remove(planetObserver);
    }


    public void notifyPlanetObservers(String planetName, int newSoldierCount, Player playerOwner) {
        for (PlanetObserver observer : planetObservers) {
            observer.updatePlanetsSoldiers(planetName, newSoldierCount);
            observer.updatePlanetColor(planetName, playerOwner.getColor());
        }
    }

    public void addPlayerObserver(PlayerObserver playerObserver) {
        playerObservers.add(playerObserver);
    }

    public void notifyPlayerObservers() {
        for (PlayerObserver playerObserver : playerObservers) {
            playerObserver.updatePlayerInfo();
        }
    }
    
}
