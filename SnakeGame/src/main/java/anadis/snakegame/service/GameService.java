/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.service;

import anadis.snakegame.domain.Block;
import anadis.snakegame.domain.Direction;
import anadis.snakegame.domain.Food;
import anadis.snakegame.domain.Snake;
import anadis.snakegame.ui.Ui;
import java.util.List;

/**
 * The Service class that provides the interface between application logic and
 * graphical user interface. 
 * This class keeps track of the objects on the game
 * screen and the game score.
 * 
 * @author anadis
 */
public class GameService {

    private int score;
    private Snake snake;
    private Food food;
    private Direction direction;
    private boolean gameOver;

    /**
     * 
     */
    public GameService() {
        this.snake = new Snake(0, Ui.height / 2);
        this.food = new Food(5, 5);
        this.direction = Direction.RIGHT;
        this.gameOver = false;
        this.score = 0;
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
        snake.border();

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
    }

    /**
     *
     * @return food Block
     */
    public Block getFood() {
        return this.food;
    }
}
