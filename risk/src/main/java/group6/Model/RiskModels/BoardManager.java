package group6.Model.RiskModels;

import java.awt.Point;

import group6.Model.Board;

public class BoardManager {
    private Board board;

    public BoardManager() {
        // Nothing should happen when an instance of BoardManager is created
    }

    protected void createBoard() {
        board = new Board();
    }

    protected void loadBoard(String[] planetNames, String[] solarsystems, String[] adjacencies, Point[][] planetPoints, Point[] solarPoints) {
        board.loadBoard(planetNames, solarsystems, adjacencies, planetPoints, solarPoints);
    }

    protected void distributePlanets() {
        
    }

}


