import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuController implements ActionListener {

    private MainMenuView view;
    private RiskModel model;
    private BoardView boardView;
    private GameView gameView;
    
    public MainMenuController(RiskModel model, MainMenuView view){

        this.model = model;
        this.view = view;
        view.mainMenuActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand() == "New Game"){
            view.setVisible(false);
            //Hard coded !!!
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<Color> colors = new ArrayList<Color>();
            names.add("Valle");
            names.add("Max");
            colors.add(Color.red);
            colors.add(Color.blue);
            model.initGame(names, colors);
            boardView = new BoardView();
            boardView.addActionListener(new BoardViewController(model, boardView));
            Player player1 = new Player("valle", Color.RED, 10);
            Player player2 = new Player("Max", Color.blue, 5);
            ArrayList<Player> players = new ArrayList<Player>();
            players.add(player1);
            players.add(player2);
            PlayerView playerView = new PlayerView(players);
            gameView = new GameView(boardView, playerView);
            
        }
    }
}
