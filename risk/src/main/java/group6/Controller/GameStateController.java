package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import group6.Model.RiskModel;
import group6.View.BoardView;

public class GameStateController implements ActionListener {

    RiskModel model;

    public GameStateController(RiskModel model, BoardView view){

        this.model = model;
        view.addActionListenertostateButton(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        model.changeState();
    }
    
}
