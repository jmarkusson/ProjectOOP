package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import group6.Model.RiskModel;
import group6.View.PlayerStateView;


public class PlayerStateController implements ActionListener{
    
    private PlayerStateView view;
    private RiskModel model;
    private int currentStateIndex;

    PlayerStateController (PlayerStateView view, RiskModel model)
    {
        this.view = view;
        this.model = model;
        view.addController(this);
        this.currentStateIndex = 0;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("next")){
        currentStateIndex = (currentStateIndex + 1) % 3;
        view.updateStateView(currentStateIndex);
        }
    }



}