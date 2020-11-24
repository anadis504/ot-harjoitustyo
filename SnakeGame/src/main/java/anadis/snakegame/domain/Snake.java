/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anadis
 */
public class Snake {

    private List<Block> snake;
    private int height, width;

    public Snake(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            snake.add(new Block(0, height / 2));
        }
    }

    public List<Block> getSnake() {
        return this.snake;
    }

    public void move() {
        
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
        }
    }

    public void border() {
        if (snake.get(0).getX() < 0) {
            snake.get(0).setX(width-1);
        }
        if (snake.get(0).getY() < 0) {
            snake.get(0).setY(height-1);
        }
        if (snake.get(0).getX() >= width) {
            snake.get(0).setX(0);
        }
        if (snake.get(0).getY() >= height) {
            snake.get(0).setY(0);
        }
    }

    public void turn(Direction direction) {
        snake.get(0).setDirection(direction);
        border();
    }

    public boolean bodyCrash() {
        for (int i = snake.size() - 1; i > 0; i--) {
            if (snake.get(0).equals(snake.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void grow() {
        snake.add(new Block(-1, -1));
    }

}
