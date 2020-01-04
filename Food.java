/**
 * Food.java Class to store food coordinates (x,y).
 *
 * @author Yew Journ Chan
 */

public class Food {

/*----------INSTANCE VARIABLES----------*/  
private Snake snake = new Snake();
private int foodX; // Stores X position of our food
private int foodY; // Stores Y position of our food
private final int RANDOMPOSITION = 40; // used to determine random positioning for food placement

/*----------MUTATOR METHODS----------*/   
public void createFood() {

    // will set the food's x & y position at random
    int location = (int) (Math.random() * RANDOMPOSITION);
    foodX = ((location * Board.getDotSize()));

    location = (int) (Math.random() * RANDOMPOSITION);
    foodY = ((location * Board.getDotSize()));

    if ((foodX == snake.getSnakeX(0)) && (foodY == snake.getSnakeY(0))) {
        createFood();
    }
}

/*----------ACCESSOR METHODS----------*/  
public int getFoodX() {
    return foodX;
}
public int getFoodY() {
    return foodY;
}
}