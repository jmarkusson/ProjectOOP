package group6.View;




import javax.swing.*;

import group6.Controller.BoardController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardView extends JPanel {

    private final Point[] sunPositions;
    
    private Point[][] planetPositions;
    private String[] planetNames;
    private Map<String, JLabel> planetLabels;
    
    private JButton nextButton;
    private JLabel currentStateLabel;

    private ArrayList<String> states;

    private int[] sunSizes = {80, 100, 120, 140}; // Four different sizes for suns
    private int[] planetSizes = {60, 70, 80, 90}; // Four different sizes for planets

    public BoardView(String[] planetNames, Point[][] planetPositions, Point[] sunPositions){
        this.planetNames = planetNames;
        this.planetPositions = planetPositions;
        this.sunPositions = sunPositions;
        planetLabels = new HashMap<>();
        this.setLayout(null);
        setPreferredSize(new Dimension(200,200));
        states = new ArrayList<>();
        states.add("REINFORCE");
        states.add("ATTACK");
        states.add("FORTIFY");
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
        for (int x = 0; x < planetPositions.length; x++) {
            for (int y = 0; y < planetPositions[x].length; y++) {
                Point planetPosLabel = planetPositions[x][y];
                String planetNameLabel = planetNames[x * planetPositions[x].length + y];
                int labelSize = 20; // Adjust as needed
    
                JLabel planetLabel = new JLabel("14"); // Default value
                planetLabel.setBounds(planetPos.x -4, planetPos.y + 5, labelSize, labelSize);
                planetLabel.setForeground(Color.WHITE); // Set the text color
                this.add(planetLabel);
    
                planetLabels.put(planetName, planetLabel);
            }
        }
        
                
                planetButton.setOpaque(false);
                planetButton.setContentAreaFilled(false);
                planetButton.setBorderPainted(false);
                planetButton.setForeground(Color.WHITE); // Set the text color to white or any color you prefer
                planetButton.setHorizontalTextPosition(SwingConstants.CENTER);
                planetButton.setVerticalTextPosition(SwingConstants.CENTER);
            }
        }  
        this.nextButton = new JButton("NEXT STATE");
        this.nextButton.setActionCommand("NEXT");
        this.nextButton.setBounds(700, 700, 200, 50);
        this.currentStateLabel = new JLabel(states.get(0));
        this.currentStateLabel.setBounds(800, 50, 500, 100);
        this.currentStateLabel.setSize(new Dimension(100,100));
        this.currentStateLabel.setForeground(Color.WHITE);
        this.nextButton.addActionListener(controller);

        add(nextButton);
        add(currentStateLabel);

        this.revalidate();
        this.repaint();
    }

    public void updateCurrentStateLabel(int currentState){
        this.currentStateLabel.setText(states.get(currentState));
    }

    public void updatePlanetValue(String planetName, int numberToChange) {
        JLabel label = planetLabels.get(planetName);
        if (label != null) {
            int currentValue = Integer.parseInt(label.getText());
            int newValue = currentValue + numberToChange;
            
            label.setText(String.valueOf(newValue));
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        for (int z = 0; z < 200; z++) { 
        int x = (int)(Math.random() * getWidth());
        int y = (int)(Math.random() * getHeight());
        g.fillOval(x, y, 2, 2); // Each star is a small dot
        }
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

