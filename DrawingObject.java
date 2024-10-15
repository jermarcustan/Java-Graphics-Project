import java.awt.Graphics2D;

public abstract class DrawingObject {
    public abstract void draw(Graphics2D g2d);
    public abstract void adjustX(double distance);
    public abstract double getX();
}
