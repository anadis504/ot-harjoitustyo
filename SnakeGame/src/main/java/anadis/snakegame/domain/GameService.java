/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.ui.Ui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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

    public void timeInstance(GraphicsContext context) {
        this.context = context;
        Ui.back.setVisible(gameOver);
        System.out.println(Ui.back);
        
        if (gameOver) {
            paintGameOver();
            return;
        }

        snake.move();
        snake.turn(direction);

        if (snake.getSnake().get(0).equals(food)) {
            eat();
        }

        if (snake.bodyCrash()) {
            gameOver = true;
        }
        paintBackground();
        paintFood();
        paintSnake();
    }

    public void paintFood() {

        Color color = food.getColor();
        context.setFill(color);
        context.fillOval(food.getX() * Ui.blocksize, food.getY() * Ui.blocksize, Ui.blocksize, Ui.blocksize);
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

    public void paintGameOver() {
        context.setFill(Color.RED);
        context.fillText("GAME OVER \nYOUR SCORE " + score, width / 3, height / 2);
    }

    public void paintBackground() {
        context.setFill(Color.BEIGE);
        context.fillRect(0, 0, width, height);

        context.setFill(Color.DEEPSKYBLUE);
        context.fillText("Score: " + score, 10, 30);
    }

}
