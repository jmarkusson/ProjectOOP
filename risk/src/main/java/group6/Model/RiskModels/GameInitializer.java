package group6.Model.RiskModels;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        this.boardManager.createBoard();
        this.boardManager.loadBoard(fileParser.getPlanetNamesArray(), fileParser.getSolarsystemsArray(), fileParser.getAdjacenciesArray(), fileParser.getPlanetPointsArray(), fileParser.getSolarPointsArray());
        this.playerManager.initPlayers(playerNames,playerColors);
        this.boardManager.distributePlanets();

    }

    
}

