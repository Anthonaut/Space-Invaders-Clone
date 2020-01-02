// utility
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Area;

// graphics

// events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;

    // FIXME list your game objects here
    GraphicsObject playerShip;

    GraphicsObject bullet;
    String bulletVisible = "No";

    invader [][] fleet = new invader[5][10];
    String [][] alive;
    String direction = "R";
    String changedirection = "N";

    GraphicsObject loseScreen;
    GraphicsObject winScreen;

    /* Constructor for a Space Invaders game
     */
    public SpaceInvaders() {
        // fix the window size and background color
        this.canvasWidth = 600;
        this.canvasHeight = 400;
        this.backgroundColor = Color.WHITE;
        setPreferredSize(new Dimension(this.canvasWidth, this.canvasHeight));

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);

        // FIXME initialize your game objects

        //player ship
        this.playerShip = new playerShip(300, canvasHeight - 30);

        // Bullet
        this.bullet = new bullet(100, 390);

        // invader fleet
        fleet = new invader[5][10];
        alive = new String[5][10];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 10; c++)
            {
                fleet[r][c] = new invader(20, 20);
                fleet[r][c].x = 40 * c;
                fleet[r][c].y = 40 * r;
                alive[r][c] = "alive";
            }
        }


        // Lose Screen
        this.loseScreen = new loseScreen(0, 0);
        // Win Screen
        this.winScreen = new winScreen(0, 0);
    }

    /* Start the game
     */
    @Override
    public void run() {
        // show this window
        display();

        // set a timer to redraw the screen regularly
        this.timer.start();
    }

    /* Create the window and display it
     */
    private void display() {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(this);
        jframe.pack();
        jframe.setVisible(true);
    }

    /* Run all timer-based events
     *
     * @param e  An object describing the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // update the game objects
        update();
        // draw every object (this calls paintComponent)
        repaint(0, 0, this.canvasWidth, this.canvasHeight);
        // increment the frame counter
        this.frame++;
    }

    /* Paint/Draw the canvas.
     *
     * This function overrides the paint function in JPanel. This function is
     * automatically called when the panel is made visible.
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear the canvas before painting
        clearCanvas(g);
        if (hasWonGame()) {
            paintWinScreen(g);
        } else if (hasLostGame()) {
            paintLoseScreen(g);
        } else {
            paintGameScreen(g);
        }
    }

    /* Clear the canvas
     *
     * @param g The Graphics representing the canvas
     */
    private void clearCanvas(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);
    }

    /* Respond to key release events
     *
     * A key release is when you let go of a key
     * 
     * @param e  An object describing what key was released
     */
    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key type events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyTyped(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key press events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // FIXME what happens when left arrow is pressed
            this.playerShip.x -= playerShip.speed_x;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // FIXME what happens when right arrow is pressed
            this.playerShip.x += playerShip.speed_x;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && bulletVisible.equals("No")) {
            // FIXME what happens when space bar is pressed
            bulletVisible = "Yes";
            bullet.x = playerShip.x + 5;
            bullet.y = playerShip.y - 10;
        }
    }

    /* Update the game objects
     */
    private void update() {
        // FIXME update game objects here

        // bullet shoots upwards
        if (bulletVisible.equals("Yes"))
            bullet.y -= bullet.speed_y;
        // if bullet goes off screen
        if (bullet.y < 0)
            bulletVisible = "No";
        // if a bullet has hit an alien
        if (bulletVisible.equals("Yes"))
            for (int r = 0; r < 5 ; r++)
                for (int c = 0; c < 10; c++)
                {
                    if (alive[r][c].equals("alive"))
                        if (bullet.y == fleet[r][c].y + 20 && (fleet[r][c].x < bullet.x + 2  && bullet.x + 2 < fleet[r][c].x + 20))
                        {
                            bulletVisible = "No";
                            alive[r][c] = "dead";
                        }
                }

        // moving the invader fleet
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 10; c++) {
                if (direction.equals("R"))
                    fleet[r][c].x += fleet[r][c].speed_x;
                if (direction.equals("L"))
                    fleet[r][c].x -= fleet[r][c].speed_x;
            }
        }

        // check if invader fleet has hit the borders
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 10; c++)
            {
                if (alive[r][c].equals("alive")) {
                    if (fleet[r][c].x + 20 > canvasWidth) {
                        direction = "L";
                        changedirection = "Y";
                    }
                    if (fleet[r][c].x < 0) {
                        direction = "R";
                        changedirection = "Y";
                    }
                }
            }
        }

        // move invader fleet down when direction changes
        if (changedirection.equals("Y"))
        {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 10; c++)
                {
                    fleet[r][c].y += fleet[r][c].speed_y;
                    changedirection = "N";
                }
            }
        }




    }


    /* Check if the player has lost the game
     * 
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        // FIXME delete this when ready
        for (int r = 0; r < 5; r++)
            for (int c = 0; c < 10; c++)
            {
                if (alive[r][c].equals("alive"))
                {
                    if (fleet[r][c].y + 20 > playerShip.y) {
                        return true;
                    }
                }
            }
        return false;
    }

    /* Check if the player has won the game
     * 
     * @returns  true if the player has won, false otherwise
     */
    private boolean hasWonGame() {
        // FIXME delete this when ready
        // counting the amount of dead invaders
        int counter = 0;
        for (int r = 0; r < 5; r++)
            for (int c = 0; c < 10; c++)
            {
                if (alive[r][c].equals("dead"))
                    counter = counter + 1;
            }
        if (counter == 50)
            return true;
        else
            return false;
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) {
        // FIXME draw game objects here

        // Draw the Player ship
        playerShip.draw(g);


        // Draw the bullet if visible
        if (bulletVisible.equals("Yes"))
            bullet.draw(g);

        // Draw the invader fleet
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 10; c++)
            {
                if (alive[r][c].equals("alive"))
                    new invader(fleet[r][c].x, fleet[r][c].y).draw(g);
            }
        }
    }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        // FIXME draw the win screen here
        winScreen.draw(g);
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        // FIXME draw the game over screen here
        loseScreen.draw(g);

    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
}
