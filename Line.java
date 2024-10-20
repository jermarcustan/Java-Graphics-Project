import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Line extends DrawingObject{
    private double start_x;
    private double start_y;
    private double end_x;
    private double end_y;
    private double thickness;
    private Color color;

    public Line(double start_x, double start_y, double end_x, double end_y, double thickness, Color color) {

        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;
        this.thickness = thickness;
        this.color = color;

    }

    @Override
    public void draw(Graphics2D g2d) { 

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        g2d.setColor(color);

        Stroke originalStroke = g2d.getStroke();

        g2d.setStroke(new BasicStroke((float)thickness));
        Line2D.Double line = new Line2D.Double(start_x, start_y, end_x, end_y);
        g2d.draw(line);        

        g2d.setStroke(originalStroke);

    }
    @Override
    public void adjustX(double distance) {
        start_x += distance;
        end_x += distance;
    }

    @Override
    public double getX() {
        return start_x;
    }
    
}
