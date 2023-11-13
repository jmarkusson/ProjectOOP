import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class GameOptionsController implements ActionListener{

    private ArrayList<Player> players = new ArrayList<>();
    private int nmbrOfPlayers = 2;

    private RiskModel model;
    private GameOptionsView view;

    public GameOptionsController(RiskModel model, GameOptionsView view){
        this.model = model;
        this.view = view;
        view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("combobox")){
            JComboBox source = (JComboBox) e.getSource();
            nmbrOfPlayers = source.getSelectedIndex() + 1;
        }

        else if (e.getActionCommand().equals("NEXT")){
            System.out.println("yes");
    
            view.mainView(nmbrOfPlayers);
        }
        else if (e.getActionCommand().equals("textfield")) {
            JTextField source = (JTextField) e.getSource();
            String playerName = source.getText();
            players.add(new Player(playerName, new Color(players.size()+1), players.size()+1));

            } 
        else if (e.getActionCommand().equals("StartGame")) {
            view.dispose();
            GameView gameView = new GameView(new BoardView(), new PlayerView(players));
            } 
        else if (e.getActionCommand().equals("Quit Game")) {

            }
    }

    
}