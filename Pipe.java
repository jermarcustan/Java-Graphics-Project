import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Pipe extends DrawingObject {
    private double x, y, width, height;
    private boolean passed;

    public Pipe(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.passed = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Create a gradient to make the pipe look more like an ocean obstacle
        GradientPaint oceanPipe = new GradientPaint((float) x, (float) y, new Color(32, 178, 170), (float) x, (float) (y + height), new Color(0, 128, 128));
        g2d.setPaint(oceanPipe);
        g2d.fill(new Rectangle2D.Double(x, y, width, height));

        // Add a simple outline for the pipe for visibility
        g2d.setColor(Color.BLACK);
        g2d.draw(new Rectangle2D.Double(x, y, width, height));
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
