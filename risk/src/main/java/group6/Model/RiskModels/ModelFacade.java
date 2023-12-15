package group6.Model.RiskModels;

import group6.Model.Board;
import group6.Model.Dice;
import group6.Model.Planet;
import group6.Model.Player;
import group6.Model.PlayerOwnership;
import group6.Model.Interfaces.GameStateObserver;
import group6.Model.Interfaces.Ownable;
import group6.Model.Interfaces.PlanetObserver;
import group6.Model.Interfaces.PlayerObserver;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class ModelFacade {
    private GameInitializer gameInitializer;
    private GameMechanics gameMechanics;
    private PlayerManager playerManager;
    private BoardManager boardManager;
    
    private FileParser fileParser;
    private Board board;
    private GameStateManager gameStateManager;
    private PlayerOwnership playerOwnership = new PlayerOwnership();
    private ObserverManager observerManager;
    private Dice dice = new Dice();

    public ModelFacade() {
        this.board = new Board();

        this.observerManager = new ObserverManager();
        this.playerManager = new PlayerManager(this.playerOwnership);
        this.boardManager = new BoardManager(this.board, this.playerManager, this.observerManager);
        this.gameMechanics = new GameMechanics(this.playerManager, this.boardManager, this.dice);
        
        this.fileParser = new FileParser();
        this.gameInitializer = new GameInitializer(this.boardManager, this.playerManager, this.fileParser);
        this.gameStateManager = new GameStateManager(observerManager);
    }

    public ArrayList<Planet> getPlanets(){
        return board.getPlanets();
    }

    public void initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors) {
        this.gameInitializer.initGame(playerNames, playerColors);
    }

    public void reinforcePlanet(String rplanet, int soldiers){
        //local variables for function
        Planet planet = this.boardManager.getPlanetByName(rplanet);
        Player owner = this.playerManager.getOwner(planet);
        // Handle game logic
        gameMechanics.reinforcePlanet(planet, soldiers);
        //notifies observers
        notifyPlayerObservers();
        notifyPlanetObservers(rplanet, planet.getSoldiers(), owner);
    }

    public boolean isReinforceDone(){
        return gameMechanics.isReinforceDone();
    }

    public void attackPlanet(String planetAttackingFrom, String planetAttacking, int amountOfSoldiers){
        // Local variables for function
        Planet originPlanet = this.boardManager.getPlanetByName(planetAttackingFrom);
        Planet defendingPlanet = this.boardManager.getPlanetByName(planetAttacking);
        Player owner = this.playerManager.getOwner(originPlanet);
        Player defendingowner = this.playerManager.getOwner(defendingPlanet);
        // Handles game logic
        gameMechanics.attackPlanet(originPlanet, defendingPlanet, amountOfSoldiers);

        owner = this.playerManager.getOwner(originPlanet);
        defendingowner = this.playerManager.getOwner(defendingPlanet);
        
        // Notifies observers
        notifyPlayerObservers();
        notifyPlanetObservers(planetAttackingFrom, originPlanet.getSoldiers(),owner);
        notifyPlanetObservers(planetAttacking, defendingPlanet.getSoldiers(),defendingowner);
        
    }
    
    public void fortifyPlanet(String fromPlanet, String toPlanet, int soldiers){
        Planet originPlanet = this.boardManager.getPlanetByName(fromPlanet);
        Planet planetToFortify = this.boardManager.getPlanetByName(toPlanet);
        Player owner = this.playerManager.getOwner(originPlanet);

        gameMechanics.fortifyPlanet(originPlanet, planetToFortify,soldiers, owner);
        notifyPlanetObservers(fromPlanet, boardManager.getSoldiers(originPlanet), owner);
        notifyPlanetObservers(toPlanet, boardManager.getSoldiers(planetToFortify), owner);
    }

    public void setNumberOfPlayers(int numberOfPlayers){
        this.playerManager.setNumberOfPlayers(numberOfPlayers); 
    }
    
    public int getNumberOfPlayers(){
        return this.playerManager.getNumberOfPlayers();
    }
    
    // Entites that knows about modelFacade should not be able to call functions below:

    private void notifyPlanetObservers(String planet, int amountOfSoldiers, Player owner){
        observerManager.notifyPlanetObservers(planet, amountOfSoldiers, owner); 
    }
    
    private void notifyPlayerObservers(){
        observerManager.notifyPlayerObservers();
    }

    public void addPlayerObserver(PlayerObserver playerObserver) {
        observerManager.addPlayerObserver(playerObserver);
    }

    public void attach(PlanetObserver planetObserver) {
        observerManager.attach(planetObserver);
    }

    public Color[] getPlayerColors(){
        return playerManager.getPlayerColors();
    } 

    public ArrayList<Player> getPlayers(){
        return playerManager.getPlayers();
    }

    public Player getPlayer(int i){
        return playerManager.getPlayer(i);
    }
    
    public Color getPlayerColor(Player player){
        return playerManager.getPlayerColor(player);
    }

    public String[] getPlanetNames(){
        return boardManager.getPlanetNames();
    }

    public Point[][] getPlanetPositions() {
        return boardManager.getPlanetPositions();
    }

    public Point[] getSolarPositions(){
        return boardManager.getSolarPositions();
    }

    public HashMap<String, Color> getPlanetColorMap(){
        return boardManager.getPlanetColorMap();
    }

    public Planet getPlanetByName(String planetName){
        return boardManager.getPlanetByName(planetName);

    }

    public int getPlanetsSoldiers(String planet){
        return boardManager.getPlanetsSoldiers(planet);
    }


    public Player getCurrentPlayer(){
        return playerManager.getCurrentPlayer();
    }

    public int getCurrentPlayersReinforcableSoldier(){
        return playerManager.getCurrentPlayersReinforcableSoldier();
    }

    public boolean isOwnedCurrentPlayer(Ownable ownable){
        return playerManager.isOwnedCurrentPlayer(ownable);
    }

    public void setGameStateObserver(GameStateObserver gameStateObserver){
        gameStateManager.setGameStateObserver(gameStateObserver);
    }

    public void changeGameStateIndex(){
        gameStateManager.changeGameStateIndex();
    }

    public String getCurrentGameState(){
       return gameStateManager.getCurrentGameState();
    }

    public void changeState(){
        gameStateManager.changeState();
    }

    public void nextPlayer(){
        playerManager.nextPlayer();
    }
    public String[] getUnownedAdjecentPlanets(String planet){
        return this.boardManager.getUnownedAdjecentPlanets(planet);
    }
    public String[] getOwnedAdjecentPlanets(String planet){
        return this.boardManager.getOwnedAdjecentPlanets(planet);
    }
}

