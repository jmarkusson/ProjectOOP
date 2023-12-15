package group6.Model;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
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

    public void loadBoard(String[] planetNames, String[] solarSystems, String[] adjacencies, Point[][] planetPositions, Point[] solarPoints){
        this.planetNames = planetNames;
        this.planetPositions = planetPositions;
        this.solarPositions = solarPoints;
        this.solarSystems = solarSystems;

        createPlanets();
        createSolarSystems();
        setPlanetAdjacencies(adjacencies);
    }

    private void createPlanets() {
        planetMap = new HashMap<>();
        for (String planetName : planetNames) {
            planetMap.put(planetName, new Planet(planetName));
        }
        listofPlanets = new ArrayList<>(planetMap.values());
    }

    private void createSolarSystems() {
        solarsystemMap = new HashMap<>();
        for (String solarSystem : solarSystems) {
            String[] parts = solarSystem.split(",");
            String name = parts[0];
            int bonus = Integer.parseInt(parts[1]);
            ArrayList<Planet> planetsInSystem = new ArrayList<>();
            for (int j = 2; j < parts.length; j++) {
                planetsInSystem.add(planetMap.get(parts[j]));
            }
            solarsystemMap.put(name, new SolarSystem(name, bonus, planetsInSystem));
        }
    }

    private void setPlanetAdjacencies(String[] adjacencies){
        for (String adjacency : adjacencies){
            String[] parts = adjacency.split(",");
            String planetName = parts[0];
            ArrayList<Planet> adjacentPlanets = new ArrayList<>();
            for (int i = 1; i < parts.length; i++){
                String adjacentPlanetName = parts[i];
                adjacentPlanets.add(planetMap.get(adjacentPlanetName));
            }
            planetMap.get(planetName).setAdjacencies(adjacentPlanets);
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
}


