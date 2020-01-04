/**
 * Board.java Class for Game Board and methods
 *
 * @author: Yew Journ Chan
 */

//java Abstract Window Toolkit utilized to develop GUI
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
/*----------INSTANCE VARIABLES----------*/
private final static int BOARDWIDTH = 1000; //this will hold the widthe of the window
private final static int BOARDHEIGHT = 980; // this will hold the height of the window
private final static int PIXELSIZE = 25; // this is used to present the pixel size of cell and snake's body joint
//we have to set the amount of pixels in the game because:
//if there are any less pixels, game will end prematurely
//if there are any more pixels, game will have no winning formula

private final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT)
        / (PIXELSIZE * PIXELSIZE);

private boolean inGame = true; // checks if game is still running
private Timer timer; // timer used to record each tick time
private static int speed = 45; // set game speed, snake will travel faster when speed is lowered

// Instances for Snake and Food to call respective methods:
private Snake snake = new Snake();
private Food food = new Food();

/*----------CONSTRUCTOR METHODS----------*/
public Board() {

    addKeyListener(new Keys());
    setBackground(Color.BLACK);
    setFocusable(true);

    setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

    initializeGame();
}
/*------------OTHER METHODS-------------*/
@Override //used to paint components to the displayed screen
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    draw(g);
}

void draw(Graphics g) { //will draw the snake and food which is called through repaint();
    if (inGame == true) { //will check if game is running and will only draw if snake is still alive
        g.setColor(Color.green);
        g.fillRect(food.getFoodX(), food.getFoodY(), PIXELSIZE, PIXELSIZE); // food

        for (int i = 0; i < snake.getJoints(); i++) { // will draw the snake
            if (i == 0) { //snake's head
                g.setColor(Color.RED);
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        PIXELSIZE, PIXELSIZE);
            } else { // snake's body
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        PIXELSIZE, PIXELSIZE);
            }
        }

        Toolkit.getDefaultToolkit().sync(); //this will sync all graphics together
    } else {
        endGame(g); //if the snake is no longer alive, game will end
    }
}

void initializeGame() {
    snake.setJoints(3); // starts the program with a snake body initialized with 3 cells

    // Create our snake's body
    for (int i = 0; i < snake.getJoints(); i++) { // this will create the snake's body
        snake.setSnakeX(BOARDWIDTH / 2);
        snake.setSnakeY(BOARDHEIGHT / 2);
    }

    snake.setMovingRight(true); // starts the game with snake moving towards the right
    food.createFood(); // will generate food to spawn
    timer = new Timer(speed, this); // will set the timer to record game speed, and make game run/move
    timer.start(); // will begin the timer
}

void checkFoodCollisions() { //to define method fo when snake hits the food

    if ((proximity(snake.getSnakeX(0), food.getFoodX(), 20))
            && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {

        System.out.println("intersection");
        snake.setJoints(snake.getJoints() + 1); // will ad a joint to the snake
        food.createFood(); //will spawn new food
    }
}

void checkCollisions() { // will check collisions with snake's body and board edges

    for (int i = snake.getJoints(); i > 0; i--) { // method for if the snake hits its own body
        if ((i > 5) //if snake intersects with intself and is not larger than 5:
                && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                        .getSnakeY(0) == snake.getSnakeY(i)))) {
            inGame = false; // then the game ends
        }
    }

    if (snake.getSnakeY(0) >= BOARDHEIGHT) { // if the snake hits the board's edges
        inGame = false;
    }

    if (snake.getSnakeY(0) < 0) {
        inGame = false;
    }

    if (snake.getSnakeX(0) >= BOARDWIDTH) {
        inGame = false;
    }

    if (snake.getSnakeX(0) < 0) {
        inGame = false;
    }

    if (!inGame) { // will stop the timer once game has ended
        timer.stop();
    }
}

void endGame(Graphics g) {

    String message = "Game over"; //message indicating that game is over
    Font font = new Font("Times New Roman", Font.BOLD, 60); // create new font instance
    FontMetrics metrics = getFontMetrics(font);
    g.setColor(Color.red); //sets text color to red
    g.setFont(font); //sets font

    g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2, //draws message on board
            BOARDHEIGHT / 2);
    System.out.println("Game Ended");

}

@Override
public void actionPerformed(ActionEvent e) { //set to run constantly as long as snake is alive/ game is active
    if (inGame == true) {

        checkFoodCollisions();
        checkCollisions();
        snake.move();

        System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
                + " " + food.getFoodX() + ", " + food.getFoodY());
    }
    repaint(); // will repaint screen
}

private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
            snake.setMovingLeft(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
            snake.setMovingRight(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
            snake.setMovingUp(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
            snake.setMovingDown(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

            inGame = true;
            snake.setMovingDown(false);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setMovingUp(false);

            initializeGame();
        }
    }
}

private boolean proximity(int a, int b, int closeness) {
    return Math.abs((long) a - b) <= closeness;
}

public static int getAllDots() {
    return TOTALPIXELS;
}

public static int getDotSize() {
    return PIXELSIZE;
}
}