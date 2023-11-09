import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardViewController implements ActionListener {
    
    private RiskModel model;
    private BoardView view;

    protected BoardViewController(RiskModel model, BoardView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
