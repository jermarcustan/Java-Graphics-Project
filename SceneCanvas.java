import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.geom.*;

// Ensure this import is here
import javax.swing.*;
import java.util.Random;


public class SceneCanvas extends JComponent {
    private Fish fish;
    private ArrayList<Pipe> pipes;
    private ArrayList<DrawingObject> objects;
    private Timer timer;
    private boolean GameOver;
    private double score;
    private JButton playAgainButton;
    private int pipeCounter;
    
    

    public SceneCanvas() {
        setPreferredSize(new Dimension(800, 600));
        initializeGame();

        // Create Play Again button (initially invisible)
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(350, 300, 100, 30);
        playAgainButton.setVisible(false);
        playAgainButton.addActionListener(e -> resetGame());
        add(playAgainButton);

        // Create a timer for the main game loop
        timer = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!GameOver) {
                fish.fall(); // The bird falls continuously

                // Move the pipes and remove any that are off-screen
                ArrayList<Pipe> pipesToRemove = new ArrayList<>();
                for (Pipe pipe : pipes) {
                    pipe.moveLeft();

                    if (!pipe.getPass() && fish.getX() >= pipe.getX() + pipe.getWidth()) {
                        pipe.setPass();
                        score += 0.5;
                    }

                    if (pipe.OverBounds()) {
                        pipesToRemove.add(pipe);
                    }
                }

                // Increment pipe counter and generate pipes at intervals
                pipeCounter++;
                if (pipeCounter >= 30) {  // Roughly every 1.5 seconds (assuming 60 FPS)
                    generatePipes();
                    pipeCounter = 0;  // Reset the counter
                }

                objects.removeAll(pipesToRemove);  // Remove off-screen pipes

                checkCollisions();
                checkBounds(); 
            }
                repaint(); // Important! Refresh the screen
            }
        });


        // Key listener to make the bird "flap"
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    fish.flap();
                }
            }
        });

        setFocusable(true); // Allow the component to receive focus
        requestFocusInWindow(); // Request focus for the component
    }

    public void startGame() {
        timer.start();
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call the superclass method first
        Graphics2D g2d = (Graphics2D) g;

        // Iterate through objects and draw them
        for (DrawingObject obj : objects) {
            obj.draw(g2d);
        }

        Font scoreFont = new Font("Arial", Font.BOLD, 24);
        g2d.setFont(scoreFont);

        FontMetrics fm = g2d.getFontMetrics();
        String scoreText = "Score: " + String.valueOf((int) score);
        int textWidth = fm.stringWidth(scoreText);
        int textHeight = fm.getHeight();

        int x = 5;
        int y = 5;
        int padding = 5;
        int borderWidth = textWidth + (padding * 2);
        int borderHeight = textHeight + (padding * 2);
    
        // Draw the border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, borderWidth, borderHeight);
    
        // Draw the score text
        g2d.setColor(Color.BLACK);
        g2d.drawString(scoreText, x + padding, y + fm.getAscent() + padding);
    }

    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (fish.getBounds().intersects(pipe.getBounds())) {
                endGame();
            }
        }
    }

    private void checkBounds() {
        if (fish.getY() <= 0 || fish.getY() >= 600-fish.getHeight()) {
            endGame();
        }
    }

    private void generatePipes() {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(400);
        Pipe topPipe = new Pipe(800, 0, 100, rand_int1, Color.GREEN);
        pipes.add(topPipe);

        // the space between the pipes is 200

        Pipe bottomPipe = new Pipe(800, rand_int1+200, 100, 600-rand_int1-200, Color.GREEN);
        pipes.add(bottomPipe);
        // Add new pipes to the objects list so they will be drawn
        objects.add(topPipe);
        objects.add(bottomPipe);
    }

    private void initializeGame() {
        fish = new Fish(100, 300, 100);
        pipes = new ArrayList<>();
        objects = new ArrayList<>();
        score = 0;
        GameOver = false;
        pipeCounter = 0;

        // Add the fish and pipes to the objects list
        objects.clear();
        objects.add(fish);  // Make sure the fish is added to the list
        addInitialPipes();
    }

    private void endGame() {
        GameOver = true;
        timer.stop();
        playAgainButton.setVisible(true);
        System.out.println("Game Over!");
    }

    private void resetGame() {
        initializeGame();
        playAgainButton.setVisible(false);
        timer.start();
        repaint();
    }
    private void addInitialPipes() {
        Pipe topPipe = new Pipe(800, 0, 100, 200, Color.GREEN);
        Pipe bottomPipe = new Pipe(800, 400, 100, 200, Color.GREEN);
        
        pipes.add(topPipe);
        pipes.add(bottomPipe);
        objects.add(topPipe);
        objects.add(bottomPipe);
    }

    

   

}
