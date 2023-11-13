import java.util.ArrayList;

public class Planet{

    private String name;
    private ArrayList<Planet> adjacentPlanets;

    public Planet(String name) {
        this.name = name;
    }

    public void setAdjacencies(ArrayList<Planet> adjacentPlanets) {
        this.adjacentPlanets = adjacentPlanets;
    }


}