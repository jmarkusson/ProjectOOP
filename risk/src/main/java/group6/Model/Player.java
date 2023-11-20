package group6.Model;


import java.util.List;

import group6.Model.Interfaces.PlayerTurnState;

import java.awt.Color;

public class Player
{
    private Color color; 
    private String name;
    private int soldiers;
    private int reinforceableSoldiers;
    private int playerNumber;
    private List<Planet> planetsOwned;

    private PlayerTurnState currentState;

    public Player(String name, Color color, int playerNumber) {
        this.name = name;
        this.color = color;
        this.playerNumber = playerNumber;
        this.soldiers = 15;  
    }
    
        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSoldiers() {
            return soldiers;
        }

        public void setSoldiers(int soldiers) {
            this.soldiers = soldiers;
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

        public void setState(PlayerTurnState state){
            this.currentState = state;
        }

        public void placeSoldiers(int numberOfSoldiers, Planet planet){
            currentState.placeSoldiers(this, numberOfSoldiers, planet);
        }

        public void attack(Planet fromPlanet, Planet toPlanet){
            currentState.attack(this, fromPlanet, toPlanet);
        }
}





