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
import group6.Model.Interfaces.Ownable;

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

    public String[] getPlanetNames(){
        return board.getPlanetNames();
    }

    public Point[][] getPlanetPositions() {
        return board.getPlanetPositions();
    }

    public Point[] getSolarPositions(){
        return board.getSolarPositions();
    }

    public HashMap<String, Color> getPlanetColorMap(){
        return board.getPlanetColorMap();
    }

    public int getPlanetsSoldiers(String planet){
        return board.getPlanetByName(planet).getSoldiers();
    }

    public String[] getUnownedAdjecentPlanets(String planet){
        ArrayList<Planet> adjacentPlanets = getAdjecentPlanets(getPlanetByName(planet));
        ArrayList<Ownable> playersOwnables = playerManager.getPlayerOwnables();
        ArrayList<String> unownedAdjecentPlanets = new ArrayList<>();

        for (Planet adjacentPlanet : adjacentPlanets) {
            if (!playersOwnables.contains(adjacentPlanet)) {
                unownedAdjecentPlanets.add(adjacentPlanet.getName());
            }
        }

        return unownedAdjecentPlanets.toArray(new String[0]);

    }
    public String[] getOwnedAdjecentPlanets(String planet){
        ArrayList<Planet> adjacentPlanets = getAdjecentPlanets(getPlanetByName(planet));
        ArrayList<Ownable> playersOwnables = playerManager.getPlayerOwnables();
        ArrayList<String> ownedAdjecentPlanets = new ArrayList<>();

        for (Planet adjacentPlanet : adjacentPlanets) {
            if (playersOwnables.contains(adjacentPlanet)) {
                ownedAdjecentPlanets.add(adjacentPlanet.getName());
            }
        }

        return ownedAdjecentPlanets.toArray(new String[0]);
    }
    public ArrayList<Planet> getAdjecentPlanets(Planet planet){
        return planet.getAdjecentPlanets();
    }
   
}


