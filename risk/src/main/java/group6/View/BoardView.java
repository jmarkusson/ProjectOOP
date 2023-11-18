package group6.View;




import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class BoardView extends JPanel {

    private final Point[] sunPositions = {
        new Point(150, 150), // Top-left sun moved closer to center
        new Point(550, 150), // Top-right sun moved closer to center
        new Point(150, 400), // Bottom-left sun moved closer to center
        new Point(550, 400)  // Bottom-right sun moved closer to center
    };
    
    private final Point[][] planetPositions = {
        // Planets around top-left sun
        {new Point(150, 50), new Point(310, 110), new Point(200, 230), new Point(60, 110)}, 
        // Planets around top-right sun
        {new Point(610, 50), new Point(670, 110), new Point(610, 230), new Point(450, 110)}, 
        // Planets around bottom-left sun
        {new Point(280, 320), new Point(310, 440), new Point(250, 500), new Point(70, 440)}, 
        // Planets around bottom-right sun
        {new Point(450, 320), new Point(670, 350), new Point(610, 500), new Point(430, 440)}  
    };
    
    
    private final String[] planetNames = {
        "Coruscant", "Tatooine", "Hoth", "Dagobah",
        "Alderaan", "Bespin", "Endor", "Naboo",
        "Kashyyyk", "Yavin IV", "Jakku", "Mustafar",
        "Sullust", "Fondor", "Ithor", "Anoat"
    };

    private int[] sunSizes = {80, 100, 120, 140}; // Four different sizes for suns
    private int[] planetSizes = {50, 60, 70, 80}; // Four different sizes for planets

    public BoardView() {
        setPreferredSize(new Dimension(200,200));
    }

    public void addController(ActionListener e){
        
       
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < planetPositions.length; i++) {
                    for (int j = 0; j < planetPositions[i].length; j++) {
                        Point p = planetPositions[i][j];
                        Ellipse2D.Float planet = new Ellipse2D.Float(p.x, p.y, 30, 30);
                        if (planet.contains(e.getPoint())) {
                            int planetIndex = i * planetPositions[i].length + j;
                            JOptionPane.showMessageDialog(null, "Planet " + planetNames[planetIndex] + " clicked!");
                        }
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (int i = 0; i < planetPositions.length; i++) {
                    for (int j = 0; j < planetPositions[i].length; j++) {
                        Point p = planetPositions[i][j];
                        Ellipse2D.Float planet = new Ellipse2D.Float(p.x, p.y, 30, 30);
                        if (planet.contains(e.getPoint())) {
                            int planetIndex = i * planetPositions[i].length + j;
                            BoardView.this.setToolTipText(planetNames[planetIndex]);
                            return;
                        }
                    }
                }
                BoardView.this.setToolTipText(null);
            }
        });
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
                g.drawString(planetNames[i * planetPositions[i].length + j], planetPos.x, planetPos.y +20);
            }
        }
    }

}

