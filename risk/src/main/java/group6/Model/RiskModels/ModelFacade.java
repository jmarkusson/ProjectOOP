package group6.Model.RiskModels;

import group6.Model.Board;
import group6.Model.Planet;

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

}
