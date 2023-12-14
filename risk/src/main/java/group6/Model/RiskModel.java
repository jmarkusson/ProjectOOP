package group6.Model;


import java.util.List;
import java.util.Random;

import group6.Model.Interfaces.PlanetObserver;
import group6.Model.Interfaces.GameStateObserver;
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

    private GameStateObserver gameStateObserver;
    private List<PlanetObserver> planetObservers = new ArrayList<>();
    private List<PlayerObserver> playerObservers = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Board board;
    private int nmbrOfPlayers = 2;
    private PlayerOwnership playerOwnership = new PlayerOwnership();
    private int currentPlayerIndex;
    private Color[] colorChoices = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK};
    private String[] gamestates = {"REINFORCE", "ATTACK", "FORTIFY"};
    private int currentGameState = 0;
    

    public RiskModel(){
         /* Nothing should happen when instance of Model is created */
    }

    public void changeState(){
        gameStateObserver.actOnStateChange();
    }

    public void changeGameStateIndex(){
        currentGameState = (currentGameState + 1) % 3;
    }
    
    public String getCurrentGameState(){
        return gamestates[currentGameState];
    }

    public void setGameStateObserver(GameStateObserver gameStateObserver){
        this.gameStateObserver = gameStateObserver;
    }

    /*public boolean initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors){
        
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
            board.loadBoard(planetsArray, solarsystemsArray, adjacenciesArray, pointsArray, solarPointsArray);

            initPlayers(playerNames, playerColors);

            distributePlanets();

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
    */

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
// Took the function below and implemented it in 
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
        for (Player currentPlayer : players){
            ArrayList<Planet> ownedPlanetsList = getOwnedPlanets(currentPlayer);
            int i = 0;
            while(currentPlayer.getReinforceableSoldiers() > 0){
                Planet currentPlanet = ownedPlanetsList.get(i % ownedPlanetsList.size());
                putPlayersSoldierOnPlanet(currentPlayer, currentPlanet, 1);
                i++;
            }
        }
    }

    private void putPlayersSoldierOnPlanet(Player player, Planet planet, int soldiers){
            planet.addSoldiers(soldiers);
            player.removeReinforceableSoldiers(soldiers);
            notifyPlanetObservers(planet.getName(), soldiers, playerOwnership.getOwner(planet));

    }       

    public Color[] getColors(){
        return colorChoices;
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

    private ArrayList<Planet> getOwnedPlanets(Player player){
        ArrayList<Ownable> ownables = playerOwnership.getPlayersOwnables(player);
        ArrayList<Planet> ownedPlanets = new ArrayList<>();
        for (Ownable ownable : ownables){
            if (ownable instanceof Planet){
                ownedPlanets.add((Planet) ownable);
            }
        }
        return ownedPlanets;
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

        Player owner = playerOwnership.getOwner(originPlanet);

        notifyPlanetObservers(fromPlanet, originPlanet.getSoldiers(), owner);
        notifyPlanetObservers(toPlanet, fortifyPlanet.getSoldiers(), owner);
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
        Planet attackingPlanet = board.getPlanetByName(attackFromPlanet);
        Planet defendingPlanet = board.getPlanetByName(planetToAttack);

        int remainingAttackers = amountOfSoldiers;
        int defendingSoldiersLost = 0;
        int attackingSoldiersLost = 0;

        while (remainingAttackers > 0 && defendingPlanet.getSoldiers() > 0) {
            int attackingDice = Math.min(remainingAttackers, 3);
            int defendingDice = Math.min(defendingPlanet.getSoldiers(), 2);

            Integer[] attackRolls = rollDice(attackingDice);
            Integer[] defendRolls = rollDice(defendingDice);

            Arrays.sort(attackRolls, Collections.reverseOrder());
            Arrays.sort(defendRolls, Collections.reverseOrder());

            for (int i = 0; i < Math.min(attackingDice, defendingDice); i++) {
                int attackValue = attackRolls[i];
                int defendValue = defendRolls[i];

                if (attackValue > defendValue) {
                    defendingPlanet.removeSoldiers(1);
                    defendingSoldiersLost++;
                } else {
                    remainingAttackers--;
                    attackingSoldiersLost++;
                    attackingPlanet.removeSoldiers(1);
                }
            }
        }

        Player attackingPlayer = playerOwnership.getOwner(attackingPlanet);
        Player defendingPlayer = playerOwnership.getOwner(defendingPlanet);
        attackingPlayer.setSoldiers(attackingPlayer.getSoldiers() - attackingSoldiersLost);
        defendingPlayer.setSoldiers(defendingPlayer.getSoldiers() - defendingSoldiersLost);

        if (defendingPlanet.getSoldiers() == 0) {
            playerOwnership.assignOwnership(defendingPlanet, attackingPlayer);
            playerOwnership.removeOwnership(defendingPlanet, defendingPlayer);
            defendingPlanet.addSoldiers(remainingAttackers);
            attackingPlanet.removeSoldiers(remainingAttackers);
        }

        notifyPlayerObservers();
        notifyPlanetObservers(attackFromPlanet, attackingPlanet.getSoldiers(),
            playerOwnership.getOwner(attackingPlanet));
        notifyPlanetObservers(planetToAttack, defendingPlanet.getSoldiers(),
            playerOwnership.getOwner(defendingPlanet));

    }


    private Integer[] rollDice(int numberOfDice) {
        Random random = new Random();
        Integer[] rolls = new Integer[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            rolls[i] = random.nextInt(6) + 1;
        }
        return rolls;
    }

    
}
