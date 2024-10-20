import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SceneCanvas extends JComponent {
    private Fish fish;
    private ArrayList<Pipe> pipes;
    private ArrayList<DrawingObject> objects;
    private ArrayList<Bubble> bubbles; // Add bubbles for background animation
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
    private float volume = 0.2f; // 


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

        // Create a timer for the main game loop
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
                            // play the music once after every pair of pipes passed 
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

        // Key listener to make the fish "flap"
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

    public void startGame() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the ocean background with a gradient
        GradientPaint ocean = new GradientPaint(0, 0, new Color(135, 206, 250), 0, getHeight(), new Color(0, 105, 148));
        g2d.setPaint(ocean);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw seabed at the bottom
        SeaBed seabed = new SeaBed(0, getHeight(), getWidth(), 50, new Color(139, 69, 19));
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

    private void LoadBGMusic() {
        try {
        // Background Music: https://www.youtube.com/watch?v=Hvdfx9avekU&list=PLwJjxqYuirCLkq42mGw4XKGQlpZSfxsYd&index=4
        File audioFile_background = new File("Background_music.wav");
        AudioInputStream audioStream_background = AudioSystem.getAudioInputStream(audioFile_background);
        backgroundMusic = AudioSystem.getClip();
        backgroundMusic.open(audioStream_background);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void LoadScoreMusic() {
        try {
        // Score Music: https://www.youtube.com/watch?v=zXKcybzvj7Y
        File audioFile_score = new File("Score_music.wav");
        AudioInputStream audioStream_score = AudioSystem.getAudioInputStream(audioFile_score);
        scoreMusic = AudioSystem.getClip();
        scoreMusic.open(audioStream_score);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void LoadGameOverMusic() {
        try {
        // Score Music: https://www.youtube.com/watch?v=bug1b0fQS8Y
        File audioFile_gameover = new File("GameOver_music.wav");
        AudioInputStream audioStream_gameover = AudioSystem.getAudioInputStream(audioFile_gameover);
        gameOverMusic = AudioSystem.getClip();
        gameOverMusic.open(audioStream_gameover);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void startBGMusic() {
        if (backgroundMusic != null && !backgroundMusic.isRunning()) {
            backgroundMusic.setMicrosecondPosition(0);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    private void startScoreMusic() {
        if (scoreMusic != null && !scoreMusic.isRunning()) {
            scoreMusic.setMicrosecondPosition(0);
            scoreMusic.start();  
        }
    }

    private void startGameOverMusic() {
        if (gameOverMusic != null && !gameOverMusic.isRunning()) {
            gameOverMusic.setMicrosecondPosition(0);
            gameOverMusic.start();  
        }
    }

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

    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
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

    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (fish.getBounds().intersects(pipe.getBounds())) {
                endGame();
                break; // Exit the loop immediately after a collision
            }
        }
    }

    private void checkBounds() {
        if (fish.getY() <= 0 || fish.getY() >= 600 - fish.getHeight()) {
            endGame();
        }
    }

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

    private void initializeGame() {
        fish = new Fish(100, 300, 60);
        pipes = new ArrayList<>();
        objects = new ArrayList<>();
        score = 0;
        GameOver = false;
        started = false;
        pipeCounter = 0;
        pipePassed = 0;

        objects.add(fish);
        addInitialPipes();
    }

    private void endGame() {
        GameOver = true;
        timer.stop(); // Stop the main game loop
        fish.startFalling();
        stopBackgroundMusic();
        startGameOverMusic();
        // Disable input while waiting for the delay
        setFocusable(false);

        // Create a delay before showing the Game Over dialog
        Timer fallTimer = new Timer(1000 / 60, new ActionListener() { // 1000 ms = 1 second delay
            @Override
            public void actionPerformed(ActionEvent e) {
                fish.updateFall();
                repaint();

                if (System.currentTimeMillis() - fish.getFall_StartTime() >= fish.getFall_Duration()){

                    ((Timer)e.getSource()).stop();
                    int response = JOptionPane.showConfirmDialog(SceneCanvas.this, "Game Over! Your score: " + (int) score + "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        resetGame(); // Restart the game if the player chooses "Yes"
                    } else {
                        playAgainButton.setVisible(false); // Just to ensure it's hidden if "No"
                    }

                    // Re-enable input after the dialog is shown
                    setFocusable(true);
                    requestFocusInWindow();
                    
                }

            }
        });
        fallTimer.start();
    }




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

    private void addInitialPipes() {
        Pipe topPipe = new Pipe(800, 0, 100, 200);
        Pipe bottomPipe = new Pipe(800, 400, 100, 200);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
        objects.add(topPipe);
        objects.add(bottomPipe);
    }

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
        // Draw the score text
        g2d.setColor(Color.BLACK);
        g2d.drawString(scoreText, x + padding, y + fm.getAscent() + padding);
    }
}

