import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Pipe extends DrawingObject {
    private double x, y, width, height;
    private Color color;
    private boolean passed; // has the fish passed the pipe? (used for the score)

    public Pipe(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        passed = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(x, y, width, height));
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
        if (x + width <= 0) {
            return true;
        }
        else 
            return false;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}

