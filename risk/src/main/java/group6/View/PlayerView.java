package group6.View;
import javax.swing.*;

import group6.Model.Player;

import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerView extends JPanel {

    private Player player;

    private JLabel nameLabel;
    private JLabel soldiersLabel;
    private JLabel planetsOwnedLabel;
    private JLabel reinforcementLabel;

    public PlayerView(Player player) {
        this.player = player;
        setPreferredSize(new Dimension(200, 100));
        setBackground(Color.WHITE);

      
        nameLabel = new JLabel();
        nameLabel.setText(this.player.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        Font stateFont = nameLabel.getFont().deriveFont(20f);
        nameLabel.setFont(stateFont);
        soldiersLabel = new JLabel();
        soldiersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        planetsOwnedLabel = new JLabel();
        reinforcementLabel = new JLabel();
        reinforcementLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Color playerColor = this.player.getColor();
        nameLabel.setForeground(playerColor);
        soldiersLabel.setForeground(playerColor);
        planetsOwnedLabel.setForeground(playerColor);
        reinforcementLabel.setForeground(playerColor);

        setLayout(new GridLayout(4,1));

        add(nameLabel);
        add(soldiersLabel);
        add(planetsOwnedLabel);
        add(reinforcementLabel);


        updatePlayerInfo();
    }

    public void addController(ActionListener controller) {
       
    }

    public void updatePlayerInfo() {
        soldiersLabel.setText("Soldiers: " + this.player.getSoldiers());

        reinforcementLabel.setText("Reinforcements: " + this.player.getReinforceableSoldiers());


        //planetsOwnedLabel.setText("Planets Owned: " + (this.player.getPlanetsOwned().size()));

    }
}