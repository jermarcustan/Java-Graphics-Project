import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Fish extends DrawingObject {
    private double x, y, size;
    private double velocity = 0;
    private final double gravity = 2.0;

    public Fish(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void draw(Graphics2D g2d) {

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.setColor(Color.CYAN);
        Ellipse2D.Double body = new Ellipse2D.Double(x, y, size, size/2);
        g2d.fill(body);
        
        // Create a Path2D.Double object for floating-point precision
        Path2D.Double tail = new Path2D.Double();
        tail.moveTo(x, y + size/4);  // Starting point of the polygon
        tail.lineTo(x-size/2, y);   // Second point
        tail.curveTo(x-size/4, y + (1*size)/8,x-size/4, y + (3*size)/8,x-size/2, y + (2*size)/4);  // Curve
        tail.closePath();           // Closes the polygon

        // Set the color and fill the polygon (fish tail)
        g2d.setColor(Color.BLUE);
        g2d.fill(tail);


        // Draw the fish eye (small Ellipse2D)
        g2d.setColor(Color.WHITE);
        Ellipse2D.Double eye = new Ellipse2D.Double(x+3*size/4, y + size/8, size/10, size/10);
        g2d.fill(eye);
        g2d.setColor(Color.BLACK);
        Ellipse2D.Double pupil = new Ellipse2D.Double(x+25*size/32, y + 5*size/32, size/20, size/20);
        g2d.fill(pupil);

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

    public double getHeight() {
        return size/2;
    }

    public void fall() {
        y += velocity;
        velocity += gravity;
    }

    public void flap() {
        velocity = -15; // Makes the fish jump upwards
    }

    public Rectangle2D getBounds() {
         // Get and print the bounds of the ellipse
         Rectangle2D.Double bodyBounds = new Rectangle2D.Double(x, y, size, size/2);
         
         Path2D.Double tail = new Path2D.Double();
        tail.moveTo(x, y + size/4);  // Starting point of the polygon
        tail.lineTo(x-size/2, y);   // Second point
        tail.curveTo(x-size/4, y + (1*size)/8,x-size/4, y + (3*size)/8,x-size/2, y + (2*size)/4);  // Curve
        tail.closePath();           // Closes the polygon
         Rectangle2D tailBounds = tail.getBounds2D();
        
         return bodyBounds.createUnion(tailBounds);


    }
}
