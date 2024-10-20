import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Ellipse extends DrawingObject{
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public Ellipse(double x, double y, double width, double height, Color color) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    @Override
    public void draw(Graphics2D g2d) { 

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(color);
        Ellipse2D.Double circ = new Ellipse2D.Double(x, y, width, height);
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
