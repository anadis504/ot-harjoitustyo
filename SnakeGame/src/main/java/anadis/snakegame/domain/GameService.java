/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.ui.Ui;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    private GraphicsContext context;

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
        
        snake.move();
        snake.turn(direction);
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
    
    public void paintSnake() {
        for (Block block : snake.getSnake()) {
            context.setFill(Color.DARKSEAGREEN);
            context.fillRect(block.getX() * Ui.blocksize, block.getY() * Ui.blocksize, Ui.blocksize, Ui.blocksize);
        }
    }

    public void eat() {
        snake.grow();
        score++;
        food.relocate();
    }

    

    public void paintBackground() {
        context.setFill(Color.BEIGE);
        context.fillRect(0, 0, width, height);

        context.setFill(Color.DEEPSKYBLUE);
        context.fillText("Score: " + score, 10, 30);
    }

}
