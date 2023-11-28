package group6.Controller;
import java.awt.event.MouseAdapter;

import java.awt.Point;

import group6.Model.RiskModel;
import group6.View.BoardView;

public class BoardViewController extends MouseAdapter{
    
    private RiskModel model;
    private BoardView view;
    private String[] planetNames;
    private Point[][] planetPositions;
    private Point[] solarPositions;

    

    protected BoardViewController(RiskModel model, BoardView view){
        this.model = model;
        this.view = view;
        this.planetNames = model.getPlanetNames();
        this.planetPositions = model.getPlanetPositions();
        this.solarPositions = model.getSolarPositions();
      
        view.addController(this);
    }
    
    

}
