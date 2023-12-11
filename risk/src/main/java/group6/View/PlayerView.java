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

        Color playerColor = this.player.getColor();
        nameLabel.setForeground(playerColor);
        soldiersLabel.setForeground(playerColor);
        planetsOwnedLabel.setForeground(playerColor);

        setLayout(new GridLayout(3,1));

        add(nameLabel);
        add(soldiersLabel);
        add(planetsOwnedLabel);


        updatePlayerInfo();
    }

    public void addController(ActionListener controller) {
       
    }

    public void updatePlayerInfo() {
        soldiersLabel.setText("Soldiers: " + this.player.getSoldiers());
        //planetsOwnedLabel.setText("Planets Owned: " + (this.player.getPlanetsOwned().size()));

    }
}