import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame {

    
    public GameView(JPanel boardView, JPanel playerView){
       
        setTitle("Java-Risk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Adding the BoardView to the center of the JFrame
        add(boardView, BorderLayout.CENTER);
        add(playerView, BorderLayout.EAST);

        setSize(1200, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setVisible(true);
    }
}
