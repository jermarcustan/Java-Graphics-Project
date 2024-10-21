/**
* This bubble class is a subclass of the DrawingObject abstract class that is used to draw
* and adjust the X position of the small bubbles floating on the background of the game.  
* The bubbles are drawn using the created Ellipse class. 
 *
 * Author: Ang, Tan
 * ID Number: 200302, 204947
 * Date Created: October 15, 2024
 *
 * Certification of Authorship:
 * I certify that I have authored this code on my own. Any assistance received in writing this code
 * has been acknowledged, and the code has been created entirely by myself.
*/

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

     /**
     * The constructor accepts four input parameters: x, y, width, and height of the bubble. 
     * The speed of the bubble is randomly generated using the java.util.Random package so that
     * the bubbles in the program rise at varying speeds.
     * @param x - the x position of the object in pixels
     * @param y - the y position of the object in pixels
     * @param width - the width of the object in pixels
     * @param height - the height of the object in pixels
     */

     
    public Bubble(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = new Random().nextDouble() * 2 + 1; // Random speed between 1 and 3
    }
    /**
     * This method draws the bubbles using the Graphics2D class. 
     * The draw method of the self-created ellipse class is used to draw the bubble.
     * Anti-Aliasing is turned on to smoothen lines.
     * @param g2d - Graphics2D class for rendering the objects
     */
    @Override
    public void draw(Graphics2D g2d) {

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Ellipse bubble = new Ellipse(x,y,width,height, new Color(173, 216, 230, 150));
        bubble.draw(g2d);
    }
    /**
     * Horizontal movement is not needed for the bubbles.
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {

    }
    /**
     * Standard method to return the x position of the bubble
     * @return position in the x-axis
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * This method is called on every frame of the program and 
     * calculates how much the y position of the bubble decreases as the bubbles rises.
     * Once the bubble reaches the top of the JFrame, reset its position at the bottom.
     */
    public void rise() {
        y -= speed; 
        if (y + height < 0) {
            y = 600; 
        }
    }
}
