package group6.Controller;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import group6.Model.RiskModels.ModelFacade;
import group6.View.BoardView;
import group6.View.GameOptionsView;
import group6.View.GameView;
import group6.View.PlayerView;

public class GameOptionsController implements ActionListener{

    private ModelFacade modelFacade;
    private GameOptionsView view;

    private ArrayList<String> playerNames;
    private ArrayList<Color> playerColors;

    public GameOptionsController(ModelFacade modelFacade, GameOptionsView view){
        this.modelFacade = modelFacade;
        this.view = view;
        view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("combobox")){
            JComboBox source = (JComboBox) e.getSource();
            modelFacade.setnmbOfPlayers(source.getSelectedIndex()+2);
        }

        else if (e.getActionCommand().equals("NEXT")){
    
            view.mainView(modelFacade.getnmbrOfPlayers(), modelFacade.getPlayerColors());
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

            modelFacade.initGame(playerNames, playerColors);
            
            for (int i = 0; i < modelFacade.getPlayers().size(); i++){
                playerViews.add(new PlayerView(modelFacade.getPlayer(i)));
                modelFacade.addPlayerObserver(playerViews.get(i));
            }
            view.dispose();
            BoardView boardview = new BoardView(modelFacade.getPlanetNames(), modelFacade.getPlanetPositions(), modelFacade.getSolarPositions(), modelFacade.getPlanetColorMap());
            
            BoardController boardViewController = new BoardController(modelFacade, boardview);
            GameView gameView = new GameView(boardview, playerViews);
            GameStateController gameStateController = new GameStateController(modelFacade, boardview);
            
            } 
            
        else if (e.getActionCommand().equals("Quit Game")) {
            view.dispose();
            }
    }

    
}