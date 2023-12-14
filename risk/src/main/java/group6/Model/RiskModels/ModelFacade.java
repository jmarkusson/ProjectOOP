package group6.Model.RiskModels;

import group6.Model.Board;
import group6.Model.Planet;
import group6.Model.Player;

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

public class ModelFacade {
    private GameInitializer gameInitializer;
    private GameMechanics gameMechanics;
    private PlayerManager playerManager;
    private BoardManager boardManager;
    private ObserverManager observerManager;
    private FileParser fileParser;
    private Board board;

    public ModelFacade() {
        this.board = new Board();
        this.boardManager = new BoardManager(this.board);
        this.gameMechanics = new GameMechanics();
        this.playerManager = new PlayerManager();
        this.observerManager.getInstance(); // Singleton Example, see ObserverManager class.
        this.fileParser = new FileParser();
        this.gameInitializer = new GameInitializer(this.boardManager, this.playerManager, this.fileParser);
    }

    public ArrayList<Planet> getPlanets(){
        return board.getPlanets();
    }

    public void attackPlanet(String planetAttackingFrom, String planetAttacking, int amountOfSoldiers){
        Planet originPlanet = this.boardManager.getPlanetByName(planetAttackingFrom);
        Planet defendingPlanet = this.boardManager.getPlanetByName(planetAttacking);

        gameMechanics.attackPlanet(boardManager.getPlanetByName(planetAttacking),
        boardManager.getPlanetByName(planetAttacking), amountOfSoldiers);

        notifyPlayerObservers();
        
        Player owner = this.playerManager.getOwner(originPlanet);
        Player defendingowner = this.playerManager.getOwner(defendingPlanet);
        notifyPlanetObservers(planetAttackingFrom, originPlanet.getSoldiers(),
        owner);

        notifyPlanetObservers(planetAttacking, defendingPlanet.getSoldiers(),
        defendingowner);
        
    }
    
    public void fortifyPlanet(String fromPlanet, String toPlanet, int soldiers){
        Planet originPlanet = this.boardManager.getPlanetByName(fromPlanet);
        Planet planetToFortify = this.boardManager.getPlanetByName(toPlanet);
        Player owner = this.playerManager.getOwner(originPlanet);

        gameMechanics.fortifyPlanet(originPlanet, planetToFortify,soldiers, owner);
        notifyPlanetObservers(fromPlanet, boardManager.getSoldiers(originPlanet), owner);
        notifyPlanetObservers(toPlanet, boardManager.getSoldiers(planetToFortify), owner);
    }
    
    public void notifyPlanetObservers(String planet, int amountOfSoldiers, Player owner){
        observerManager.notifyPlanetObservers(planet, amountOfSoldiers, owner);
        
    }
}

