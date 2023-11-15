import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerView extends JPanel {


    private ArrayList<Player> players;

    public PlayerView(ArrayList<Player> players) {
        this.players = players;
        setPreferredSize(new Dimension(200, getHeight())); // Set the preferred width for the player panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color for the player panel, if desired
        setBackground(Color.LIGHT_GRAY);

        // Set the starting y position for drawing
        int y = 30;
        for (Player player : players) {
            // For each player, draw their name and other details
            g.setColor(player.getColor()); // Assume Player has a getColor method
            g.drawString(player.getName(), 10, y); // Assume Player has a getName method
            // Add other player details as needed...

            // Increment the y position for the next player
            y += 40;
        }
    }
}

