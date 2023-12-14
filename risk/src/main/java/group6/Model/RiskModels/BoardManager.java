package group6.Model.RiskModels;

import java.awt.Point;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import group6.Model.Board;
import group6.Model.Planet;
import group6.Model.Player;

public class BoardManager {
    private Board board;
    private ArrayList<Planet> planets;
    private PlayerManager playerManager;
    private ObserverManager observerManager;

    public BoardManager(Board board) {
        this.board = board;
        this.planets = planets;
    }

    protected void loadBoard(String[] planetNames, String[] solarsystems, String[] adjacencies, Point[][] planetPoints, Point[] solarPoints) {
        this.board.loadBoard(planetNames, solarsystems, adjacencies, planetPoints, solarPoints);
    }

    protected void distributePlanets() {
        List<Planet> shuffledPlanets = getShuffledPlanets();
    }

    public ArrayList<Planet> getPlanets() {
        return board.getPlanets();
    }

    private List<Planet> getShuffledPlanets(){
        List<Planet> shuffledPlanets = new ArrayList<>(board.getPlanets());
        Collections.shuffle(shuffledPlanets);
        return shuffledPlanets;
    }

    private void evenlyDistributeInitalPlanets(List<Planet> planets){
        for (int i = 0; i < planets.size(); i++){
            Planet currentPlanet = planets.get(i);
            Player currentPlayer = playerManager.getPlayers().get(i % playerManager.getPlayers().size());

            playerManager.assignOwnership(currentPlanet, currentPlayer);

            putPlayersSoldierOnPlanet(currentPlayer, currentPlanet, 1);


            board.getPlanetColorMap().put(currentPlanet.getName(), currentPlayer.getColor());
                
        }
    }

    private void putPlayersSoldierOnPlanet(Player player, Planet planet, int soldiers){
        planet.addSoldiers(soldiers);
        player.removeReinforceableSoldiers(soldiers);
        
        observerManager.notifyPlanetObservers(planet.getName(), soldiers, playerManager.getOwner(planet));

} 
    public Planet getPlanetByName(String planetName){
        return board.getPlanetByName(planetName);

    }
    public int getSoldiers(Planet planet){
        return planet.getSoldiers();
    }
}


