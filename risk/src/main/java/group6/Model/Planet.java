package group6.Model;


import java.util.ArrayList;

import group6.Model.Interfaces.Ownable;

public class Planet implements Ownable{

    private String name;
    private ArrayList<Planet> adjacentPlanets;
    private int soldiers;

    public Planet(String name) {
        this.name = name;
    }

    public void setAdjacencies(ArrayList<Planet> adjacentPlanets) {
        this.adjacentPlanets = adjacentPlanets;
    }

    public void addSoldiers(int soldiers){
        this.soldiers += soldiers;
    }

}