package group6.Model.RiskModels;

import java.awt.Color;

import java.util.ArrayList;
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

public class GameInitializer {

    private Color[] colorChoices = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK};
    private FileParser fileParser;
    private BoardManager boardManager;
    private PlayerManager playerManager;

    public GameInitializer(BoardManager boardManager, PlayerManager playerManager, FileParser fileParser) {
        this.boardManager = boardManager;
        this.playerManager = playerManager;
        this.fileParser = fileParser;
    }

    public void initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors) {
        this.fileParser.parseFile();
        this.boardManager.loadBoard(fileParser.getPlanetNamesArray(), fileParser.getSolarsystemsArray(), fileParser.getAdjacenciesArray(), fileParser.getPlanetPointsArray(), fileParser.getSolarPointsArray());
        this.playerManager.initPlayers(playerNames,playerColors);
        this.boardManager.distributePlanets();

    }

    
}

