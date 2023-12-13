package group6.View;




import javax.swing.*;

import group6.Model.Interfaces.PlanetObserver;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BoardView extends JPanel implements PlanetObserver{

    private final Point[] sunPositions;
    
    private Point[][] planetPositions;
    private String[] planetNames;
    private Map<String, JLabel> planetLabels;
    
    public JLabel getCurrentPlayerLabel() {
        return currentPlayerLabel;
    }

    private JButton nextButton;
    private JLabel currentStateLabel;
    private JLabel currentPlayerLabel;


    private int[] sunSizes = {80, 100, 120, 140}; // Four different sizes for suns
    private int[] planetSizes = {60, 70, 80, 90}; // Four different sizes for planets
    private HashMap<String, Color> planetColors;

    public BoardView(String[] planetNames, Point[][] planetPositions, Point[] sunPositions, HashMap<String, Color> planetColors){
        this.planetNames = planetNames;
        this.planetPositions = planetPositions;
        this.sunPositions = sunPositions;
        this.planetColors = planetColors;
        planetLabels = new HashMap<>();
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

                String planetName = planetNames[i * planetPositions[i].length + j];
                Point planetPos = planetPositions[i][j];

                JButton planetButton = createPlanetButton(planetName, planetPos, planetSizes[j], controller);
                this.add(planetButton);

                JLabel planetLabel = createPlanetLabel(planetPos, "0"); 
                this.add(planetLabel);
                planetLabels.put(planetName, planetLabel);
            }
        }

        this.nextButton = new JButton("NEXT STATE");
        this.nextButton.setActionCommand("NEXT");
        this.nextButton.setBounds(770, 700, 200, 50);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(Color.DARK_GRAY);
        this.nextButton.addActionListener(controller);

        this.currentStateLabel = new JLabel("REINFORCE");
        this.currentStateLabel.setBounds(650, 50, 300, 100);
        Font stateFont = currentStateLabel.getFont().deriveFont(20f);
        this.currentStateLabel.setFont(stateFont);
        this.currentStateLabel.setForeground(Color.WHITE);
        this.currentStateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.currentPlayerLabel = new JLabel();
        this.currentPlayerLabel.setBounds(655, 2, 300, 50);
        Font playerFont = currentPlayerLabel.getFont().deriveFont(30f);
        this.currentPlayerLabel.setFont(playerFont);
        this.currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(currentPlayerLabel);
        add(nextButton);
        add(currentStateLabel);

        this.revalidate();
        this.repaint();
    }

    private JButton createPlanetButton(String planetName, Point position, int size, ActionListener controller) {
        JButton planetButton = new JButton(planetName);
        int buttonSize = size + 27; // Use the corresponding size from planetSizes array
        planetButton.setBounds(position.x - buttonSize / 2, position.y - buttonSize / 2, buttonSize, buttonSize);
        planetButton.addActionListener(controller);

        planetButton.setOpaque(false);
        planetButton.setContentAreaFilled(false);
        planetButton.setBorderPainted(false);
        planetButton.setForeground(Color.BLACK); // Set the text color to white or any color you prefer
        planetButton.setHorizontalTextPosition(SwingConstants.CENTER);
        planetButton.setVerticalTextPosition(SwingConstants.CENTER);

        return planetButton;
    }

    private JLabel createPlanetLabel(Point position, String text) {
        JLabel planetLabel = new JLabel(text);
        int labelSize = 20;
        planetLabel.setBounds(position.x - 4, position.y + 5, labelSize, labelSize);
        planetLabel.setForeground(Color.BLACK); // Set the text color

        return planetLabel;
    }

    public void updateCurrentStateLabel(String currentState){
        
        this.currentStateLabel.setText(currentState);
    }

    public void setNextButtonLabel(String str){
        this.nextButton.setText(str);
    }

    

    @Override
    public void updatePlanetColor(String planetName, Color color) {
        if (planetColors.containsKey(planetName)) {
            planetColors.put(planetName, color);
        }
        repaint(); // Repaint the panel to reflect the color change
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        for (int z = 0; z < 200; z++) { 
        int x = (int)(Math.random() * getWidth());
        int y = (int)(Math.random() * getHeight());
        g.fillOval(x, y, 2, 2);
    }
        g.setColor(Color.YELLOW);
        for (int i = 0; i < sunPositions.length; i++) {
            Point sunPosition = sunPositions[i];
            int size = sunSizes[i]; g.fillOval(sunPosition.x - size / 2, sunPosition.y - size / 2, size, size);
        }
        for (int i = 0; i < planetPositions.length; i++) {
            for (int j = 0; j < planetPositions[i].length; j++) {
                Point planetPos = planetPositions[i][j];
                int size = planetSizes[j];
                
                String planetName = planetNames[i * planetPositions[i].length + j];
                Color planetColor = planetColors.getOrDefault(planetName, Color.GRAY); // Use default color if not specified

                g.setColor(planetColor);
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
        g2d.setColor(Color.WHITE);
        g2d.drawLine(start.x, start.y, end.x, end.y);
    }

    @Override
    public void updatePlanetsSoldiers(String planetName, int newSoldiersCount) {
        JLabel label = planetLabels.get(planetName);
        if (label != null) {
            int currentValue = Integer.parseInt(label.getText());
            int newValue = currentValue + newSoldiersCount;
            
            label.setText(String.valueOf(newValue));
        }
        this.repaint();
    }



}

