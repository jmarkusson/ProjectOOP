package group6.Model;


import java.util.List;
import java.awt.Color;

public class Player
{
    private final Color color; 
    private final String name;
    private int soldiers;
    private int reinforceableSoldiers;
    private int bonusSoldiers;
    private int playerNumber;
    private List<Planet> planetsOwned;

    public Player(String name, Color color, int playerNumber) {
        this.name = name;
        this.color = color;
        this.playerNumber = playerNumber;
        this.soldiers = 15;  
        this.bonusSoldiers = 3;
        this.reinforceableSoldiers = bonusSoldiers;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public void addSoldiers(int soldiers){
        this.soldiers += soldiers;
    }

    public void setReinforceableSoldiers(int reinforceableSoldiers){
        this.reinforceableSoldiers = reinforceableSoldiers;
    }

    public int getReinforceableSoldiers(){
        return this.reinforceableSoldiers;
    }

    public void removeReinforceableSoldiers(int reinforceableSoldiers){
        this.reinforceableSoldiers -= reinforceableSoldiers;
    }

    public List<Planet> getPlanetsOwned() {
        return planetsOwned;
    }

    public void setPlanetsOwned(List<Planet> planetsOwned) {
        this.planetsOwned = planetsOwned;
    }

    public int getBonusSoldiers() {
        return bonusSoldiers;
    }

    public void setBonusSoldiers(int bonusSoldiers) {
        this.bonusSoldiers = bonusSoldiers;
    }

    public int getPlayerNumber() {
    return playerNumber;
    }

    public void removeSoldiers(int soldiers){
        this.soldiers -= soldiers;
    }
    
}





