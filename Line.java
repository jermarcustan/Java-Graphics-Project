/**
 * This line class draws a line using the Line2D.Double class 
 * with added instance fields for the color and thickness of the line.
 * The line is one of the basic shapes in Java.
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

public class Line extends DrawingObject{
    private double start_x;
    private double start_y;
    private double end_x;
    private double end_y;
    private double thickness;
    private Color color;

    /**
     * All of the parameters are in pixels.
     * @param start_x - x position of the starting point of the line
     * @param start_y - y position of the starting point of the line
     * @param end_x - x position of the ending point of the line
     * @param end_y - y position of the ending point of the line
     * @param thickness - thickness of the line
     * @param color - color of the line
     */
    public Line(double start_x, double start_y, double end_x, double end_y, double thickness, Color color) {

        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;
        this.thickness = thickness;
        this.color = color;

    }

    /**
     * This method draws the line using the Graphics2D class.
     * The Line2D.Double class is used with its standard parameters.
     * The color of the Graphics2D class is changed using the setColor method.
     * The stroke of the Graphics2D class is changed using the setStroke method.
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

        Stroke originalStroke = g2d.getStroke();

        g2d.setStroke(new BasicStroke((float)thickness));
        Line2D.Double line = new Line2D.Double(start_x, start_y, end_x, end_y);
        g2d.draw(line);        

        g2d.setStroke(originalStroke);

    }
    /**
     * Mutator method which changes the x position of both the starting point and ending point.
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        start_x += distance;
        end_x += distance;
    }

    /**
     * Accessor method which returns the x position of the starting point
     * @return position along the x-axis
     */
    @Override
    public double getX() {
        return start_x;
    }
    
}
