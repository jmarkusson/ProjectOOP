import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuController implements ActionListener {

    private MainMenuView view;
    private RiskModel model;
    private BoardView boardView;
    
    public MainMenuController(RiskModel model, MainMenuView view){

        this.model = model;
        this.view = view;
        view.mainMenuActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand() == "New Game"){
            view.setVisible(false);
            model.initGame(null, null);
            boardView = new BoardView();
            boardView.addActionListener(new BoardViewController(model, boardView));
            boardView.setVisible(true);
            
        }
    }
}
