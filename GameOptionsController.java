

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class GameOptionsController implements ActionListener{

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
            model.setnmbOfPlayers(source.getSelectedIndex() +2);
        }

        else if (e.getActionCommand().equals("NEXT")){
            System.out.println("yes");
    
            view.mainView(model.getnmbrOfPlayers());
        }
        else if (e.getActionCommand().equals("StartGame")) {
            
            ArrayList<PlayerView> playerViews = new ArrayList<>();

            for (int i = 0; i < view.textfields.size(); i++) {
                JTextField playerNameField = (JTextField) view.textfields.get(i);
                String playerName = playerNameField.getText();
                model.addPlayer(new Player(playerName, new Color(i), i));
                playerViews.add(new PlayerView(model.getPlayer(i)));
            }

            view.dispose();
            GameView gameView = new GameView(new BoardView(), playerViews);
    
            } 
            
        else if (e.getActionCommand().equals("Quit Game")) {
            view.dispose();
            }
    }

    
}