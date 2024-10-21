/**
 * The SceneFrame class is a subclass of the JFrame class. 
 * It contains the SceneCanvas class where the game is rendered.
 *
 * Author: Ang, Tan
 * ID Number: 200302, 204947
 * Date Created: October 10, 2024
 *
 * Certification of Authorship:
 * I certify that I have authored this code on my own. Any assistance received in writing this code
 * has been acknowledged, and the code has been created entirely by myself.
 */

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;



public class SceneFrame extends JFrame {
    private SceneCanvas canvas;
/**
 * The setUpGUI method adds the SceneCanvas and places it in the content pane.
 */
    public void setUpGUI() {
        setTitle("Project 1 - Ang - Tan"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new SceneCanvas();
        add(canvas);

        // Disable resizing
        setResizable(false);

        // Automatically fits the window according to the preferred size
        pack();
        getContentPane().setBackground(Color.WHITE );
        setVisible(true);
        canvas.startGame();
    }
}
