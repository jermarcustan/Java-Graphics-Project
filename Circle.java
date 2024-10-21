/**
 * This circle class draws a circle using the Ellipse2D.Double class 
 * with an added instance field for the color.
 * The circle is one of the basic shapes in Java.
 *  
 * Author: Ang, Tan
 * ID Number: 200302, 204947
 * Date Created: October 20, 2024
 *
 * Certification of Authorship:
 * I certify that I have authored this code on my own. Any assistance received in writing this code
 * has been acknowledged, and the code has been created entirely by myself.
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Circle extends DrawingObject{
    private double x;
    private double y;
    private double size;
    private Color color;


    /**
     * The circle class constructor accepts the standard instance fields 
     * for the Ellipse2D.Double class, where the width and height are equal 
     * with an additional field for the color of the circle.
     * @param x - the x position of the object in pixels
     * @param y - the y position of the object in pixels
     * @param size - the width of the object in pixels
     * @param color - the color of the circle
     * 
     */
    public Circle(double x, double y, double size, Color color) {

        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;

    }
    /**
     * This method draws the circle using the Graphics2D class. 
     * The Ellipse2D.Double class is used where the width and height parameters are made equal.
     * Anti-Aliasing is turned on to smoothen lines.
     * @param g2d - Graphics2D class for rendering the objects
     */
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
    /**
     * The x position of the circle is changed by adding the specified distance.
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        x += distance;
    }
    /**
     * Accessor method for the x position
     * @return position in the x-axis
     */
    @Override
    public double getX() {
        return x;
    }
    
}
