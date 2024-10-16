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
        g2d.setColor(Color.black);
        // Ellipse2D.Double body = new Ellipse2D.Double(x, y, size, size/2);
        // g2d.fill(body);


        g2d.setColor(Color.BLACK);
        Path2D.Double tail = new Path2D.Double();
        tail.moveTo(x + (3.0/16)*size, y + (15.0/128)*size);
        tail.curveTo(x, y - (3.0/8)*size, x - (3.0/8)*size, y + (1.0/8)*size, x, y + (2.0/16)*size);
        tail.curveTo(x - (3.0/8)*size, y + (1.0/16)*size, x - (3.0/8)*size, y + (7.0/16)*size, x, y + (6.0/16)*size);
        tail.curveTo(x - (3.0/8)*size, y + (3.0/8)*size, x, y + (7.0/8)*size, x + (3.0/16)*size, y + (49.0/128)*size);
        tail.closePath();
        g2d.draw(tail);

        g2d.setColor(new Color(225, 91, 18));
        g2d.fill(tail);
        g2d.setColor(Color.BLACK);
        Path2D.Double upper_fin = new Path2D.Double();
        upper_fin.moveTo(x + (11.0/16)*size, y - (1.0/32)*size);
        upper_fin.curveTo(x + (17.0/32)*size, y - (3.0/8)*size, x - (5.0/32)*size, y - (1.0/8)*size, x + (10.0/32)*size, y + (1.0/32)*size);
        upper_fin.closePath();
        g2d.draw(upper_fin);
        g2d.setColor(new Color(225, 91, 18));
        g2d.fill(upper_fin);


        g2d.setColor(Color.BLACK);
        Path2D.Double lower_fin = new Path2D.Double();
        lower_fin.moveTo(x + (9.0/16)*size, y + (17.0/32)*size);
        lower_fin.curveTo(x + (14.0/32)*size, y + (3.0/4)*size, x + (5.0/32)*size, y + (20.0/32)*size, x + (10.0/32)*size, y + (15.0/32)*size);
        lower_fin.closePath();
        g2d.draw(lower_fin);
        g2d.setColor(new Color(225, 91, 18));
        g2d.fill(lower_fin);

        g2d.setColor(Color.BLACK);
        Path2D.Double body = new Path2D.Double();
        body.moveTo(x + (1.0/8)*size, y + ((1.0/4) * size));
        body.curveTo(x + (2.0/8)*size, y-size/8, x + (7.0/8)*size, y - size/8, x + size, y + ((3.0/16) * size));
        body.curveTo(x + size*(34.0/32), y + ((5.0/32) * size), x + size*(37.0/32), y + ((6.0/32) * size), x + size*(34.0/32), y + ((1.0/4) * size));
        body.curveTo(x + size*(35.0/32), y + ((8.0/32) * size), x + size*(37.0/32), y + ((12.0/32) * size), x + size, y + ((5.0/16) * size));
        body.curveTo(x + (7.0/8)*size, y + (10.0/16)*size, x + (2.0/8)*size, y + (10.0/16)*size, x+ (1.0/8)*size, y + ((1.0/4) * size));
        g2d.draw(body);
        
        g2d.setColor(new Color(250,149,31));
        g2d.fill(body);

        g2d.setColor(Color.BLACK);
        Ellipse2D.Double eye = new Ellipse2D.Double(x+3*size/4, y + size/8, size/10, size/10);
        g2d.fill(eye);
        g2d.setColor(Color.WHITE);
        Ellipse2D.Double pupil = new Ellipse2D.Double(x+25*size/32, y + 9*size/64, size/20, size/20);
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
         return bodyBounds;


    }
}
