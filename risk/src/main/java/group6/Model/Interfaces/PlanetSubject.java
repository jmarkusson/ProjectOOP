package group6.Model.Interfaces;

public interface PlanetSubject {
    void attach(PlanetObserver observer);
    void detach(PlanetObserver observer);
    void notifyObservers(String planetName, int newSoldierCount);
}
