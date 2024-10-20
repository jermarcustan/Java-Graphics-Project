import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Rectangle extends DrawingObject{
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public Rectangle(double x, double y, double width, double height, Color color) {

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
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        g2d.fill(rect);        

    }
    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    @Override
    public double getX() {
        return x;
    }

    public void drawGradient(Graphics2D g2d, GradientPaint gp) { 

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        g2d.setPaint(gp);
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        g2d.fill(rect);        

    }
    public void drawOutline(Graphics2D g2d) { 

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        g2d.setColor(Color.BLACK);
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        g2d.draw(rect);        

    }
    
}
