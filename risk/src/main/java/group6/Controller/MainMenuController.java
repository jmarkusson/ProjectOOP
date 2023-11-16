package group6.Controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import group6.Model.RiskModel;
import group6.View.GameOptionsView;
import group6.View.MainMenuView;


public class MainMenuController implements ActionListener {

    private MainMenuView view;
    private RiskModel model;

    
    public MainMenuController(RiskModel model, MainMenuView view){

        this.model = model;
        this.view = view;
        view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand() == "New Game"){
            view.dispose();
            
            GameOptionsView gameOptionsView = new GameOptionsView();
            GameOptionsController controller = new GameOptionsController(model, gameOptionsView);
            gameOptionsView.initView();
     
        }
        else{
            view.dispose();
        }
    }
}
