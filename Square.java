/**
 * The Square class creates a basic shape class for a square.
 * It uses the Rectangle2D.Double class.
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


public class Square extends DrawingObject{
    private double x;
    private double y;
    private double size;
    private Color color;

    /**
     * The constructor of the square class has the following fields:
     * @param x - x coordinate of the upper left corner
     * @param y - y coordinate of the upper left corner
     * @param size - size of the square
     * @param color - color of the square
     */

    public Square(double x, double y, double size, Color color) {

        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;

    }

    /**
     * The draw method uses a Rectangle2D.Double class to draw the square.
     * Both the width and height parameters of the Rectangle2D.Double class are equal.
     * Antialiasing is enabled to smoothen edges.
     * @param g2d - Graphics2D class used to render the shapes
     */

    @Override
    public void draw(Graphics2D g2d) { 

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        g2d.setColor(color);
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, size, size);
        g2d.fill(rect);        

    }
    /**
     * The adjustX method moves the square with respect to the x-axis
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    /**
     * The getX method returns the x-coordinate of the upper left corner of the square.
     * @return position of the seaweed along the x-axis
     */
    @Override
    public double getX() {
        return x;
    }
    
}
