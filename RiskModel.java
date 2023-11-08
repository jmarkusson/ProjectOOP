import java.awt.Color;
import java.util.List;

import Board.BoardView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RiskModel {

    private ArrayList<String> playerNames;
    private ArrayList<Color> playerColors;

    protected RiskModel(){
         /* Nothing should happen when instance of Model is created */
    }

    public boolean initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors){
        
        BoardView board = new BoardView();
        this.playerNames = playerNames;
        this.playerColors = playerColors;

        List<String> planets = new ArrayList<>();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("planets.txt"));

            while ((line = reader.readLine()) != null) {
                planets.add(line);
            }
            String[] planetsArray = planets.toArray(new String[0]);

            reader = new BufferedReader(new FileReader("solarsystems.txt"));

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("due bajs");
        }

        return false;
    }
}
