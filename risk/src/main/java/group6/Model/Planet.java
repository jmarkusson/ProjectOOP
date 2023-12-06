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

    public void removeSoldiers(int soldiers){
        this.soldiers -= soldiers;
    }

    public String getName(){
        return this.name;
    }

    public int getSoldiers(){
        return soldiers;
    }

    public ArrayList<Planet> getAdjecentPlanets(){
        return adjacentPlanets;
    }
}