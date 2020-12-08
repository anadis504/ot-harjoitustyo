/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.domain.GameService;
import anadis.snakegame.domain.ScoreService;
import anadis.snakegame.domain.*;
import anadis.snakegame.ui.Ui;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 *
 * @author anadis
 */
public class GameScene {

    private int width;
    private int height;
    private GameService gameService;
    private ScoreService scoreService;
    private AnimationTimer timer;
    private VBox inputForm;

    public GameScene(ScoreService service) {

        this.scoreService = service;
        this.width = Ui.width * Ui.blocksize;
        this.height = Ui.height * Ui.blocksize;

    }

    public Pane getScene() {

        this.gameService = new GameService();
        GridPane root = new GridPane();
        root.setPrefSize(width, height);
        Canvas canvas = new Canvas(width, height);
        GraphicsContext context = canvas.getGraphicsContext2D();

        this.initNewScoreForm();
        root.getChildren().addAll(canvas, Ui.back, inputForm);

        this.timer = new AnimationTimer() {
            long lastTimeInstance = 0;

            @Override
            public void handle(long now) {
                if (lastTimeInstance == 0) {
                    lastTimeInstance = now;
                    timeInstance(context);
                }

                if (now - lastTimeInstance > 1000000000 / (gameService.getScore() + 5)) {
                    lastTimeInstance = now;
                    timeInstance(context);
                }
            }
        };
        timer.start();

        return root;
    }

    public void timeInstance(GraphicsContext context) {
        boolean gameOver = gameService.getGameOver();
        Ui.back.setVisible(gameOver);

        if (gameOver) {
            paintGameOver(context);
            timer.stop();
            return;
        }
        gameService.gameUnit();
        paintBackground(context);
        paintFood(context);
        paintSnake(context);

    }

    public void paintGameOver(GraphicsContext context) {
        context.setFill(Color.RED);
        context.fillText("GAME OVER\nYOUR SCORE  " + gameService.getScore(), width / 3, height / 2);
        this.inputForm.setVisible(scoreService.isTopTwenty(gameService.getScore()));
        Ui.back.setVisible(!scoreService.isTopTwenty(gameService.getScore()));
    }

    public void paintBackground(GraphicsContext context) {
        context.setFill(Color.BEIGE);
        context.fillRect(0, 0, width, height);

        context.setFill(Color.DEEPSKYBLUE);
        context.fillText("Score: " + gameService.getScore(), 10, 30);
    }

    public void paintFood(GraphicsContext context) {
        gameService.foodColor();
        Color color = Color.web(gameService.foodColor());
        context.setFill(color);
        context.fillOval(gameService.foodX(), gameService.foodY(), Ui.blocksize, Ui.blocksize);
    }

    public void paintSnake(GraphicsContext context) {
        for (Block block : gameService.getSnake()) {
            context.setFill(Color.DARKSEAGREEN);
            context.fillRect(block.getX() * Ui.blocksize, block.getY() * Ui.blocksize, Ui.blocksize, Ui.blocksize);
        }
    }

    public void initNewScoreForm() {
        this.inputForm = new VBox();
        inputForm.setAlignment(Pos.TOP_CENTER);
        inputForm.setSpacing(10);
        inputForm.setPadding(new Insets(50, 100, 50, 100));
        Label namelabel = new Label("your name");
        TextField inputField = new TextField();
        Button submit = new Button("save");

        submit.setOnAction(e -> {
            String nickname = inputField.getText();
            System.out.println(inputField.getText());
            scoreService.addScore(nickname, gameService.getScore());
            Ui.back.fire();
        });

        inputForm.getChildren().addAll(namelabel, inputField, submit);
        inputForm.setVisible(false);
    }

    public GameService getService() {
        return this.gameService;
    }

    public void stopTimer() {
        if (this.timer != null) {
            this.timer.stop();
        }
    }

}
