package group6.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import group6.Model.RiskModels.ModelFacade;
import group6.View.AttackView;

public class AttackController implements ActionListener {
    
    private AttackView attackView;
    private ModelFacade rModel;
    
    public AttackController (AttackView attackView, ModelFacade rModel)
    {
        this.attackView = attackView;
        this.rModel = rModel;
        attackView.setController(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ROLL")){
            attackView.dispose();

            String attackFromPlanet = attackView.getPlanetName();
            String planetToAttack = (String) attackView.getPlanetToAttackComboBox().getSelectedItem();
            Integer amountOfSoldiers = (Integer) attackView.getSoldiersComboBox().getSelectedItem();

            rModel.attackPlanet(attackFromPlanet, planetToAttack, amountOfSoldiers);
            

        }
    }
    
    }
