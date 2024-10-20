import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class SeaBed extends DrawingObject {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public SeaBed(double x, double y, double width, double height, Color color) {
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

        Rectangle seabed = new Rectangle(x, y, width, height, color);
        seabed.draw(g2d);
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


}
