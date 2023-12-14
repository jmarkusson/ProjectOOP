package group6.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import group6.View.FortifyView;
import group6.Model.RiskModels.ModelFacade;

public class FortifyController implements ActionListener {

    FortifyView view;
    // RiskModel model;
    private ModelFacade modelFacade;
   

    public FortifyController(ModelFacade modelFacade, FortifyView view){
        
        this.modelFacade = modelFacade;
        this.view = view;

        view.addController(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("fortify")){
            String originPlanet = view.getPlanetName();
            String fortifyPlanet = (String) view.getAdjecentPlanetsBox().getSelectedItem();
            Integer soldiersAmount = (Integer) view.getAmountOfSoldiersBox().getSelectedItem();

            modelFacade.fortifyPlanet(originPlanet, fortifyPlanet, soldiersAmount);
            view.dispose();   
        }
    }
    
}
