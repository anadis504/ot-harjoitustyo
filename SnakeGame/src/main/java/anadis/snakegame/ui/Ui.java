/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.scenes.ScoreScene;
import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.dao.ScoreDao;
import anadis.snakegame.domain.Direction;
import anadis.snakegame.domain.GameService;
import anadis.snakegame.domain.Score;
import anadis.snakegame.domain.ScoreService;
import anadis.snakegame.scenes.GameScene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author anadis
 */
public class Ui extends Application {

    public static int width = 20;
    public static int height = 20;
    public static int blocksize = 25;
    private GameService gameService;
    private ScoreScene viewScores;
    private GameScene gameScene;
    private ScoreService scoreService;

    public Ui() {
        this.scoreService = new ScoreService(new FileScoreDao("scores.txt"));
        this.viewScores = new ScoreScene(scoreService);
        this.gameService = new GameService();
        this.gameScene = new GameScene(gameService);

    }

    @Override
    public void init() throws Exception {
    }

    @Override
    public void start(Stage window) {

        BorderPane borderPane = new BorderPane();

        HBox selection = new HBox();
        selection.setPadding(new Insets(20, 20, 20, 20));
        selection.setSpacing(10);

        Button newGame = new Button("New game");
        Button scores = new Button("Scores");

        selection.getChildren().addAll(newGame, scores);
        borderPane.setCenter(selection);

        scores.setOnAction((event) -> {
            Button back = new Button("back to menu");
            back.setOnAction(e -> {
                borderPane.setCenter(selection);
            });
            borderPane.setCenter(viewScores.getScene(back));

        });

        newGame.setOnAction(e -> {
            borderPane.setCenter(gameScene.getScene());
        });

        Scene scene = new Scene(borderPane, width * blocksize, height * blocksize);

        scene.setOnKeyPressed(event -> {
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
        window.setScene(scene);
        window.setTitle("SNAKE GAME");
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
