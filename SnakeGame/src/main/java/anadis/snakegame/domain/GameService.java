/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.ui.Ui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author anadis
 */
public class GameService {

    private int width, height, blocksize, score;
    private Snake snake;
    private Food food;
    private Direction direction;
    private boolean gameOver;

    public GameService() {
        this.blocksize = Ui.blocksize;
        this.height = Ui.height * blocksize;
        this.width = Ui.width * blocksize;
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
    
    public void timeInstance(GraphicsContext context) {
        
        if (gameOver) {
            context.setFill(Color.RED);
            context.fillText("GAME OVER", width / 2, height / 2);
            return;
        }

        snake.move();
        snake.turn(direction);

        if (snake.getSnake().get(0).equals(food)) {
            snake.grow();
            score++;
            food.relocate(width, height);
        }

        if (snake.bodyCrash()) {
            gameOver = true;
        }

        fill(context);
    }
    
    public void fill(GraphicsContext context) {
        context.setFill(Color.BEIGE);
        context.fillRect(0, 0, width, height);

        context.setFill(Color.DEEPSKYBLUE);
        context.fillText("Score: " + score, 10, 30);

        Color color = food.getColor();

        context.setFill(color);
        context.fillOval(food.getX() * blocksize, food.getY() * blocksize, blocksize, blocksize);

        for (Block block : snake.getSnake()) {
            context.setFill(Color.DARKSEAGREEN);
            context.fillRect(block.getX() * blocksize, block.getY() * blocksize, blocksize, blocksize);
        }
    }
}
