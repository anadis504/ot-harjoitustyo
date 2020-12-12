/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.ui.Ui;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the snake in the game. Consists of a list of blocks
 *
 * @author anadis
 */
public class Snake {

    private List<Block> snake;
    private int height, width;

    /**
     * Initializing the snake to consist of three block, meaning snake is three
     * grid units long.
     *
     * Snakes receives its coordinates through constructor. Class
     * uses public variables 'width' and 'height' from Class 'Ui' to know the
     * frames of the game grid
     * 
     * @Param x
     * @Param y
     */
    public Snake(int x, int y) {
        this.width = Ui.width;
        this.height = Ui.height;
        this.snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            snake.add(new Block(x, y));
        }
    }

    /**
     *
     * @return snake - ArrayList<Block>
     */
    public List<Block> getSnake() {
        return this.snake;
    }

    /**
     * Method for moving the snake. Starting form the tail of the snake the 
     * method sets the coordinates of each Block of the snake to the coordinates
     * of the next Block. Finally the head of the snake moves in the given 
     * direction.
     * 
     * @param direction direction the snake is moving
     */
    public void move(Direction direction) {

        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
        }
        snake.get(0).setDirection(direction);

    }

    /**
     * If snake is going outside of the game frame makes the snake head appear 
     * again on the opposite side of the game grid
     */
    public void border() {
        if (snake.get(0).getX() < 0) {
            snake.get(0).setX(width - 1);
        }
        if (snake.get(0).getY() < 0) {
            snake.get(0).setY(height - 1);
        }
        if (snake.get(0).getX() >= width) {
            snake.get(0).setX(0);
        }
        if (snake.get(0).getY() >= height) {
            snake.get(0).setY(0);
        }
    }

    public boolean hitFrame() {
        return snake.get(0).getX() < 0 || snake.get(0).getX() >= width 
                || snake.get(0).getY() < 0 || snake.get(0).getY() >= height;
    }

    /**
     * Checks if the head of the snake hits any unit of the snake body  
     * 
     * @return true if snake crashes in itself, otherwise false
     */
    public boolean bodyCrash() {
        for (int i = snake.size() - 1; i > 0; i--) {
            if (snake.get(0).equals(snake.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds another Block to the List of Blocks that make the snake-body, making 
     * snake one pixel unit longer
     */
    public void grow() {
        snake.add(new Block(-1, -1));
    }

}
