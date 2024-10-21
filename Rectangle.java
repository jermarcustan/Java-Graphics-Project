/**
 * This class creates a Rectangle basic shape class using a Rectangle2D.Double class.
 * It has an instance field for the color of the rectangle.
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

public class Rectangle extends DrawingObject{
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    /**
     * The class constructor has all the fields of the rectangle2d.double class with a Color field
     * @param x - x coordinate of the upper left corner of the rectangle
     * @param y - y coordinate of the upper left corner of the rectangle
     * @param width - width of the rectangle
     * @param height - height of the rectangle
     * @param color - color of the rectangle
     */
    public Rectangle(double x, double y, double width, double height, Color color) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    /** 
     * The draw method uses the Graphics2D class to draw the rectangle. 
     * Antialiasing was enabled to smoothen edges.
     * The color of Graphics2D was set using the setColor method.
     * @param g2d - Graphics2D class used to render the shapes
     */  
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

    /**
     * This mutator method is used to change the x coordinate of the rectangle.
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    /**
     * This accessor method is used to return the x coordinate of the upper left corner of the rectangle.
     * @return position along the x-axis
     */
    @Override
    public double getX() {
        return x;
    }


    /**
     * This class is used to draw the rectangle with a gradient color.
     * @param g2d - Graphics2D class for rendering the objects
     * @param gp - GradientPaint class for the gradient
     */
    public void drawGradient(Graphics2D g2d, GradientPaint gp) { 

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        g2d.setPaint(gp);
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        g2d.fill(rect);        
    }

    /**
     * This class is used to draw an outline around the rectangle.
     * @param g2d - Graphics2D class for rendering the objects
     */
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
