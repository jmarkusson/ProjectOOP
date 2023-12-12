package group6.Controller;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import group6.Model.RiskModel;
import group6.View.BoardView;
import group6.View.GameOptionsView;
import group6.View.GameView;
import group6.View.PlayerView;

public class GameOptionsController implements ActionListener{

    private RiskModel model;
    private GameOptionsView view;

    private ArrayList<String> playerNames;
    private ArrayList<Color> playerColors;

    public GameOptionsController(RiskModel model, GameOptionsView view){
        this.model = model;
        this.view = view;
        view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("combobox")){
            JComboBox source = (JComboBox) e.getSource();
            model.setnmbOfPlayers(source.getSelectedIndex()+2);
        }

        else if (e.getActionCommand().equals("NEXT")){
    
            view.mainView(model.getnmbrOfPlayers(), model.getColors());
        }
        else if (e.getActionCommand().equals("colorChooser")){

        }
        else if (e.getActionCommand().equals("StartGame")) {
            
            ArrayList<PlayerView> playerViews = new ArrayList<>();
            
            playerNames = new ArrayList<String>();
            playerColors = new ArrayList<Color>();

            ArrayList<Color> selectedColors = new ArrayList<>();

            for (int i = 0; i < view.getTextfields().size(); i++) {
                JTextField playerNameField = view.getTextfields().get(i);
                JComboBox colorBox = view.getColorBoxes().get(i);
                String playerName = playerNameField.getText();
                if(playerName.isEmpty()){
                    JOptionPane.showMessageDialog(view, "Playername has not been provided", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                Color playerColor = (Color) colorBox.getSelectedItem();
                if (selectedColors.contains(playerColor)) {
                    JOptionPane.showMessageDialog(view, "Color already selected by another player", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                selectedColors.add(playerColor);

                playerNames.add(playerName);
                playerColors.add(playerColor); 
                
            }

            model.initGame(playerNames, playerColors);
            
            for (int i = 0; i < model.getPlayers().size(); i++){
                playerViews.add(new PlayerView(model.getPlayer(i)));
                model.addPlayerObserver(playerViews.get(i));
            }
            view.dispose();
            BoardView boardview = new BoardView(model.getPlanetNames(), model.getPlanetPositions(), model.getSolarPositions(), model.getPlanetColorMap());
            
            BoardController boardViewController = new BoardController(model, boardview);
            GameView gameView = new GameView(boardview, playerViews);
    
            } 
            
        else if (e.getActionCommand().equals("Quit Game")) {
            view.dispose();
            }
    }

    
}