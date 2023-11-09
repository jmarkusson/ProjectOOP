import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskController implements ActionListener {

    private RiskView view;
    private RiskModel model;
    private Board.BoardView boardView;
    
    public RiskController(RiskModel model, RiskView view){

        this.model = model;
        this.view = view;
        view.mainMenuActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand() == "New Game"){
            boardView = new Board.BoardView();

            boardView.setVisible(true);
            boardView.addAct
        }
    }
}
