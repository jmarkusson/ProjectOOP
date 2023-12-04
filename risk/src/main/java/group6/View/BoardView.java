package group6.View;




import javax.swing.*;

import group6.Controller.BoardViewController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class BoardView extends JPanel {

    private final Point[] sunPositions;
    
    private Point[][] planetPositions;
    private String[] planetNames;
    
    

    private int[] sunSizes = {80, 100, 120, 140}; // Four different sizes for suns
    private int[] planetSizes = {60, 70, 80, 90}; // Four different sizes for planets

    public BoardView(String[] planetNames, Point[][] planetPositions, Point[] sunPositions){
        this.planetNames = planetNames;
        this.planetPositions = planetPositions;
        this.sunPositions = sunPositions;
        this.setLayout(null);

        setPreferredSize(new Dimension(200,200));
    }

    public void update() {
        
        this.revalidate();
        this.repaint();
    }

    public void initializePlanetButtons(ActionListener controller) {
        for (int i = 0; i < planetPositions.length; i++) {
            for (int j = 0; j < planetPositions[i].length; j++) {
                Point planetPos = planetPositions[i][j];
                String planetName = planetNames[i * planetPositions[i].length + j];
                int buttonSize = planetSizes[j] + 27; // Use the corresponding size from planetSizes array
                JButton planetButton = new JButton(planetName);
                
                // Set the bounds so the button is centered on the planet's position
                planetButton.setBounds(planetPos.x - buttonSize / 2, planetPos.y - buttonSize / 2, buttonSize, buttonSize);
                planetButton.addActionListener(controller);
                this.add(planetButton);
                
                planetButton.setOpaque(false);
                planetButton.setContentAreaFilled(false);
                planetButton.setBorderPainted(false);
                planetButton.setForeground(Color.WHITE); // Set the text color to white or any color you prefer
                planetButton.setHorizontalTextPosition(SwingConstants.CENTER);
                planetButton.setVerticalTextPosition(SwingConstants.CENTER);
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
                

                
            }
        }

        Map<String, Point> planetMap = createPlanetMap();
        String[] connections = {
            "Dagobah, Coruscant, Mustafar",
            "Tatooine, Dagobah",
            "Tatooine, Naboo, Sullust",
            "Alderaan, Naboo",
            "Hoth, Kashyyyk, Yavin IV",
            "Naboo, Sullust",
            "Alderaan, Bespin",
            "Bespin, Endor",
            "Endor, Fondor, Sullust",
            "Sullust, Endor",
            
            "Ithor, Fondor",
            "Ithor, Anoat",
            "Anoat, Yavin IV",
            "Hoth, Tatooine",
            "Jakku, Yavin IV",
            "Mustafar, Jakku",
            "Anoat, Sullust"
            
            
        };

        for (String connection : connections) {
            String[] planets = connection.split(", ");
            for (int i = 0; i < planets.length - 1; i++) {
                Point start = planetMap.get(planets[i]);
                Point end = planetMap.get(planets[i + 1]);
                if (start != null && end != null) {
                    drawDottedLine(g, start, end);
                }
            }
        }
    }
    private Map<String, Point> createPlanetMap() {
    Map<String, Point> planetMap = new HashMap<>();
    for (int i = 0; i < planetPositions.length; i++) {
        for (int j = 0; j < planetPositions[i].length; j++) {
            String planetName = planetNames[i * planetPositions[i].length + j];
            Point planetPos = planetPositions[i][j];
            planetMap.put(planetName, planetPos);
        }
    }
    return planetMap;
}   

    private void drawDottedLine(Graphics g, Point start, Point end) {
        Graphics2D g2d = (Graphics2D) g;
        float[] dashingPattern = {2, 2};
        Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern, 2.0f);
    
        g2d.setStroke(stroke);
        g2d.drawLine(start.x, start.y, end.x, end.y);
    }



}

