package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import group6.Model.RiskModel;
import group6.View.FortifyView;

public class FortifyController implements ActionListener {

    FortifyView view;
    RiskModel model;
   

    public FortifyController(RiskModel model, FortifyView view){
        
        this.model = model;
        this.view = view;

        view.addController(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("fortify")){
            
        }
    }
    
}
