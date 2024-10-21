/**
 * SceneCanvas.java
 *
 * Description: This class represents the main game canvas where all the game objects, including the fish, pipes,
 * bubbles, seaweed clusters, and score, are drawn. The class also handles the game loop, user input,
 * collision detection, and game state management, including starting, resetting, and ending the game.
 *
 * Author: Ang, Tan
 * ID Number: 200302
 * Date Created: October 20, 2024
 *
 * Certification of Authorship:
 * I certify that I have authored this code on my own. Any assistance received in writing this code
 * has been acknowledged, and the code has been created entirely by myself.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The SceneCanvas class draws all of the game elements, including the fish, pipes, bubbles, and seaweed clusters. It also controls the game logic, such as
 * detecting collisions, handling user input, playing background music, and managing the game states (start, end, reset).
 */
public class SceneCanvas extends JComponent {
    private Fish fish;
    private ArrayList<Pipe> pipes;
    private ArrayList<DrawingObject> objects;
    private ArrayList<Bubble> bubbles; // Add bubbles for background animation
    private ArrayList<SeaweedCluster> seaweedClusters;
    private Timer timer;
    private boolean GameOver;
    private boolean started;
    private double score;
    private JButton playAgainButton;
    private int pipeCounter;
    private int pipePassed;
    private Clip backgroundMusic;
    private Clip scoreMusic;
    private Clip gameOverMusic;
    private float volume = 0.2f;

