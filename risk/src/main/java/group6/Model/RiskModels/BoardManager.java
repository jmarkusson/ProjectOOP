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

    public BoardManager(Board board, PlayerManager playerManager, ObserverManager observerManager) {
        this.board = board;
        this.planets = planets;
        this.playerManager = playerManager;
        this.observerManager = observerManager;

    }

    protected void loadBoard(String[] planetNames, String[] solarsystems, String[] adjacencies, Point[][] planetPoints, Point[] solarPoints) {
        this.board.loadBoard(planetNames, solarsystems, adjacencies, planetPoints, solarPoints);
    }

    protected void distributePlanets() {
        List<Planet> shuffledPlanets = getShuffledPlanets();
        playerManager.initializePlayersReinforceableSoldiers();
        evenlyDistributeInitalPlanets(shuffledPlanets);
        distributeRemainingSoldiers(shuffledPlanets);
        playerManager.resetAllReinforcableSoldierForNextTurn();
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
            ArrayList<Player> players = playerManager.getPlayers();
            
            Planet currentPlanet = planets.get(i);
            Player currentPlayer = players.get(i % players.size());

            assignPlanetToPlayer(currentPlanet, currentPlayer);

            board.getPlanetColorMap().put(currentPlanet.getName(), currentPlayer.getColor());
                
        }
    }

    private void assignPlanetToPlayer(Planet planet, Player player){
        playerManager.assignOwnership(planet, player);

        putPlayersSoldierOnPlanet(player, planet, 1);
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
    public void distributeRemainingSoldiers(List<Planet> planets){
        for (Player currentPlayer : playerManager.getPlayers()){
            ArrayList<Planet> ownedPlanetsList = getOwnedPlanets(currentPlayer);
            int i = 0;
            while(currentPlayer.getReinforceableSoldiers() > 0){
                Planet currentPlanet = ownedPlanetsList.get(i % ownedPlanetsList.size());
                putPlayersSoldierOnPlanet(currentPlayer, currentPlanet, 1);
                i++;
            }
        }
    }

    private ArrayList<Planet> getOwnedPlanets(Player player){
        ArrayList<Ownable> ownables = playerManager.getPlayerOwnership().getPlayersOwnables(player);
        ArrayList<Planet> ownedPlanets = new ArrayList<>();
        for (Ownable ownable : ownables){
            if (ownable instanceof Planet){
                ownedPlanets.add((Planet) ownable);
            }
        }
        return ownedPlanets;
    }
   
}


