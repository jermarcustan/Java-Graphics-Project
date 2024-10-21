/**
 * The SeaBed class describes and draws the seabed at the bottom of the canvas.
 * It is drawn using the basic shape Rectangle class. 
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
import java.util.Random;


public class SeaBed extends DrawingObject {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;
    /**
     * Constructor for the SeaBed.
     * @param x - x coordinate of the upper left corner of the seabed
     * @param y - y coordinate of the upper left corner of the seabed
     * @param width - width of the seabed
     * @param height - height of the seabed
     * @param color - color of the seabed
     */
    public SeaBed(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }


     /**
      * The draw method is used to draw the seabed using a Rectangle.
      * Antialiasing is used to smoothen out the edges.
      * @param g2d - Graphics2D class used to render the shapes
      */
    @Override
    public void draw(Graphics2D g2d) {

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle seabed = new Rectangle(x, y, width, height, color);
        seabed.draw(g2d);
        
    }

    /**
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    /**
     * This accessor method is used to return the x coordinate of the seabed. 
     * @return position along the x-axis
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * This accessor method is used to return the y position of the seabed.
     * @return position along the y-axis
     */
    public double getY() {
        return y;
    }

    /**
     * This accessor method is used to return the width of the seabed. 
     * @return width of the seabed
     */
    public double getWidth() {
        return width;
    }


}
