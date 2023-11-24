package group6.View;




import javax.swing.*;
import java.awt.*;



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

    public void addController(MouseAdapter e){
        
       
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
                g.drawString(planetNames[i * planetPositions[i].length + j], planetPos.x-20, planetPos.y );
            }
        }
    }

}

