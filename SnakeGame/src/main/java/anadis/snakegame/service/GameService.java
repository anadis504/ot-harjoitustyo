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
 *
 * @author anadis
 */
public class GameService {

    private int width, height, score;
    private Snake snake;
    private Food food;
    private Direction direction;
    private boolean gameOver;

    public GameService() {
        this.height = Ui.height * Ui.blocksize;
        this.width = Ui.width * Ui.blocksize;
        this.snake = new Snake();
        this.food = new Food(5, 5);
        this.direction = Direction.RIGHT;
        this.gameOver = false;
        this.score = 0;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getScore() {
        return this.score;
    }
    
    public boolean getGameOver() {
        return this.gameOver;
    }

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

    public String foodColor() {
        return food.getColor();
    }

    public int foodX() {
        return food.getX() * Ui.blocksize;
    }
    
    public int foodY() {
        return food.getY() * Ui.blocksize;
    }
    
    public List<Block> getSnake() {
        return snake.getSnake();        
    }

    public void eat() {
        snake.grow();
        score++;
        food.relocate();
    }

    public Block getFood() {
        return this.food;
    }
}
