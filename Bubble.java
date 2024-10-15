import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Bubble extends DrawingObject {
    private double x, y, size, speed;

    public Bubble(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = new Random().nextDouble() * 2 + 1; // Random speed between 1 and 3
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(173, 216, 230, 150)); // Light blue with transparency
        g2d.fill(new Ellipse2D.Double(x, y, size, size));
    }

    @Override
    public void adjustX(double distance) {
        // No horizontal movement needed for bubbles
    }

    @Override
    public double getX() {
        return x;
    }

    public void rise() {
        y -= speed; // Move bubbles upwards
        if (y + size < 0) {
            y = 600; // Reset bubble to bottom when it reaches the top
        }
    }
}
