package group6.Model;


import java.util.List;

import group6.Model.Interfaces.Ownable;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class RiskModel {

    private ArrayList<Player> players = new ArrayList<>();
    private Board board;
    private int nmbrOfPlayers = 2;
    private PlayerOwnership playerOwnership = new PlayerOwnership();
    private int currentPlayerIndex;
    private Color[] colorChoices = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK};

    public RiskModel(){

         /* Nothing should happen when instance of Model is created */
    }
    

    public boolean initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors){
        
        Boolean succesfullLoad = false;
        board = new Board();

        List<String> list = new ArrayList<>();
        List<Point[]> listOfPointArrays = new ArrayList<>();
        List<Point> listOfSunPositions = new ArrayList<>();
        
        String line;
        

        try {
            // Reads and split planets into array
            InputStream planetStream = getClass().getClassLoader().getResourceAsStream("textfiles/planets.txt");
            BufferedReader readerplanet = new BufferedReader(new InputStreamReader(planetStream));

            while ((line = readerplanet.readLine()) != null) {
                list.add(line);
            }
            String[] planetsArray = list.toArray(new String[0]);
            
            //Reand and splits SunPoints into array
            InputStream sunpointStream = getClass().getClassLoader().getResourceAsStream("textfiles/solarPoints.txt");
            BufferedReader sunreaderPoint = new BufferedReader(new InputStreamReader(sunpointStream));
            while ((line = sunreaderPoint.readLine()) != null) {
                String[] parts = line.split(",");       
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                listOfSunPositions.add(new Point(x, y));
            }
            Point[] solarPointsArray = listOfSunPositions.toArray(new Point[0]);
            

            // Reads and splits PlanetPoints into array
            
            InputStream pointStream = getClass().getClassLoader().getResourceAsStream("textfiles/Points.txt");
            BufferedReader readerPoint = new BufferedReader(new InputStreamReader(pointStream));
            
            

            while ((line = readerPoint.readLine()) != null) {
                String[] pointStrings = line.split("\\s+");
                Point[] points = new Point[pointStrings.length];
                for (int i = 0; i < pointStrings.length; i++) {
                    points[i] = parsePoint(pointStrings[i]);
                }
                listOfPointArrays.add(points);
            }
            Point[][] pointsArray = listOfPointArrays.toArray(new Point[listOfPointArrays.size()][]);
            
            // Reads and split solar systems with its bonus number and planets
            list.clear();
            InputStream solarSystemStream = getClass().getClassLoader().getResourceAsStream("textfiles/SolarSystems.txt");
            BufferedReader readerSolarSystems = new BufferedReader(new InputStreamReader(solarSystemStream));

            while ((line = readerSolarSystems.readLine()) != null){
                list.add(line);
            }
            String[] solarsystemsArray = list.toArray(new String[0]);

            // Reads and splits adjacencies
            list.clear();
            InputStream adjacenciesStream = getClass().getClassLoader().getResourceAsStream("textfiles/AdjacentPlanets.txt");
            BufferedReader readerAdjacentPlanets = new BufferedReader(new InputStreamReader(adjacenciesStream));

            while ((line = readerAdjacentPlanets.readLine()) != null){
                list.add(line);
            }
            String[] adjacenciesArray = list.toArray(new String[0]);
            succesfullLoad = board.loadBoard(planetsArray, solarsystemsArray, adjacenciesArray, pointsArray, solarPointsArray);

            players = new ArrayList<Player>();

            for(int i = 0; i < playerNames.size(); i++){

                players.add(new Player(playerNames.get(i), playerColors.get(i), i));
                nmbrOfPlayers++;

            }

            distributePlanets();

            readerplanet.close();
            readerPoint.close();
            readerSolarSystems.close();
            readerAdjacentPlanets.close();
            
    } catch (FileNotFoundException e) {
        System.err.println("One or more files were not found: " + e.getMessage());
    } catch (IOException e) {
        System.err.println("An error occurred while reading from the file: " + e.getMessage());
    }

        return false;

    }

    public void distributePlanets(){
        List<Planet> shuffledPlanets = getShuffledPlanets();
        initializePlayersReinforceableSoldiers();
        evenlyDistributeInitalPlanets(shuffledPlanets);
        distributeRemainingSoldiers(shuffledPlanets);
        resetAllReinforcableSoldierForNextTurn();
    }

    private List<Planet> getShuffledPlanets(){
        List<Planet> shuffledPlanets = new ArrayList<>(board.getPlanets());
        Collections.shuffle(shuffledPlanets);
        return shuffledPlanets;
    }

    private void initializePlayersReinforceableSoldiers(){
        for (Player player : players){
            player.setReinforceableSoldiers(player.getSoldiers());
        }

    }

    public Color[] getColors(){
        return colorChoices;
    } 

    private void evenlyDistributeInitalPlanets(List<Planet> planets){
        for (int i = 0; i < planets.size(); i++){
            Planet currentPlanet = planets.get(i);
            Player currentPlayer = players.get(i % players.size());

            playerOwnership.assignOwnership(currentPlanet, currentPlayer);

            putPlayersSoldierOnPlanet(currentPlayer, currentPlanet, 1);

        }
    }

    private void distributeRemainingSoldiers(List<Planet> planets){
        int i = 0;
        while (playersHaveReinforceableSoldiers()) {
            Player currentPlayer = players.get(i % players.size());
            Planet currentPlanet = planets.get(i % planets.size());

            if (currentPlayer.getReinforceableSoldiers() > 0) {
                putPlayersSoldierOnPlanet(currentPlayer, currentPlanet, 1);
            }
            i++;
        }
    }
    // check this
    private boolean playersHaveReinforceableSoldiers(){
            for (Player player : players){
                if (player.getReinforceableSoldiers() > 0){
                    return true;
                }
            }
            return false;
    }

    private void putPlayersSoldierOnPlanet(Player player, Planet planet, int soldiers){
            planet.addSoldiers(soldiers);
            player.removeReinforceableSoldiers(soldiers);
    }
    

    public boolean isOwned(Ownable ownable, Player player){
        return playerOwnership.isOwned(ownable, player);
    }

    public void reinforce(int soldiersPlaced, String planetName) {
        Player currentPlayer = getCurrentPlayer();
        
        int soldiersLeft = currentPlayer.getReinforceableSoldiers() - soldiersPlaced;
        currentPlayer.setReinforceableSoldiers(soldiersLeft);
    
        int totalSoldiers = currentPlayer.getSoldiers() + soldiersPlaced;
        currentPlayer.setSoldiers(totalSoldiers);

        Planet planet = getPlanetByName(planetName);
        planet.addSoldiers(soldiersPlaced);

    }

    public Boolean isReinforceDone(){
        Boolean reinforceDone = false;
        Player currentPlayer = getCurrentPlayer();

        if(currentPlayer.getReinforceableSoldiers() == 0){
            reinforceDone = true;
            // Set Reinforcable Soldier back to the amount of bonustroops so it is correct at the start of the next round
            resetReinforcableSoldierForNextTurn(currentPlayer);

        }

        return reinforceDone;
    }

    private void resetReinforcableSoldierForNextTurn(Player player){
        player.setReinforceableSoldiers(player.getBonusSoldiers());
    }

    private void resetAllReinforcableSoldierForNextTurn(){
        for (Player player : players){
            resetReinforcableSoldierForNextTurn(player);
        }
    }

    public void attack(int attackSoldiers, int defendSoldiers, String attackPlanetName, String defendPlanetName){
        Player currentPlayer = getCurrentPlayer();

        Planet attackPlanet = getPlanetByName(attackPlanetName);
        Planet defendPlanet = getPlanetByName(defendPlanetName);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public Player getPlayer(int i){
        return players.get(i);
    }
    public void setnmbOfPlayers(int nmbrOfPlayers){
        this.nmbrOfPlayers = nmbrOfPlayers;
    }

    public int getnmbrOfPlayers(){
        return this.nmbrOfPlayers;
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
        
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer(){
        this.currentPlayerIndex = (currentPlayerIndex + 1) % getnmbrOfPlayers();
    }

    public int getCurrentPlayersFortifySoldiers(){
        return this.getCurrentPlayer().getFortifySoldiers();
    }

    public int getCurrentPlayersReinforcableSoldier(){
        return this.getCurrentPlayer().getReinforceableSoldiers();
    }

    public void setCurrentPlayersReinforcableSoldier(int soldiers){
        getCurrentPlayer().setReinforceableSoldiers(soldiers);
    }

    private Point parsePoint(String str) {
        String[] parts = str.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new Point(x, y);
    }

    public ArrayList<Planet> getAdjecentPlanets(Planet planet){
        return planet.getAdjecentPlanets();
    }


    public String[] getOwnedAdjecentPlanets(String planet){
        ArrayList<Planet> adjacentPlanets = getAdjecentPlanets(getPlanetByName(planet));
        ArrayList<Ownable> playersOwnables = playerOwnership.getPlayersOwnables(this.getCurrentPlayer());
        ArrayList<String> ownedAdjecentPlanets = new ArrayList<>();

        for (Planet adjacentPlanet : adjacentPlanets) {
            if (playersOwnables.contains(adjacentPlanet)) {
                ownedAdjecentPlanets.add(adjacentPlanet.getName());
            }
        }

        return ownedAdjecentPlanets.toArray(new String[0]);
    }

    public int getPlanetsSoldiers(String planet){
        return board.getPlanetByName(planet).getSoldiers();
    }

    public Planet getPlanetByName(String planet){
        return board.getPlanetByName(planet);
    }

    public void ReinforcePlanet(String planet, int soldiers){
        Planet rPlanet = getPlanetByName(planet);
        this.getCurrentPlayer().removeReinforceableSoldiers(soldiers);
        rPlanet.addSoldiers(soldiers);
    }
}
