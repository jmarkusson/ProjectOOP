import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        else if (e.getActionCommand().equals("textfield")) {
            JTextField source = (JTextField) e.getSource();
            String playerName = source.getText();
            model.setPlayers(new Player(playerName, new Color(model.getnmbrOfPlayers()+1),
            model.getnmbrOfPlayers()+1));
            } 

        else if (e.getActionCommand().equals("StartGame")) {
            view.dispose();
            BoardView boardView = new BoardView();
            PlayerView playerView = new PlayerView(model.getPlayers());
            GameView gameView = new GameView(boardView, playerView);
            BoardViewController boardViewController = new BoardViewController(model, boardView);
            
            } 
            
        else if (e.getActionCommand().equals("Quit Game")) {
            view.dispose();
            }
    }

    
}