package group6.Model;


import java.util.ArrayList;

import group6.Model.Interfaces.Ownable;

public class SolarSystem implements Ownable{

    private String name;
    private int extraTroops;
    private ArrayList<Planet> planetsinSolarsystem;

    public SolarSystem(String name, int extraTroops, ArrayList<Planet> planetsinSolarsystem){
        this.name = name;
        this.extraTroops = extraTroops;
        this.planetsinSolarsystem = planetsinSolarsystem;
    }

    public String getName() {
        return name;
    }

    public int getExtraTroops() {
        return extraTroops;
    }

    public ArrayList<Planet> getPlanetsinSolarsystem() {
        return planetsinSolarsystem;
    }
}

