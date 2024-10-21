/**
 * The pipe class serves as the obstacles in the game which the user must go in between.
 * The pipes come in pairs, the upper pipe and lower pipe, both of which are instantiated using this class.  
 *
 * Author: Ang, Tan
 * ID Number: 200302, 204947
 * Date Created: October 10, 2024
 * 
 * Certification of Authorship:
 * I certify that I have authored this code on my own. Any assistance received in writing this code
 * has been acknowledged, and the code has been created entirely by myself.
 
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Pipe extends DrawingObject {
    private double x, y, width, height;
    private boolean passed;

    /**
     * The class constructor has fields for the location and size of the pipe 
     * as well as a boolean (passed) which is false as long as the fish has not passed through the pipe.
     * @param x  - x position of the upper left corner of the pipe
     * @param y - y position of the upper left corner of the pipe
     * @param width - width of the pipe in pixels
     * @param height - height of the pipe in pixels
     * the passed field is a boolean which becomes true once the fish has passed the pipe
     */
    public Pipe(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.passed = false;
    }

    /**
     * The draw method uses the Graphics2D class to draw the pipe on the JFrame. 
     * Antialiasing is turned on to make the lines smoother and to reduce artifacts.
     * A gradient paint is used for the color of the pipe.
     * Lastly, the self-created Rectangle class is used to draw the pipe.
     * The drawGradient method of the Rectangle is used to color the pipe.
     * The drawOutline method of the Rectangle is used to outline the pipe.
     * @param g2d - Graphics2D class for rendering the objects
     */
    @Override
    public void draw(Graphics2D g2d) {

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        // Create a gradient to make the pipe look more like an ocean obstacle
        GradientPaint oceanPipe = new GradientPaint((float) x, (float) y, new Color(32, 178, 170), (float) x, (float) (y + height), new Color(0, 128, 128));

        Rectangle pipe = new Rectangle(x, y, width, height, Color.BLACK);
        pipe.drawGradient(g2d, oceanPipe);

        // Add a simple outline for the pipe for visibility
        pipe.drawOutline(g2d);
    }

    /**
     * This mutator method adjusts the x position of the pipe according to the distance.
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    /**
     * This accessor method returns the x position of the upper left corner of the pipe.
     * @return position along the x-axis
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * This accessor method returns the y position of the upper left corner of the pipe.
     * @return position along the y-axis
     */
    public double getY() {
        return y;
    }

    /**
     * This accessor method returns the width of the pipe.
     * @return width of the pipe
     */
    public double getWidth() {
        return width;
    }

    /**
     * This accessor method returns the value of passed.
     * @return boolean for when the fish has passed the pipe
     */
    public boolean getPass() {
        return passed;
    }

    /**
     * This mutator method sets the value of passed as true when the fish has passed the pipe.
     */
    public void setPass() {
        passed = true;
    }

    /**
     * In every frame, this method is run which moves all the pipes on the screen by 15 pixels to the left.
     */
    public void moveLeft() {
        x -= 15; // Adjust speed as needed
    }

    /**
     * It returns false once the pipe is no longer visible. 
     * @return a boolean on whether the pipe is still visible in the game
     */
    public boolean OverBounds() {
        return (x + width <= 0);
    }

    /**
     * This method returns a rectangle2D class which is used as the hitbox for the pipe.
     * @return the hitbox of the pipe
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }

}
