import java.awt.*;
import java.util.ArrayList;

public class SeaweedCluster extends DrawingObject {
    private ArrayList<Line> seaweedStems;
    private ArrayList<Circle> seaweedBlobs;  // Circles at the top of the stems

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
    }

    @Override
    public void draw(Graphics2D g2d) {

        for (Line stem : seaweedStems) {
            stem.draw(g2d);
        }

        // Draw the circles on top of the stems
        for (Circle blob : seaweedBlobs) {
            blob.draw(g2d);
        }
    }

    @Override
    public void adjustX(double distance) {
        for (Line stem : seaweedStems) {
            stem.adjustX(distance);
        }
        for (Circle blob : seaweedBlobs) {
            blob.adjustX(distance);
        }
    }

    @Override
    public double getX() {
        return seaweedStems.get(0).getX();
    }
}
