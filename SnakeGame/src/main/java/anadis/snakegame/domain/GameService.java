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
     *
     */
    public GameService(int level) {
        this.snake = new Snake(0, Ui.height / 2);
        this.food = new Food(5, 5);
        this.direction = Direction.RIGHT;
        this.gameOver = false;
        this.score = 0;
        this.level = level;
        this.counter = 0;
        this.maxSteps = (int) Math.floor((Ui.height+Ui.width)*0.75);
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

    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return true if Game Over, otherwise false
     */
    public boolean getGameOver() {
        return this.gameOver;
    }

    /**
     * Method for maintaining one time instance of the game. Moves the snake one
     * pixel unit, relocates the snake if snake is leaving the game frame, calls
     * method eat if snake head hits food and set Game Over if snake crashes.
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
     * @return snake ArrayList<Block>
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
     * @return food Block
     */
    public Block getFood() {
        return this.food;
    }
    
    public int getRemainingCounter() {
        return (maxSteps-counter+1)/2;
    }
}
