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

public class BoardManager {
    private Board board;
    private ArrayList<Planet> planets;

    public BoardManager(Board board) {
        this.board = board;
        this.planets = planets;
    }

    protected void loadBoard(String[] planetNames, String[] solarsystems, String[] adjacencies, Point[][] planetPoints, Point[] solarPoints) {
        this.board.loadBoard(planetNames, solarsystems, adjacencies, planetPoints, solarPoints);
    }

    protected void distributePlanets() {
        
    }

    public ArrayList<Planet> getPlanets() {
        return board.getPlanets();
    }

    private List<Planet> getShuffledPlanets(){
        List<Planet> shuffledPlanets = new ArrayList<>(board.getPlanets());
        Collections.shuffle(shuffledPlanets);
        return shuffledPlanets;
    }
    

}


