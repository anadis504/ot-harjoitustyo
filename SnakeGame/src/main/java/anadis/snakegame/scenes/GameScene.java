/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.scenes;

import anadis.snakegame.domain.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 *
 * @author anadis
 */
public class GameScene {

    private static int width;
    private static int height;
    private static int blocksize = 25;
    private Snake snake;
    private int score;
    private Food food;
    private Direction direction;
    private boolean gameOver;

    public GameScene(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(width, height);
        this.food = new Food(5, 5);
        this.direction = Direction.RIGHT;
        this.gameOver = false;
        this.score = 0;
    }
    
    public Scene getScene() {

        Pane root = new Pane();
        root.setPrefSize(width*blocksize, height*blocksize);
        Canvas canvas = new Canvas(blocksize * width, blocksize * height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    timeInstance(context);
                    return;
                }

                if (now - lastTick > 1000000000 / (score + 5)) {
                    lastTick = now;
                    timeInstance(context);
                }
            }
        }.start();

        Scene scene = new Scene(root, blocksize * width+2, blocksize * height+2);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                direction = Direction.UP;
            }
            if (event.getCode() == KeyCode.RIGHT) {
                direction = Direction.RIGHT;
            }
            if (event.getCode() == KeyCode.DOWN) {
                direction = Direction.DOWN;
            }
            if (event.getCode() == KeyCode.LEFT) {
                direction = Direction.LEFT;
            }
        });

        return scene;
    }

    public void newFood() {
        while (true) {
            food.relocate(width, height);
            for (Block block : snake.getSnake()) {
                if (block.equals(food)) {
                    continue;
                }
            }
            break;
        }
    }
    
    public void timeInstance(GraphicsContext context) {

        if (gameOver) {
            context.setFill(Color.RED);
            context.fillText("GAME OVER", width / 2 * blocksize, height / 2 * blocksize);
            return;
        }

        snake.move();
        snake.turn(direction);

        if (snake.getSnake().get(0).equals(food)) {
            snake.grow();
            score++;
            newFood();
        }

        if (snake.bodyCrash()) {
            gameOver = true;
        }

        fill(context);
    }
    
    public void fill(GraphicsContext context) {
        context.setFill(Color.BEIGE);
        context.fillRect(0, 0, width * blocksize, height * blocksize);

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
