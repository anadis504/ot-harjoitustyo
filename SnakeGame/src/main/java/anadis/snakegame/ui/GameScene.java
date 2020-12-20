/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.domain.GameService;
import anadis.snakegame.domain.ScoreService;
import anadis.snakegame.domain.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class for showing the ongoing game in gui. Handles AnimationTimer
 *
 */
public class GameScene {

    private int width, height;
    private GameService gameService;
    private ScoreService scoreService;
    private AnimationTimer timer;
    private VBox inputForm;

    /**
     * Receives an instance of ScoreService class in constructor.
     *
     * @param service
     */
    public GameScene(ScoreService service) {

        this.scoreService = service;
        this.width = Ui.width * Ui.blocksize;
        this.height = Ui.height * Ui.blocksize;

    }

    /**
     * Initials the scene with an instance of AnimationTimer.
     *
     * @param level
     * @return Scene, the created scene to view the ongoing game
     */
    public Scene getScene(int level) {

        this.gameService = new GameService(level);
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

        Scene gameScene = new Scene(root);
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                gameService.setDirection(Direction.UP);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                gameService.setDirection(Direction.RIGHT);
            }
            if (event.getCode() == KeyCode.DOWN) {
                gameService.setDirection(Direction.DOWN);
            }
            if (event.getCode() == KeyCode.LEFT) {
                gameService.setDirection(Direction.LEFT);
            }
        });

        return gameScene;
    }

    /**
     * Handles one unit of the AnimationTimer. Check for game over and paint the
     * canvas accordingly.
     *
     * @param context, GraphicalContext used to animate the game
     */
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

    /**
     * Paints the Game Over picture and manages the checking of possible new
     * high score. If a new high score is reached, outputs the rank of the score
     * and shows the input form for filling out name of player
     *
     * @param context, GraphicalContext used to animate the game
     */
    public void paintGameOver(GraphicsContext context) {
        int rank = scoreService.generateRank(gameService.getScore(), gameService.getLevel());
        context.setFill(Color.RED);
        context.setFont(Font.font("Arial", 17));
        if (rank <= 20) {
            context.fillText("GAME OVER\nNEW HIGHSCORE!"
                    + "\nYOUR RANKING:  " + rank, width / 3, height / 2);
            this.inputForm.setVisible(true);
            Ui.back.setVisible(false);
        } else if (rank > 20) {
            context.fillText("GAME OVER\nYOUR SCORE  " + gameService.getScore(),
                    width / 3, height / 2);
            Ui.back.setVisible(true);
        }

    }

    /**
     * Paints the background of the game picture, views the current score and
     * the food timer if level demands it.
     *
     * @param context, GraphicalContext used to animate the game
     */
    public void paintBackground(GraphicsContext context) {
        int level = gameService.getLevel();
        context.setFill(Color.LIGHTYELLOW);
        context.fillRect(0, 0, width, height);
        if (level == 2 || level == 4) {
            context.setStroke(Color.SADDLEBROWN);
            context.setLineWidth(3);
            context.strokeRect(0, 0, width, height);
        }
        context.setFill(Color.DEEPSKYBLUE);
        context.fillText("Score: " + gameService.getScore(), Ui.blocksize / 2, Ui.blocksize);
        if (level == 3 || level == 4) {
            context.setFill(Color.ORANGERED);
            context.fillText("" + gameService.getRemainingCounter(), Ui.width / 2 * Ui.blocksize, Ui.blocksize);
        }
    }

    /**
     * Paint the food in the game picture
     *
     * @param context, GraphicalContext used to animate the game
     */
    public void paintFood(GraphicsContext context) {
        gameService.foodColor();
        Color color = Color.web(gameService.foodColor());
        context.setFill(color);
        context.fillOval(gameService.foodX() + 1, gameService.foodY() + 1, Ui.blocksize - 2, Ui.blocksize - 2);
    }

    /**
     * Paints the snake, main character in the game.
     *
     * @param context
     */
    public void paintSnake(GraphicsContext context) {
        gameService.getSnake().forEach((block) -> {
            context.setFill(Color.DARKSEAGREEN);
            context.fillRect(block.getX() * Ui.blocksize, block.getY() * Ui.blocksize, Ui.blocksize, Ui.blocksize);
        });
    }

    /**
     * Handles the input form for players name when adding new high score to the
     * memory. On execution redirects the scene back to the "main menu".
     *
     */
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
            scoreService.addScore(nickname, gameService.getScore(), gameService.getLevel());
            Ui.back.fire();
        });

        inputForm.getChildren().addAll(namelabel, inputField, submit);
        inputForm.setVisible(false);
    }

}
