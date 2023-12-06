package group6.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import group6.Model.RiskModel;
import group6.View.AttackView;

public class AttackController implements ActionListener {
    
    private AttackView attackView;
    private RiskModel rModel;
    
    public AttackController (AttackView attackView, RiskModel rModel)
    {
        this.attackView = attackView;
        this.rModel = rModel;
        attackView.setController(this);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }}
