package group6.Model;


import java.util.ArrayList;

import group6.Model.Interfaces.Ownable;

public class Planet{

    private String name;
    private ArrayList<Planet> adjacentPlanets;
    private int soldiers;
    private Player owner;


    public Planet(String name) {
        this.name = name;
    }

    public void setAdjacencies(ArrayList<Planet> adjacentPlanets) {
        this.adjacentPlanets = adjacentPlanets;
    }


}