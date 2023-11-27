package group6.View;




import javax.swing.*;

import group6.Controller.BoardViewController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class BoardView extends JPanel {

    private final Point[] sunPositions;
    
    private Point[][] planetPositions;
    private String[] planetNames;
    

    private int[] sunSizes = {80, 100, 120, 140}; // Four different sizes for suns
    private int[] planetSizes = {50, 60, 70, 80}; // Four different sizes for planets

    public BoardView(String[] planetNames, Point[][] planetPositions, Point[] sunPositions){
        this.planetNames = planetNames;
        this.planetPositions = planetPositions;
        this.sunPositions = sunPositions;
        

        setPreferredSize(new Dimension(200,200));
    }

    public void add(JButton button) {
        this.add(button);
        this.revalidate();
        this.repaint();
    }

    public void initializePlanets(ActionListener controller) {
        for (int i = 0; i < planetPositions.length; i++) {
            for (int j = 0; j < planetPositions[i].length; j++) {
                Point planetPos = planetPositions[i][j];
                String planetName = planetNames[i * planetPositions[i].length + j];
                JButton planetButton = new JButton(planetName);
                int buttonSize = 20; // Or calculate the size you need
                planetButton.setBounds(planetPos.x - buttonSize / 2, planetPos.y - buttonSize / 2, buttonSize, buttonSize);
                planetButton.addActionListener(controller);
                this.add(planetButton);
            }
        }
        this.revalidate();
        this.repaint();
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        //Draw the stars
        g.setColor(Color.WHITE);
        for (int z = 0; z < 200; z++) { // Draw 200 stars
        int x = (int)(Math.random() * getWidth());
        int y = (int)(Math.random() * getHeight());
        g.fillOval(x, y, 2, 2); // Each star is a small dot
        }
        // Draw the suns
        g.setColor(Color.YELLOW);
        for (int i = 0; i < sunPositions.length; i++) {
            Point sunPosition = sunPositions[i];
            int size = sunSizes[i]; // Use the corresponding size from sunSizes array
            g.fillOval(sunPosition.x - size / 2, sunPosition.y - size / 2, size, size);
        }
        // Draw the planets
        for (int i = 0; i < planetPositions.length; i++) {
            for (int j = 0; j < planetPositions[i].length; j++) {
                Point planetPos = planetPositions[i][j];
                int size = planetSizes[j]; // Use the size based on the planet's index
                
                // Draw planet with varying size
                g.setColor(Color.getHSBColor(j / (float) planetPositions[i].length, 0.7f, 0.9f));
                g.fillOval(planetPos.x - size / 2, planetPos.y - size / 2, size, size);
                

                // Draw name
                g.setColor(Color.BLACK);
                g.drawString(planetNames[i * planetPositions[i].length + j], planetPos.x-20, planetPos.y );
            }
        }
    }

}

