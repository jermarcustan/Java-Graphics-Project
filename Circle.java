import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Circle extends DrawingObject{
    private double x;
    private double y;
    private double size;
    private Color color;

    public Circle(double x, double y, double size, Color color) {

        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;

    }

    @Override
    public void draw(Graphics2D g2d) { 

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(color);
        Ellipse2D.Double circ = new Ellipse2D.Double(x, y, size, size);
        g2d.fill(circ);        

    }
    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    @Override
    public double getX() {
        return x;
    }
    
}
