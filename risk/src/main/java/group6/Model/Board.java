package group6.Model;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private ArrayList<Planet> listofPlanets;
    private HashMap<String, Planet> planetMap;
    private HashMap<String, SolarSystem> solarsystemMap;

    private String[] planetNames;
    private Point[][] planetPositions;
    private Point[] solarPositions;
    
    public Board(){
        // Nothing should happen when a new instance of board is created 

    }

    public boolean loadBoard(String[] arrayofPlanets, String[] arrayofSolarsystems, String[] arrayofAdjacencies, Point[][] arrayofPlanetPositions, Point[] arrayofSolarPoints){
        this.planetNames = arrayofPlanets;
        this.planetPositions = arrayofPlanetPositions;
        this.solarPositions = arrayofSolarPoints;


        boolean succesfullLoad = false;

        planetMap = new HashMap<String, Planet>();
        solarsystemMap = new HashMap<String, SolarSystem>();

        for (int i = 0; i < arrayofPlanets.length; i++){
            planetMap.put(arrayofPlanets[i], new Planet(arrayofPlanets[i]));    
        }

        listofPlanets = new ArrayList<Planet>();

        for (int i = 0; i < arrayofSolarsystems.length; i++){

            String[] oneSolarsystemArray = arrayofSolarsystems[i].split(",");

            ArrayList<Planet> planetsinSolarsystem = new ArrayList<Planet>();

            for (int j = 2; j < oneSolarsystemArray.length; j++){

                planetsinSolarsystem.add(planetMap.get(oneSolarsystemArray[j]));

            }

            solarsystemMap.put(oneSolarsystemArray[0], new SolarSystem(oneSolarsystemArray[0], Integer.parseInt(oneSolarsystemArray[1]), planetsinSolarsystem));

        }


        for (int i = 0; i < arrayofAdjacencies.length; i++){

            String[] onePlanetAdjacanecies = arrayofAdjacencies[i].split(",");

            ArrayList<Planet> adjacentPlanets = new ArrayList<Planet>();

            for (int j = 1; j < onePlanetAdjacanecies.length; j++){

                adjacentPlanets.add(planetMap.get(onePlanetAdjacanecies[j]));

            }

            planetMap.get(onePlanetAdjacanecies[0]).setAdjacencies(adjacentPlanets);
        }

        succesfullLoad = true;

        return succesfullLoad;


    }

    public String[] getPlanetNames(){
        return this.planetNames;
    }
    public Point[][] getPlanetPositions(){
        return this.planetPositions;
    }

    public Point[] getSolarPositions(){
        return this.solarPositions;
    }



    

}
