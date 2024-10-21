/**
 * The fish class is a composite shape which describes 
 * how to draw the fish that the user controls in the game.
 * The fish class has additional methods to simulate actions 
 * like going down because of gravity and going up due to user input.
 * Fish is a composite shape which makes use of a combination of basic shapes.
 * 
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

/**
 * The Fish class has a few additional instance fields.
 * velocity - The rate of change of the y position of the fish
 * gravity - Added to the velocity of the fish
 * fallStartTime - The program tracks the time when the fish intersects with one of the pipes.
 * isFalling - A boolean which becomes true when the fish intersects with one of the pipes and begins falling down
 * FALL_DURATION - After the fish intersects with one the pipes, it falls off the screen over one second.
 */
public class Fish extends DrawingObject {
    private double x;
    private double y;
    private double size;
    private double velocity = 0;
    private final double gravity = 2.0;
    private long fallStartTime;
    private boolean isFalling = false;
    private long FALL_DURATION = 1000;

    /**
     * In the class constructor, only the x position, y position, and size of the fish are parameters.
     * @param x - x coordinate (pixels)
     * @param y - y coordinate (pixels)
     * @param size - size in pixels of the fish
     */
    public Fish(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * The Graphics2D class is used to draw the different parts of the fish.
     * Parts of the fish:
     * 1. tail - a Path2D.Double class is used 
     * 2. upper_fin - a Path2D.Double class is used
     * 3. lower_fin - a Path2D.Double class is used
     * 4. body - a Path2D.Double class is used
     * 5. eye, pupil - the basic Circle class is used
     * 6. tail_lines - the basic Line class is used
     * Anti-Aliasing is turned on to smoothen lines.
     * A black outline was drawn around the object using g2d.draw
     * @param g2d - Graphics2D class for rendering the objects
     */
    @Override
    public void draw(Graphics2D g2d) {

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.BLACK);
        Path2D.Double tail = new Path2D.Double();
        tail.moveTo(x + (3.0/16)*size, y + (15.0/128)*size);
        tail.curveTo(x, y - (3.0/8)*size, x - (3.0/8)*size, y + (1.0/8)*size, x, y + (2.0/16)*size);
        tail.curveTo(x - (3.0/8)*size, y + (1.0/16)*size, x - (3.0/8)*size, y + (7.0/16)*size, x, y + (6.0/16)*size);
        tail.curveTo(x - (3.0/8)*size, y + (3.0/8)*size, x, y + (7.0/8)*size, x + (3.0/16)*size, y + (49.0/128)*size);
        tail.closePath();
        g2d.draw(tail);


        g2d.setColor(new Color(225, 91, 18));
        g2d.fill(tail);
        g2d.setColor(Color.BLACK);

        Path2D.Double upper_fin = new Path2D.Double();
        upper_fin.moveTo(x + (11.0/16)*size, y - (1.0/32)*size);
        upper_fin.curveTo(x + (17.0/32)*size, y - (3.0/8)*size, x - (5.0/32)*size, y - (1.0/8)*size, x + (10.0/32)*size, y + (1.0/32)*size);
        upper_fin.closePath();
        g2d.draw(upper_fin);
        g2d.setColor(new Color(225, 91, 18));
        g2d.fill(upper_fin);


        g2d.setColor(Color.BLACK);
        Path2D.Double lower_fin = new Path2D.Double();
        lower_fin.moveTo(x + (9.0/16)*size, y + (17.0/32)*size);
        lower_fin.curveTo(x + (14.0/32)*size, y + (3.0/4)*size, x + (5.0/32)*size, y + (20.0/32)*size, x + (10.0/32)*size, y + (15.0/32)*size);
        lower_fin.closePath();
        g2d.draw(lower_fin);
        g2d.setColor(new Color(225, 91, 18));
        g2d.fill(lower_fin);

        g2d.setColor(Color.BLACK);
        Path2D.Double body = new Path2D.Double();
        body.moveTo(x + (1.0/8)*size, y + ((1.0/4) * size));
        body.curveTo(x + (2.0/8)*size, y-size/8, x + (7.0/8)*size, y - size/8, x + size, y + ((3.0/16) * size));
        body.curveTo(x + size*(34.0/32), y + ((5.0/32) * size), x + size*(37.0/32), y + ((6.0/32) * size), x + size*(34.0/32), y + ((1.0/4) * size));
        body.curveTo(x + size*(35.0/32), y + ((8.0/32) * size), x + size*(37.0/32), y + ((12.0/32) * size), x + size, y + ((5.0/16) * size));
        body.curveTo(x + (7.0/8)*size, y + (10.0/16)*size, x + (2.0/8)*size, y + (10.0/16)*size, x+ (1.0/8)*size, y + ((1.0/4) * size));
        g2d.draw(body);
        
        g2d.setColor(new Color(250,149,31));
        g2d.fill(body);

        Circle eye = new Circle(x+3*size/4, y + size/8, size/10, Color.BLACK);
        eye.draw(g2d);
        Circle pupil = new Circle(x+25*size/32, y + 9*size/64, size/20, Color.WHITE);
        pupil.draw(g2d);

        Line tail_line_1 = new Line(x + (1.0/8)*size, y + ((1.0/4) * size), x - (2.2/8)*size, y + (1.0/4)*size, 1, Color.BLACK);
        tail_line_1.draw(g2d);

        Line tail_line_2 = new Line(x + (1.2/8)*size, y + ((0.7/4) * size), x - (1.0/8)*size, y - (0.2/4)*size, 1, Color.BLACK);
        tail_line_2.draw(g2d);

        Line tail_line_3 = new Line(x + (1.2/8)*size, y + ((1.3/4) * size), x - (1.0/8)*size, y + (2.2/4)*size, 1, Color.BLACK);
        tail_line_3.draw(g2d);

    }

