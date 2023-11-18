package group6.Model;


import java.util.List;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RiskModel {

    private ArrayList<Player> players = new ArrayList<>();
    private Board board;
    private int nmbrOfPlayers = 2;

    public RiskModel(){

         /* Nothing should happen when instance of Model is created */
    }

    public boolean initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors){
        
        Boolean succesfullLoad = false;
        board = new Board();

        List<String> list = new ArrayList<>();
        String line;

        try {
            // Reads and split planets into array
            BufferedReader reader = new BufferedReader(new FileReader("textfiles/planets.txt"));

            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            String[] planetsArray = list.toArray(new String[0]);

            // Reads and split solar systems with its bonus number and planets
            list.clear();
            reader = new BufferedReader(new FileReader("textfiles/SolarSystems.txt"));

            while ((line = reader.readLine()) != null){
                list.add(line);
            }
            String[] solarsystemsArray = list.toArray(new String[0]);

            // Reads and splits adjacencies
            list.clear();
            reader = new BufferedReader(new FileReader("textfiles/AdjacentPlanets.txt"));

            while ((line = reader.readLine()) != null){
                list.add(line);
            }
            String[] adjacenciesArray = list.toArray(new String[0]);

            //Create the board
            succesfullLoad = board.loadBoard(planetsArray, solarsystemsArray, adjacenciesArray);

            players = new ArrayList<Player>();

            for(int i = 0; i < playerNames.size(); i++){

                players.add(new Player(playerNames.get(i), playerColors.get(i), i));

            }
            

            reader.close();
    } catch (FileNotFoundException e) {
        System.err.println("One or more files were not found: " + e.getMessage());
        // Handle the case where a file wasn't found, such as logging or user notification
    } catch (IOException e) {
        System.err.println("An error occurred while reading from the file: " + e.getMessage());
        // Handle other I/O errors
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
    
}
