import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class SceneFrame extends JFrame {
    private SceneCanvas canvas;

    public void setUpGUI() {
        setTitle("Project 1 - Ang - Tan"); // Update with your surnames
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new SceneCanvas();
        add(canvas);


        pack();
        getContentPane().setBackground(Color.WHITE );
        setVisible(true);
        canvas.startGame();
    }
}
