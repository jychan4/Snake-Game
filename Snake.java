/**
 * Snake.java Class that stores information about snake 
 *
 * @author Yew Journ Chan
 */

public class Snake {

// this will store the location for the joints of the snake's body
private final int[] x = new int[Board.getAllDots()];
private final int[] y = new int[Board.getAllDots()];

// this will store directions of the snake
private boolean movingLeft = false;
private boolean movingRight = false;
private boolean movingUp = false;
private boolean movingDown = false;

private int joints = 0; // this will store the number of dots the snake has joint
                    

/*----------ACCESSOR METHODS----------*/  
public int getSnakeX(int index) {
    return x[index];
}

public int getSnakeY(int index) {
    return y[index];
}

public boolean isMovingLeft() {
    return movingLeft;
}

public boolean isMovingRight() {
    return movingRight;
}

public boolean isMovingUp() {
    return movingUp;
}

public boolean isMovingDown() {
    return movingDown;
}

public int getJoints() {
    return joints;
}



/*----------MUTATOR METHODS----------*/  
public void setSnakeX(int i) {
    x[0] = i;
}

public void setSnakeY(int i) {
    y[0] = i;
}

public void setMovingLeft(boolean movingLeft) {
    this.movingLeft = movingLeft;
}

public void setMovingRight(boolean movingRight) {
    this.movingRight = movingRight;
}

public void setMovingUp(boolean movingUp) {
    this.movingUp = movingUp;
}

public void setMovingDown(boolean movingDown) {
    this.movingDown = movingDown;
}

public void setJoints(int j) {
    joints = j;
}


/*----------OTHER METHODS----------*/  
public void move() {
    for (int i = joints; i > 0; i--) {

        // this will move all joints of the snake by one unit
        x[i] = x[(i - 1)];
        y[i] = y[(i - 1)];
    }

    // Moves snake to the left
    if (movingLeft) {
        x[0] -= Board.getDotSize();
    }
    // Moves snake to the right
    if (movingRight) {
        x[0] += Board.getDotSize();
    }
    // Moves snake upwards
    if (movingDown) {
        y[0] += Board.getDotSize();
    }
    // Moeves snake downwrds
    if (movingUp) {
        y[0] -= Board.getDotSize();
    }

    // Dotsize represents the size of the joint, so a pixel of DOTSIZE
    // gets added on to the snake in that direction
}
 }