import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Pipe extends DrawingObject {
    private double x, y, width, height;
    private boolean passed;
    private Random random;

    public Pipe(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.passed = false;
    }

    @Override
    public void draw(Graphics2D g2d) {

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        // Create a gradient to make the pipe look more like an ocean obstacle
        GradientPaint oceanPipe = new GradientPaint((float) x, (float) y, new Color(32, 178, 170), (float) x, (float) (y + height), new Color(0, 128, 128));

        Rectangle pipe = new Rectangle(x, y, width, height, Color.BLACK);
        pipe.drawGradient(g2d, oceanPipe);

        
        // Add a simple outline for the pipe for visibility
        pipe.drawOutline(g2d);
    }

    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    @Override
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public boolean getPass() {
        return passed;
    }

    public void setPass() {
        passed = true;
    }

    public void moveLeft() {
        x -= 15; // Adjust speed as needed
    }

    public boolean OverBounds() {
        return (x + width <= 0);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }

}
