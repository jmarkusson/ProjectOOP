package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import group6.Model.RiskModel;
import group6.View.ReinforceView;

public class ReinforceController implements ActionListener {

    ReinforceView view;
    RiskModel model;

    public ReinforceController(RiskModel model, ReinforceView view){
        
        this.model = model;
        this.view = view;

        view.addController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("addsoldiers")){

        }
    }
    
}
