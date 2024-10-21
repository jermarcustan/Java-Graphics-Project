/**
 * The SeaweedCluster class draws the seaweeds at the bottom of the JFrame. 
 * It is made up of composite shapes of the self-created Line, Circle, and Square classes.
 * 
 * Author: Ang, Tan
 * ID Number: 200302, 204947
 * Date Created: October 20, 2024
 *
 * Certification of Authorship:
 * I certify that I have authored this code on my own. Any assistance received in writing this code
 * has been acknowledged, and the code has been created entirely by myself.
 */
import java.awt.*;
import java.util.ArrayList;

public class SeaweedCluster extends DrawingObject {
    private ArrayList<Line> seaweedStems;
    private ArrayList<Circle> seaweedBlobs;  
    private Square seaweedBase;

    /**
     * The constructor of the SeaweedCluster class.
     * @param x - x coordinate of the upper left corner of the seaweed cluster
     * @param y - y coordinate of the upper left corner of the seaweed cluster
     * @param height - height of the seaweed cluster
     * @param width - width of the seaweed cluster
     */
    public SeaweedCluster(double x, double y, double height, double width) {
        seaweedStems = new ArrayList<>();
        seaweedBlobs = new ArrayList<>();

        // Create seaweed stems with different lean angles
        seaweedStems.add(new Line(x, y, x - width / 4, y - height * 1.1, 2, new Color(34, 139, 34))); // Left-leaning
        seaweedStems.add(new Line(x, y, x + width / 4, y - height * 1.2, 2, new Color(60, 179, 113))); // Right-leaning
        seaweedStems.add(new Line(x, y, x, y - height * 0.9, 2, new Color(0, 128, 0))); // Center

        // Add circles at the top of each stem to that look like seaweed leaves
        seaweedBlobs.add(new Circle(x - width / 4, y - height * 1.1, 10, new Color(107, 142, 35))); // Left-leaning blob
        seaweedBlobs.add(new Circle(x + width / 4, y - height * 1.2, 12, new Color(85, 107, 47)));  // Right-leaning blob
        seaweedBlobs.add(new Circle(x, y - height * 0.9, 8, new Color(143, 188, 143)));  // Center blob

        // Add a square at the bottom of the stems

        seaweedBase = new Square(x - 5.0, y, 10, new Color(0, 100, 0));

    }

    /**
     * The draw method uses for loops and iterates over seaweedStems and seaweedBlobs arrays. 
     * It also draws the Square base.
     * @param g2d - Graphics2D class used to render the shapes
     */
    @Override
    public void draw(Graphics2D g2d) {

        for (Line stem : seaweedStems) {
            stem.draw(g2d);
        }

        // Draw the circles on top of the stems
        for (Circle blob : seaweedBlobs) {
            blob.draw(g2d);
        }

        seaweedBase.draw(g2d);
    }

    /**
     * The adjustX method calls the adjustX method on the Line and Circle classes.     
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        for (Line stem : seaweedStems) {
            stem.adjustX(distance);
        }
        for (Circle blob : seaweedBlobs) {
            blob.adjustX(distance);
        }
    }

    /**
     * The getX method calls the getX method of the upper left seaweedStem
     * @return position of the seaweed along the x-axis
     */
    @Override
    public double getX() {
        return seaweedStems.get(0).getX();
    }
}
