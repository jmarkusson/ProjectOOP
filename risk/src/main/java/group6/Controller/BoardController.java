package group6.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Point;

import group6.Model.GameStateReinforce;
import group6.Model.RiskModel;
import group6.Model.Interfaces.GameState;
import group6.View.BoardView;

public class BoardController implements ActionListener{
    
    private RiskModel model;
    private BoardView view;
    private String[] planetNames;
    private Point[][] planetPositions;
    private Point[] solarPositions;
    private GameState currentGameState;

    
    

    protected BoardController(RiskModel model, BoardView view){
        this.model = model;
        this.view = view;
        this.planetNames = model.getPlanetNames();
        this.planetPositions = model.getPlanetPositions();
        this.solarPositions = model.getSolarPositions();
        view.initializePlanetButtons(this);
        setCurrentGameState(new GameStateReinforce());
    }



    public void setCurrentGameState(GameState gameState){
        currentGameState = gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentGameState.initState(model, e.getActionCommand());
    }
}