    /**
     * Standard mutator method for adjusting the x position of the fish
     * @param distance - number of pixels which the object will move along the x-axis
     */
    @Override
    public void adjustX(double distance) {
        x += distance;
    }

    /**
     * Accessor method which returns the x position
     * @return position along the x-axis
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Accessor method which returns the y position
     * @return position along the y-axis
     */
    public double getY() {
        return y;
    }

    /**
     * Accessor method which returns the size of the fish
     * @return size of the fish
     */
    public double getSize() {
        return size;
    }

    /**
     * Accessor method for the height of the fish which is used to check
     * whether the fish has reached the top or bottom of the JFrame.
     * @return height of the fish
     */
    public double getHeight() {
        return size/2;
    }

    /**
     * Accessor method for the fall duration, 1000 ms or 1 second
     * @return the fall duration, 1 second
     */
    public long getFall_Duration() {
        return FALL_DURATION;
    }

    /**
     * Accessor method for the exact time when the fish starts falling after it intersects one of the pipes
     * @return the time when the fish started falling
     */
    public long getFall_StartTime() {
        return fallStartTime;
    }

    /**
     * In every frame, the fall method is called.
     * The fish's y position increases by an amount equal to the fish's current velocity.
     * The fish's velocity is increased by the value of gravity, 2.0.
     */
    public void fall() {
        y += velocity;
        velocity += gravity;
    }

    /**
     * The fish moves upwards by 15 pixels whenever the user clicks the spacebar.
     */
    public void flap() {
        velocity = -15; 
    }

    /**
     * The accessor method for the hitbox of the fish used in collision detection.
     * The hitbox of the fish would be a rectangle enclosing the main body of the fish 
     * and does not include the fins or the tail.
     * @return the hitbox (Rectangle2D.Double) class of the fish

     */
    public Rectangle2D getBounds() {
        Rectangle2D.Double hitbox = new Rectangle2D.Double(x + (1.0/8)*size, y, (7.0/8)*size, (7.0/16)*size);

        return hitbox;

    }

    /**
     * This method is run when the fish hits or intersects with one of the pipes.
     * The fallStartTime takes the exact time when the fish hits the pipe.
     */
    public void startFalling() {
        isFalling = true;
        fallStartTime = System.currentTimeMillis();
    }

    /**
     * For one second from when the fish intersects the pipe until it falls off the screen,
     * this function is called to update the position of the fish.
     */
    public void updateFall() {
        if (isFalling) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - fallStartTime;

            if (elapsedTime < FALL_DURATION) {
                double progress = (double) elapsedTime / FALL_DURATION;
                double fallDistance = 1200 * (progress * progress);
                y += fallDistance - (1200 * ((double)(elapsedTime - 16) / FALL_DURATION) * ((double)(elapsedTime - 16) / FALL_DURATION));
            } else {
                isFalling = false;
            }
            }
        }
    }

