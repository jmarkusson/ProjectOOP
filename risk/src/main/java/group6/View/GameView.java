package group6.View;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GameView extends JFrame {

    private BoardView boardView;
    private ArrayList<PlayerView> playerViews;
    private JPanel playerPanel;
    private PlayerStateView playerStateView;
    
    public GameView(BoardView boardView, ArrayList<PlayerView> playerViews, PlayerStateView playerStateView){

        this.boardView = boardView;
        this.playerViews = playerViews;
        this.playerStateView = playerStateView;

       
        setTitle("Java-Risk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        add(this.boardView, BorderLayout.CENTER);
        playerPanel = new JPanel();
        playerPanel.setPreferredSize(new Dimension(200, getHeight()));
        for (PlayerView playerView : this.playerViews) {
            playerPanel.add(playerView);
        }
        add(this.playerPanel, BorderLayout.EAST);
        add(this.playerStateView, BorderLayout.SOUTH);

        setSize(1200, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setVisible(true);
    }

    

    public void addController(ActionListener controller){
        
        
    }
}