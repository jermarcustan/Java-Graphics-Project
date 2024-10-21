/**
 * The DrawingObject is an abstract class which contains three basic methods
 * used by all of the basic and composite shapes.
 * This class serves as the superclass for all the other shapes.
 *
 * Author: Ang, Tan
 * ID Number: 200302, 204947
 * Date Created: October 10, 2024
 *
 * Certification of Authorship:
 * I certify that I have authored this code on my own. Any assistance received in writing this code
 * has been acknowledged, and the code has been created entirely by myself.
 */
import java.awt.Graphics2D;

public abstract class DrawingObject {
    /**
     * @param g2d - Graphics2D class for rendering the objects
     */ 
     
    public abstract void draw(Graphics2D g2d);

    /**
     * @param distance - number of pixels which the object will move along the x-axis
     */
    public abstract void adjustX(double distance);
    public abstract double getX();
}
