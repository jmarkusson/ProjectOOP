package group6.Model;


import java.util.List;

import group6.Model.Interfaces.Ownable;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RiskModel {

    private ArrayList<Player> players = new ArrayList<>();
    private Board board;
    private int nmbrOfPlayers = 2;
    private PlayerOwnership playerOwnership = new PlayerOwnership();
    private int currentPlayer;

    public RiskModel(){

         /* Nothing should happen when instance of Model is created */
    }
    

    public boolean initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors){
        
        Boolean succesfullLoad = false;
        board = new Board();

        List<String> list = new ArrayList<>();
        List<Point[]> listOfPointArrays = new ArrayList<>();
        List<Point> listOfSunPositions = new ArrayList<>();
        
        String line;
        

        try {
            // Reads and split planets into array
            InputStream planetStream = getClass().getClassLoader().getResourceAsStream("textfiles/planets.txt");
            BufferedReader readerplanet = new BufferedReader(new InputStreamReader(planetStream));

            while ((line = readerplanet.readLine()) != null) {
                list.add(line);
            }
            String[] planetsArray = list.toArray(new String[0]);
            
            //Reand and splits SunPoints into array
            InputStream sunpointStream = getClass().getClassLoader().getResourceAsStream("textfiles/solarPoints.txt");
            BufferedReader sunreaderPoint = new BufferedReader(new InputStreamReader(sunpointStream));
            while ((line = sunreaderPoint.readLine()) != null) {
                String[] parts = line.split(",");       
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                listOfSunPositions.add(new Point(x, y));
            }
            Point[] solarPointsArray = listOfSunPositions.toArray(new Point[0]);
            

            // Reads and splits PlanetPoints into array
            
            InputStream pointStream = getClass().getClassLoader().getResourceAsStream("textfiles/Points.txt");
            BufferedReader readerPoint = new BufferedReader(new InputStreamReader(pointStream));
            
            

            while ((line = readerPoint.readLine()) != null) {
                String[] pointStrings = line.split("\\s+");
                Point[] points = new Point[pointStrings.length];
                for (int i = 0; i < pointStrings.length; i++) {
                    points[i] = parsePoint(pointStrings[i]);
                }
                listOfPointArrays.add(points);
            }
            Point[][] pointsArray = listOfPointArrays.toArray(new Point[listOfPointArrays.size()][]);
            
            // Reads and split solar systems with its bonus number and planets
            list.clear();
            InputStream solarSystemStream = getClass().getClassLoader().getResourceAsStream("textfiles/SolarSystems.txt");
            BufferedReader readerSolarSystems = new BufferedReader(new InputStreamReader(solarSystemStream));

            while ((line = readerSolarSystems.readLine()) != null){
                list.add(line);
            }
            String[] solarsystemsArray = list.toArray(new String[0]);

            // Reads and splits adjacencies
            list.clear();
            InputStream adjacenciesStream = getClass().getClassLoader().getResourceAsStream("textfiles/AdjacentPlanets.txt");
            BufferedReader readerAdjacentPlanets = new BufferedReader(new InputStreamReader(adjacenciesStream));

            while ((line = readerAdjacentPlanets.readLine()) != null){
                list.add(line);
            }
            String[] adjacenciesArray = list.toArray(new String[0]);

            //Create the board
            succesfullLoad = board.loadBoard(planetsArray, solarsystemsArray, adjacenciesArray, pointsArray, solarPointsArray);

            players = new ArrayList<Player>();

            for(int i = 0; i < playerNames.size(); i++){

                players.add(new Player(playerNames.get(i), playerColors.get(i), i));

            }
            
            

            readerplanet.close();
            readerPoint.close();
            readerSolarSystems.close();
            readerAdjacentPlanets.close();
    } catch (FileNotFoundException e) {
        System.err.println("One or more files were not found: " + e.getMessage());
        // Handle the case where a file wasn't found, such as logging or user notification
    } catch (IOException e) {
        System.err.println("An error occurred while reading from the file: " + e.getMessage());
        // Handle other I/O errors
    }

        return false;

    }

    public void distributePlanets(){
        List<Planet> shuffledPlanets = getShuffledPlanets();

        initializePlayersReinforceableSoldiers();

        evenlyDistributeInitalPlanets(shuffledPlanets);

        distributeRemainingSoldiers(shuffledPlanets);
    }

    private List<Planet> getShuffledPlanets(){
        List<Planet> shuffledPlanets = new ArrayList<>(board.getPlanets());
        Collections.shuffle(shuffledPlanets);
        return shuffledPlanets;
    }

    private void initializePlayersReinforceableSoldiers(){
        for (Player player : players){
            player.setReinforceableSoldiers(player.getSoldiers());
        }

    }

    private void evenlyDistributeInitalPlanets(List<Planet> planets){
        for (int i = 0; i < planets.size(); i++){
            playerOwnership.assignOwnership(planets.get(i), players.get(i % players.size()));
            planets.get(i).addSoldiers(1);
            players.get(i).removeReinforceableSoldiers(1);

        }

    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }
    private void distributeRemainingSoldiers(List<Planet> planets){
        int i = 0; // Start from the beginning of the planet list
        while (playersHaveReinforceableSoldiers()) {
            Player currentPlayer = players.get(i % players.size());
            Planet currentPlanet = planets.get(i % planets.size());

            if (currentPlayer.getReinforceableSoldiers() > 0) {
                currentPlanet.addSoldiers(1);
                currentPlayer.removeReinforceableSoldiers(1);
            }
            i++;
        }
    }

    private boolean playersHaveReinforceableSoldiers(){
            for (Player player : players){
                if (player.getReinforceableSoldiers() > 0){
                    return true;
                }
            }
            return false;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public Player getPlayer(int i){
        return players.get(i);
    }
    public void setnmbOfPlayers(int nmbrOfPlayers){
        this.nmbrOfPlayers = nmbrOfPlayers;
    }

    public int getnmbrOfPlayers(){
        return this.nmbrOfPlayers;
    }

    public String[] getPlanetNames(){
        return board.getPlanetNames();
    }

    public Point[][] getPlanetPositions() {
        return board.getPlanetPositions();
    }

    public Point[] getSolarPositions(){
        return board.getSolarPositions();
    }
        
    

    private Point parsePoint(String str) {
        String[] parts = str.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new Point(x, y);
    }
    
    
}
