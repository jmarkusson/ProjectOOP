package group6.Model;


import java.util.List;
import java.util.Random;

import group6.Model.Interfaces.PlanetObserver;
import group6.Model.Interfaces.Ownable;
import group6.Model.Interfaces.PlayerObserver;

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

public class RiskModel{

    private List<PlanetObserver> planetObservers = new ArrayList<>();
    private List<PlayerObserver> playerObservers = new ArrayList<>();
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
            InputStream planetStream = getClass().getClassLoader().getResourceAsStream("textfiles/planets.txt");
            BufferedReader readerplanet = new BufferedReader(new InputStreamReader(planetStream));

            while ((line = readerplanet.readLine()) != null) {
                list.add(line);
            }
            String[] planetsArray = list.toArray(new String[0]);
            
            InputStream sunpointStream = getClass().getClassLoader().getResourceAsStream("textfiles/solarPoints.txt");
            BufferedReader sunreaderPoint = new BufferedReader(new InputStreamReader(sunpointStream));
            while ((line = sunreaderPoint.readLine()) != null) {
                String[] parts = line.split(",");       
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                listOfSunPositions.add(new Point(x, y));
            }
            Point[] solarPointsArray = listOfSunPositions.toArray(new Point[0]);
            
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

            initPlayers(playerNames, playerColors);

            distributePlanets();
            notifyPlanetObservers(line, currentPlayerIndex);

            readerplanet.close();
            readerPoint.close();
            readerSolarSystems.close();
            readerAdjacentPlanets.close();
            
            succesfullLoad = true;

    } catch (FileNotFoundException e) {
        System.err.println("One or more files were not found: " + e.getMessage());
    } catch (IOException e) {
        System.err.println("An error occurred while reading from the file: " + e.getMessage());
    }

        return succesfullLoad;

    }

    private void initPlayers(ArrayList<String> playerNames, ArrayList<Color> playerColors){
        players = new ArrayList<Player>();

            for(int i = 0; i < playerNames.size(); i++){

                players.add(new Player(playerNames.get(i), playerColors.get(i), i));

            }
    }

    private void distributePlanets(){
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


            board.getPlanetColorMap().put(currentPlanet.getName(), currentPlayer.getColor());
                
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
            notifyPlanetObservers(planet.getName(), soldiers);

    }
    

    public boolean isOwned(Ownable ownable, Player player){
        return playerOwnership.isOwned(ownable, player);
    }

    public boolean isOwnedCurrentPlayer(Ownable ownable){
        return isOwned(ownable, getCurrentPlayer());
    }
    public void reinforce(int soldiersPlaced, String planetName) {
        Player currentPlayer = getCurrentPlayer();
        
        int soldiersLeft = currentPlayer.getReinforceableSoldiers() - soldiersPlaced;
        currentPlayer.setReinforceableSoldiers(soldiersLeft);
    
        int totalSoldiers = currentPlayer.getSoldiers() + soldiersPlaced;
        currentPlayer.setSoldiers(totalSoldiers);

        Planet planet = getPlanetByName(planetName);
        planet.addSoldiers(soldiersPlaced);

        currentPlayer.setReinforceableSoldiers(soldiersLeft);

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
        int numberOfPlayers = this.getnmbrOfPlayers();
        this.currentPlayerIndex = ((this.currentPlayerIndex + 1) % numberOfPlayers);
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
        this.getCurrentPlayer().addSoldiers(soldiers);
        notifyPlanetObservers(planet, rPlanet.getSoldiers(),
         playerOwnership.getOwner(rPlanet));
        notifyPlayerObservers();
    }

    public HashMap<String, Color> getPlanetColorMap(){
        return board.getPlanetColorMap();
    }
    public Color getPlayerColor(Player player){
        return player.getColor();
    }
     public String[] getUnownedAdjecentPlanets(String planet){
        ArrayList<Planet> adjacentPlanets = getAdjecentPlanets(getPlanetByName(planet));
        ArrayList<Ownable> playersOwnables = playerOwnership.getPlayersOwnables(this.getCurrentPlayer());
        ArrayList<String> unownedAdjecentPlanets = new ArrayList<>();

        for (Planet adjacentPlanet : adjacentPlanets) {
            if (!playersOwnables.contains(adjacentPlanet)) {
                unownedAdjecentPlanets.add(adjacentPlanet.getName());
            }
        }

        return unownedAdjecentPlanets.toArray(new String[0]);
    }

    public void fortifyPlanet(String fromPlanet, String toPlanet, int soldiers){
        Planet originPlanet = getPlanetByName(fromPlanet);
        Planet fortifyPlanet = getPlanetByName(toPlanet);

        originPlanet.removeSoldiers(soldiers);
        fortifyPlanet.addSoldiers(soldiers);

        notifyPlanetObservers(fromPlanet, originPlanet.getSoldiers(),
         playerOwnership.getOwner(originPlanet));
        notifyPlanetObservers(toPlanet, fortifyPlanet.getSoldiers(),
        playerOwnership.getOwner(fortifyPlanet));
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

    public void attackPlanet(String attackFromPlanet, String planetToAttack, int amountOfSoldiers) {
        Planet fromPlanet = board.getPlanetByName(attackFromPlanet);
        Planet toPlanet = board.getPlanetByName(planetToAttack);

        int remainingAttackers = amountOfSoldiers;

        while (remainingAttackers > 0 && toPlanet.getSoldiers() > 0) {
            int attackingDice = Math.min(remainingAttackers, 3);
            int defendingDice = Math.min(toPlanet.getSoldiers(), 2);

            int[] attackRolls = rollDice(attackingDice);
            int[] defendRolls = rollDice(defendingDice);

            Arrays.sort(attackRolls);
            Arrays.sort(defendRolls);
            
            int middleIndex = Math.min(attackingDice, 2);

            for (int i = 0; i < Math.min(middleIndex , defendingDice); i++) {
                int attackValue = attackRolls[Math.min(i, middleIndex)];
                int defendValue = defendRolls[i];

                if (attackValue > defendValue) {
                    toPlanet.removeSoldiers(1); 
                } else {
                    remainingAttackers--;
                    fromPlanet.removeSoldiers(1);
                }
            }

            
        }

        notifyPlayerObservers();
        notifyPlanetObservers(attackFromPlanet, fromPlanet.getSoldiers(),
            playerOwnership.getOwner(fromPlanet));
        notifyPlanetObservers(planetToAttack, toPlanet.getSoldiers(),
            playerOwnership.getOwner(toPlanet));

    }


    private int[] rollDice(int numberOfDice) {
        Random random = new Random();
        int[] rolls = new int[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            rolls[i] = random.nextInt(6) + 1;
        }
        return rolls;
    }

    
}
