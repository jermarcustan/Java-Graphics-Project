import java.util.Random;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Bubble extends DrawingObject {
    private double x;
    private double y;
    private double width;
    private double height;
    private double speed;

    public Bubble(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = new Random().nextDouble() * 2 + 1; // Random speed between 1 and 3
    }

    @Override
    public void draw(Graphics2D g2d) {

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Ellipse bubble = new Ellipse(x,y,width,height, new Color(173, 216, 230, 150));
        bubble.draw(g2d);
    }

    @Override
    public void adjustX(double distance) {
        // No horizontal movement needed for bubbles
    }

    @Override
    public double getX() {
        return x;
    }

    public void rise() {
        y -= speed; // Move bubbles upwards
        if (y + height < 0) {
            y = 600; // Reset bubble to bottom when it reaches the top
        }
    }
}
