import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuController implements ActionListener {

    private MainMenuView view;
    private RiskModel model;

    
    public MainMenuController(RiskModel model, MainMenuView view){

        this.model = model;
        this.view = view;
        view.mainMenuActionListener(this);
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
