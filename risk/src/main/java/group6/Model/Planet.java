package group6.Model;


import java.util.ArrayList;

public class Planet{

    private String name;
    private ArrayList<Planet> adjacentPlanets;
    private int soldiers;

    public Planet(String name) {
        this.name = name;
    }

    public void setAdjacencies(ArrayList<Planet> adjacentPlanets) {
        this.adjacentPlanets = adjacentPlanets;
    }



}