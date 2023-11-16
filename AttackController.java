

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackController implements ActionListener {
    
    private AttackView attackView;
    private RiskModel rModel;
    
    AttackController (AttackView attackView, RiskModel rModel)
    {
        this.attackView = attackView;
        this.rModel = rModel;
        
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }}
