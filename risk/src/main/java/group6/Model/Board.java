package group6.Model;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Board {

    private ArrayList<Planet> listofPlanets;
    private HashMap<String, Planet> planetMap;
    private HashMap<String, SolarSystem> solarsystemMap;
    private HashMap<String, Color> planetColorMap = new HashMap<String, Color>();

    private String[] solarSystems;
    private String[] planetNames;
    private Point[][] planetPositions;
    private Point[] solarPositions;
    
    
    public Board(){
        // Nothing should happen when a new instance of board is created 

    }

    public void loadBoard(String[] arrayofPlanets, String[] arrayofSolarsystems, String[] arrayofAdjacencies, Point[][] arrayofPlanetPositions, Point[] arrayofSolarPoints){
        this.planetNames = arrayofPlanets;
        this.planetPositions = arrayofPlanetPositions;
        this.solarPositions = arrayofSolarPoints;
        this.solarSystems = arrayofSolarsystems;
        this.planetMap = populatePlanetHashMap(this.planetNames);
        this.solarsystemMap = populateSolarSystemHashMap(this.solarSystems);
        this.listofPlanets = convertPlanetHashMapToArrayList(this.planetMap);
        

        // for (int i = 0; i < arrayofPlanets.length; i++){
        //     planetMap.put(arrayofPlanets[i], new Planet(arrayofPlanets[i]));    
        // }


        // for (int i = 0; i < arrayofSolarsystems.length; i++){

        //     String[] oneSolarsystemArray = arrayofSolarsystems[i].split(",");

        //     ArrayList<Planet> planetsinSolarsystem = new ArrayList<Planet>();

        //     for (int j = 2; j < oneSolarsystemArray.length; j++){

        //         planetsinSolarsystem.add(planetMap.get(oneSolarsystemArray[j]));

        //     }

        //     solarsystemMap.put(oneSolarsystemArray[0], new SolarSystem(oneSolarsystemArray[0], Integer.parseInt(oneSolarsystemArray[1]), planetsinSolarsystem));

        // }


        for (int i = 0; i < arrayofAdjacencies.length; i++){

            String[] onePlanetAdjacanecies = arrayofAdjacencies[i].split(",");

            ArrayList<Planet> adjacentPlanets = new ArrayList<Planet>();

            for (int j = 1; j < onePlanetAdjacanecies.length; j++){

                String oneAdjacentPlanetName = onePlanetAdjacanecies[j];
                Planet oneAdjacentPlanet = planetMap.get(oneAdjacentPlanetName);
                adjacentPlanets.add(oneAdjacentPlanet);

            }

            planetMap.get(onePlanetAdjacanecies[0]).setAdjacencies(adjacentPlanets);
        }
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

    public ArrayList<Planet> getPlanets(){
        return listofPlanets;
    }

    public Planet getPlanetByName(String planet){
        return planetMap.get(planet);
    }

    public HashMap<String, Color> getPlanetColorMap(){
        return this.planetColorMap;
    }
    
    private HashMap<String, Planet> populatePlanetHashMap(String[] arrayofPlanets){

        planetMap = new HashMap<String, Planet>();

        for (int i = 0; i < arrayofPlanets.length; i++){
            planetMap.put(arrayofPlanets[i], new Planet(arrayofPlanets[i]));    
        }
        return planetMap;
    }
    
    private HashMap<String, SolarSystem> populateSolarSystemHashMap(String[] arrayofSolarsystems){
            
        HashMap<String, SolarSystem> solarsystemMap = new HashMap<String, SolarSystem>();

        for (int i = 0; i < arrayofSolarsystems.length; i++){

            String[] oneSolarsystemArray = arrayofSolarsystems[i].split(",");

            ArrayList<Planet> planetsinSolarsystem = new ArrayList<Planet>();

            for (int j = 2; j < oneSolarsystemArray.length; j++){
                planetsinSolarsystem.add(planetMap.get(oneSolarsystemArray[j]));
            }
            
            solarsystemMap.put(oneSolarsystemArray[0], new SolarSystem(oneSolarsystemArray[0], Integer.parseInt(oneSolarsystemArray[1]), planetsinSolarsystem));
            
        }
        return solarsystemMap;
    }
    
    private ArrayList<Planet> convertPlanetHashMapToArrayList(HashMap<String, Planet> planetMap){
        return new ArrayList<Planet>(planetMap.values());
    }
}


