import java.awt.Color;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RiskModel {

    private ArrayList<String> playerNames;
    private ArrayList<Color> playerColors;

    private ArrayList<Player> players;
    private Board board;

    protected RiskModel(){
         /* Nothing should happen when instance of Model is created */
    }

    public boolean initGame(ArrayList<String> playerNames, ArrayList<Color> playerColors){
        
        Boolean succesfullLoad = false;
        board = new Board();
        this.playerNames = playerNames;
        this.playerColors = playerColors;

        List<String> list = new ArrayList<>();
        String line;

        try {
            // Reads and split planets into array
            BufferedReader reader = new BufferedReader(new FileReader("planets.txt"));

            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            String[] planetsArray = list.toArray(new String[0]);

            // Reads and split solar systems with its bonus number and planets
            list.clear();
            reader = new BufferedReader(new FileReader("solarsystems.txt"));

            while ((line = reader.readLine()) != null){
                list.add(line);
            }
            String[] solarsystemsArray = list.toArray(new String[0]);

            // Reads and splits adjacencies
            list.clear();
            reader = new BufferedReader(new FileReader("adjacencies.txt"));

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
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("due bajs");
        }



        return false;
    }
}
