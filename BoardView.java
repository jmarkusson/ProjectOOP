
import javax.swing.*;
import java.awt.*;
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

    public BoardView() {
        setToolTipText(""); // Enable tooltips

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
    public String getToolTipText(MouseEvent event) {
        return super.getToolTipText(event);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
    
        // Draw the suns
        g.setColor(Color.YELLOW);
        for (Point sunPosition : sunPositions) {
            g.fillOval(sunPosition.x - 35, sunPosition.y - 35, 100, 100); // Suns are now larger
        }
    
        // Draw the planets
        for (int i = 0; i < sunPositions.length; i++) {
            for (int j = 0; j < planetPositions[i].length; j++) {
                Point planetPos = planetPositions[i][j];
                // Draw planet
                g.setColor(Color.getHSBColor(j / (float) planetPositions[i].length, 0.7f, 0.9f));
                g.fillOval(planetPos.x - 15, planetPos.y - 15, 70, 70); // Planets are now larger
                // Draw name
                g.setColor(Color.WHITE);
                g.drawString(planetNames[i * planetPositions[i].length + j], planetPos.x, planetPos.y - 5);
            }
        }
    }

}

