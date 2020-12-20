/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.ui.Ui;
import java.util.List;

/**
 * The Service class that provides the interface between application logic and
 * graphical user interface. This class keeps track of the objects on the game
 * screen and the game score.
 *
 * @author anadis
 */
public class GameService {

    private int score, level, counter, maxSteps;
    private Snake snake;
    private Food food;
    private Direction direction;
    private boolean gameOver;

    /**
     * The Service class that provides the interface between application logic
     * and graphical user interface.
     *
     * Initials the game of width*height pixels, snake coming on from the center
     * left side and food located in the northwest quarter of the frame
     *
     * @param level
     */
    public GameService(int level) {
        this.snake = new Snake(0, Ui.height / 2);
        this.food = new Food(Ui.height / 4, Ui.width / 4);
        this.direction = Direction.RIGHT;
        this.gameOver = false;
        this.score = 0;
        this.level = level;
        this.counter = 0;
        this.maxSteps = (int) Math.floor((Ui.height + Ui.width) * 0.75);
    }

    /**
     * Method for setting the direction of the snake
     *
     * @param direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     *
     * @return amount of points
     */
    public int getScore() {
        return this.score;
    }

    /**
     *
     * @return the level of the game
     */
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return true if the game is over, otherwise false
     */
    public boolean getGameOver() {
        return this.gameOver;
    }

    /**
     * Method for maintaining one time instance of the game. Moves the snake one
     * pixel unit, checks if snake is moving outside of the game frame,
     * relocates the snake if snake is leaving the game frame or sets Game Over
     * according to the game level, calls method eat if snake head hits food and
     * set Game Over if snake crashes in itself.
     */
    public void gameUnit() {

        snake.move(direction);
        setCounter();

        if (snake.hitFrame()) {
            if (level == 1 || level == 3) {
                snake.border();
            } else if (level == 2 || level == 4) {
                gameOver = true;
            }
        }

        if (snake.getSnake().get(0).equals(food)) {
            eat();
        }

        if (snake.bodyCrash()) {
            gameOver = true;
        }

    }

    /**
     *
     * @return color of the food
     */
    public String foodColor() {
        return food.getColor();
    }

    /**
     * increases the counter by one for levels 3 and 4
     */
    public void setCounter() {
        if (level == 1 || level == 2) {
            return;
        }
        counter++;
        if (counter > maxSteps) {
            food.relocate();
            counter = 0;
        }
    }

    /**
     *
     * @return food's X coordinate
     */
    public int foodX() {
        return food.getX() * Ui.blocksize;
    }

    /**
     *
     * @return food's Y coordinate
     */
    public int foodY() {
        return food.getY() * Ui.blocksize;
    }

    /**
     *
     * @return ArrayList of Bloc objects, the snake
     */
    public List<Block> getSnake() {
        return snake.getSnake();
    }

    private void eat() {
        snake.grow();
        score++;
        food.relocate();
        counter = 0;
    }

    /**
     *
     * @return food, Block object
     */
    public Block getFood() {
        return this.food;
    }

    /**
     *
     * @return the remaining time before food is automatically relocated,
     * formatted to fit to gui
     */
    public int getRemainingCounter() {
        return (maxSteps - counter + 2) / 3;
    }
}
