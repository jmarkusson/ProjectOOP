package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import group6.Model.Planet;
import group6.Model.RiskModel;
import group6.View.ReinforceView;

public class ReinforceController implements ActionListener {

    ReinforceView view;
    RiskModel model;
    int soldiersAmount;

    public ReinforceController(RiskModel model, ReinforceView view){
        
        this.model = model;
        this.view = view;

        view.addController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("combobox")){
            JComboBox source = (JComboBox) e.getSource();
            this.soldiersAmount = source.getSelectedIndex();  
        }
        else if(e.getActionCommand().equals("addsoldiers")){
            model.ReinforcePlanet(view.getplanetName(), soldiersAmount);
            
            view.dispose();

        }
    }
    
}
