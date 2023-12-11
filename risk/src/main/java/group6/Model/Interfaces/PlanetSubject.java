package group6.Model.Interfaces;

public interface PlanetSubject {
    void attach(PlanetObserver planetObserver);
    void detach(PlanetObserver planetObserver);
    void notifyPlanetObservers(String planetName, int newSoldierCount);
}
