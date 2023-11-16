package group6.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import group6.Model.RiskModel;
import group6.View.BoardView;

public class BoardViewController implements ActionListener{
    
    private RiskModel model;
    private BoardView view;

    protected BoardViewController(RiskModel model, BoardView view){
        this.model = model;
        this.view = view;
        view.addController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}