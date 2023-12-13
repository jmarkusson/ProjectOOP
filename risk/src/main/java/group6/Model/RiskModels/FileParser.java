package group6.Model.RiskModels;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private List<String> planetsArray = new ArrayList<String>();
    private List<String> solarsystemsArray = new ArrayList<String>();
    private List<String> adjacenciesArray = new ArrayList<String>();
    private Point[][] pointsArray;
    private Point[] solarPointsArray;

    public FileParser() {
        //nothing
    }

    public void parseFile() {
        planetsArray = readFile("textfiles/planets.txt");
        solarsystemsArray = readFile("textfiles/SolarSystems.txt");
        adjacenciesArray = readFile("textfiles/AdjacentPlanets.txt");
        solarPointsArray = parsePoints(readFile("textfiles/solarPoints.txt"));
        pointsArray = parsePointsArrays(readFile("textfiles/Points.txt"));
    }
// 
    private List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file '" + filePath + "': " + e.getMessage());
        }
        return lines;
    }

    private Point[] parsePoints(List<String> lines) {
        List<Point> points = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0].trim());
            int y = Integer.parseInt(parts[1].trim());
            points.add(new Point(x, y));
        }
        return points.toArray(new Point[0]);
    }

    private Point[][] parsePointsArrays(List<String> lines) {
        List<Point[]> listOfPointArrays = new ArrayList<>();
        for (String line : lines) {
            String[] pointStrings = line.split("\\s+");
            Point[] points = new Point[pointStrings.length];
            for (int i = 0; i < pointStrings.length; i++) {
                points[i] = parsePoint(pointStrings[i]);
            }
            listOfPointArrays.add(points);
        }
        return listOfPointArrays.toArray(new Point[0][]);
    }

    private Point parsePoint(String str) {
        String[] parts = str.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new Point(x, y);
    }

    // Getters
    public String[] getPlanetNamesArray() {
        return convertToString(this.planetsArray);
    }

    public String[] getSolarsystemsArray() {
        return convertToString(this.solarsystemsArray);
    }

    public String[] getAdjacenciesArray() {
        return convertToString(this.adjacenciesArray);
    }

    public Point[][] getPlanetPointsArray() {
        return this.pointsArray;
    }

    public Point[] getSolarPointsArray() {
        return this.solarPointsArray;
    }
    private String[] convertToString(List<String> list){
        return list.toArray(new String[0]);
    }
}
