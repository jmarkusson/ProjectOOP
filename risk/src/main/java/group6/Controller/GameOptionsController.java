package group6.Controller;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import group6.Model.Player;
import group6.Model.RiskModel;
import group6.View.BoardView;
import group6.View.GameOptionsView;
import group6.View.GameView;
import group6.View.PlayerStateView;
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
            model.setnmbOfPlayers(source.getSelectedIndex() +2);
        }

        else if (e.getActionCommand().equals("NEXT")){
            System.out.println("yes");
    
            view.mainView(model.getnmbrOfPlayers());
        }
        else if (e.getActionCommand().equals("StartGame")) {
            
            ArrayList<PlayerView> playerViews = new ArrayList<>();
            
            playerNames = new ArrayList<String>();
            playerColors = new ArrayList<Color>();

            for (int i = 0; i < view.textfields.size(); i++) {
                JTextField playerNameField = (JTextField) view.textfields.get(i);
                String playerName = playerNameField.getText();

                playerNames.add(playerName);
                // Hard coded colors for now
                playerColors.add(Color.RED);
            }

            model.initGame(playerNames, playerColors);

            for (int i = 0; i < model.getPlayers().size(); i++){
                playerViews.add(new PlayerView(model.getPlayer(i)));
            }
            view.dispose();
            PlayerStateView playerStateView = new PlayerStateView();
            playerStateView.addController(new PlayerStateController(playerStateView, model));
            GameView gameView = new GameView(new BoardView(model.getPlanetNames(), model.getPlanetPositions(), model.getSolarPositions()), playerViews, playerStateView);
    
            } 
            
        else if (e.getActionCommand().equals("Quit Game")) {
            view.dispose();
            }
    }

    
}