    /**
     * Constructor for the SceneCanvas class.
     * Initializes the game objects and sets up the game environment. This includes loading background music,
     * generating bubbles, handling the game loop, and listening for user input.
     */
    public SceneCanvas() {
        setPreferredSize(new Dimension(800, 600));
        initializeGame();

        LoadBGMusic();
        LoadScoreMusic();
        LoadGameOverMusic();
        setVolume(volume);
        startBGMusic();

        // Create Play Again button (initially invisible)
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(350, 300, 100, 30);
        playAgainButton.setVisible(false);
        playAgainButton.addActionListener(e -> resetGame());
        add(playAgainButton);

        // Generate bubbles
        bubbles = new ArrayList<>();
        generateBubbles();

        // Create a timer for the main game loop.
        timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GameOver && started) {
                    fish.fall(); // The fish falls continuously after the first space bar press

                    // Move the pipes and remove any that are off-screen
                    ArrayList<Pipe> pipesToRemove = new ArrayList<>();
                    for (Pipe pipe : pipes) {
                        pipe.moveLeft();

                        if (!pipe.getPass() && fish.getX() >= pipe.getX() + pipe.getWidth()) {
                            pipe.setPass();
                            score += 0.5;
                            pipePassed++;
                            // Play score music after every pair of pipes passed
                            if (pipePassed % 2 == 0) {
                                startScoreMusic();
                            }
                        }

                        if (pipe.OverBounds()) {
                            pipesToRemove.add(pipe);
                        }
                    }

                    // Animate bubbles
                    for (Bubble bubble : bubbles) {
                        bubble.rise();
                    }

                    // Increment pipe counter and generate pipes at intervals
                    pipeCounter++;
                    if (pipeCounter >= 30) {
                        generatePipes();
                        pipeCounter = 0;
                    }

                    objects.removeAll(pipesToRemove); // Remove off-screen pipes

                    checkCollisions();
                    checkBounds();
                }
                repaint(); // Important! Refresh the screen
            }
        });

        // Key listener to make the fish "flap" when the space bar is pressed.
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!started) {
                        started = true;
                    }
                    fish.flap(); // Make the fish jump
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Method that starts the game by starting the timer which controls the game loop.
     */
    public void startGame() {
        timer.start();
    }

    /**
     * Paints all components on the canvas, including the ocean background, seabed, fish, pipes, bubbles, seaweed clusters,
     * and the score.
     *
     * @param g the Graphics object used to draw on the canvas
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the ocean background with a gradient
        GradientPaint ocean = new GradientPaint(0, 0, new Color(135, 206, 250), 0, getHeight(), new Color(0, 105, 148));
        g2d.setPaint(ocean);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw seabed at the bottom
        SeaBed seabed = new SeaBed(0, getHeight() - 50, getWidth(), 50, new Color(139, 69, 19));
        seabed.draw(g2d);

        // Draw animated bubbles in the background
        for (Bubble bubble : bubbles) {
            bubble.draw(g2d);
        }

        // Draw all objects (fish, ocean-themed pipes, etc.)
        for (DrawingObject obj : objects) {
            obj.draw(g2d);
        }

        // Draw the score on top
        drawScore(g2d);
    }

    /**
     * Method that loads and prepares the background music for the game.
     */
    private void LoadBGMusic() {
        try {
            File audioFile_background = new File("Background_music.wav");
            AudioInputStream audioStream_background = AudioSystem.getAudioInputStream(audioFile_background);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream_background);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that loads and prepares the score music. This is played whenever the player successfully moves past two pipes.
     */
    private void LoadScoreMusic() {
        try {
            File audioFile_score = new File("Score_music.wav");
            AudioInputStream audioStream_score = AudioSystem.getAudioInputStream(audioFile_score);
            scoreMusic = AudioSystem.getClip();
            scoreMusic.open(audioStream_score);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that loads and prepares the game-over music, which is played when the player loses.
     */
    private void LoadGameOverMusic() {
        try {
            File audioFile_gameover = new File("GameOver_music.wav");
            AudioInputStream audioStream_gameover = AudioSystem.getAudioInputStream(audioFile_gameover);
            gameOverMusic = AudioSystem.getClip();
            gameOverMusic.open(audioStream_gameover);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts playing the background music in a loop.
     */
    private void startBGMusic() {
        if (backgroundMusic != null && !backgroundMusic.isRunning()) {
            backgroundMusic.setMicrosecondPosition(0);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Starts playing the score music when the player successfully passes two pipes.
     */
    private void startScoreMusic() {
        if (scoreMusic != null && !scoreMusic.isRunning()) {
            scoreMusic.setMicrosecondPosition(0);
            scoreMusic.start();
        }
    }

    /**
     * Starts playing the game-over music when the player loses.
     */
    private void startGameOverMusic() {
        if (gameOverMusic != null && !gameOverMusic.isRunning()) {
            gameOverMusic.setMicrosecondPosition(0);
            gameOverMusic.start();
        }
    }

    /**
     * Sets the volume of the background music.
     *
     * @param volume a float representing the desired volume (between 0.0 and 1.0)
     */
    private void setVolume(float volume) {
        if (volume < 0f){
            volume = 0f;
        }
        if (volume > 1f){
            volume = 1f;
        }

        FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }

    /**
     * Stops the background music.
     */
    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    /**
     * Generates bubbles at random positions in the game.
     */
    private void generateBubbles() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) { // Generate 20 bubbles
            double x = rand.nextInt(800); // Random x-position
            double y = rand.nextInt(600); // Random y-position
            double height = rand.nextDouble() * 15 + 5; // Random size between 5 and 20
            double width = height * 0.9;
            bubbles.add(new Bubble(x, y, width, height));
        }
    }

    /**
     * Checks for collisions between the fish and pipes, ending the game if a collision occurs.
     */
    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (fish.getBounds().intersects(pipe.getBounds())) {
                endGame();
                break; // Exit the loop immediately after a collision
            }
        }
    }

    /**
     * Checks if the fish is out of bounds (top or bottom of the screen), ending the game if it is.
     */
    private void checkBounds() {
        if (fish.getY() <= 0 || fish.getY() >= 600 - fish.getHeight()) {
            endGame();
        }
    }

    /**
     * Generates new pipes at random intervals and positions them along the screen.
     */
    private void generatePipes() {
        Random rand = new Random();
        int randHeight = rand.nextInt(400); // Random height for the top pipe

        Pipe topPipe = new Pipe(800, 0, 100, randHeight);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(800, randHeight + 200, 100, 600 - randHeight - 200);
        pipes.add(bottomPipe);

        objects.add(topPipe);
        objects.add(bottomPipe);
    }

    /**
     * Initializes all game objects and sets the game to its starting state.
     */

    private void initializeGame() {
        fish = new Fish(100, 300, 60);
        pipes = new ArrayList<>();
        objects = new ArrayList<>();
        score = 0;
        GameOver = false;
        started = false;
        pipeCounter = 0;
        pipePassed = 0;

        // Initialize seaweed clusters to be on top of the seabed
        seaweedClusters = new ArrayList<>();
        seaweedClusters.add(new SeaweedCluster(50, 550, 100, 20));  // y position set to 550 (top of the seabed)
        seaweedClusters.add(new SeaweedCluster(300, 550, 120, 25)); // Adjusted y position
        seaweedClusters.add(new SeaweedCluster(600, 550, 90, 15));  // Adjusted y position

        // Add seaweed clusters to the objects list to draw them
        objects.addAll(seaweedClusters);

        objects.add(fish);
        addInitialPipes();
    }

    /**
     * Ends the game, stops the main game loop, and triggers the game-over sequence.
     */
    private void endGame() {
        GameOver = true;
        timer.stop(); // Stop the main game loop
        fish.startFalling();
        stopBackgroundMusic();
        startGameOverMusic();
        setFocusable(false);

        // Create a delay before showing the Game Over dialog. This prevents the user from accidentally playing again by pressing space bar.
        Timer fallTimer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fish.updateFall();
                repaint();

                if (System.currentTimeMillis() - fish.getFall_StartTime() >= fish.getFall_Duration()) {
                    ((Timer)e.getSource()).stop();
                    int response = JOptionPane.showConfirmDialog(SceneCanvas.this, "Game Over! Your score: " + (int) score + "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        resetGame(); // Restart the game if the player chooses "Yes"
                    } else {
                        playAgainButton.setVisible(false); // Ensure it's hidden if "No"
                    }

                    setFocusable(true);
                    requestFocusInWindow();
                }
            }
        });
        fallTimer.start();
    }

    /**
     * Resets the game state to the starting conditions, reinitializes objects, and restarts the game.
     */
    private void resetGame() {
        initializeGame();
        playAgainButton.setVisible(false);
        timer.start();
        requestFocusInWindow();
        repaint();
        stopBackgroundMusic();
        LoadBGMusic();
        setVolume(volume);
        startBGMusic();
    }

    /**
     * Adds initial pipes to the game screen when the game begins.
     */
    private void addInitialPipes() {
        Pipe topPipe = new Pipe(800, 0, 100, 200);
        Pipe bottomPipe = new Pipe(800, 400, 100, 200);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
        objects.add(topPipe);
        objects.add(bottomPipe);
    }

    /**
     * Draws the current game score on the screen.
     *
     * @param g2d the Graphics2D object used for drawing the score
     */
    private void drawScore(Graphics2D g2d) {
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

        // Draw the border for the score display
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, borderWidth, borderHeight);

        // Draw the score text
        g2d.setColor(Color.BLACK);
        g2d.drawString(scoreText, x + padding, y + fm.getAscent() + padding);
    }
}
