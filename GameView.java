import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class GameView extends JFrame {

    private BoardView boardView;
    private PlayerView playerView;
    
    public GameView(BoardView boardView, PlayerView playerView){

        this.boardView = boardView;
        this.playerView = playerView;
       
        setTitle("Java-Risk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Adding the BoardView to the center of the JFrame
        add(this.boardView, BorderLayout.CENTER);
        add(this.playerView, BorderLayout.EAST);

        setSize(1200, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setVisible(true);
    }

    public void addController(ActionListener controller){
        boardView.addController(controller);
        playerView.addController(controller);
    }
}
