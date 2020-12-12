/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.domain.ScoreService;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private ScoreScene viewScores;
    private GameScene gameScene;
    private ScoreService scoreService;
    public static Button back;
    private Scene mainSelection;
    private Scene LevelSelection;
    private int levels;
    private HashMap<Integer, Button> levelButtons;
    private int routing;
//    private HashMap<Integer, Button> scoreLevels;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String scoreFile = properties.getProperty("scoreFile");
        FileScoreDao scoreDao = new FileScoreDao(scoreFile);
        this.scoreService = new ScoreService(scoreDao);

        this.viewScores = new ScoreScene(scoreService);
        this.gameScene = new GameScene(scoreService);
        this.back = new Button("back to menu");
        this.levels = 2;

        this.routing = 0;
        this.levelButtons = new HashMap<>();
        for (int i = 1; i <= levels; i++) {
            levelButtons.put(i, new Button("level " + i));
        }

    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();

        HBox selection = new HBox();
        selection.setPadding(new Insets(20, 20, 20, 20));
        selection.setSpacing(10);

        Button newGame = new Button("New game");
        Button scores = new Button("Scores");
        Button backFromScores = new Button("back to menu");

        selection.getChildren().addAll(newGame, scores);
        borderPane.setCenter(selection);

        mainSelection = new Scene(borderPane, width * blocksize, height * blocksize);
        backFromScores.setOnAction(e -> {
            primaryStage.setScene(mainSelection);
        });

        scores.setOnAction((event) -> {
            routing = 2;
            primaryStage.setScene(LevelSelection);
        });

        newGame.setOnAction(e -> {
            routing = 1;
            primaryStage.setScene(LevelSelection);
        });

        back.setOnAction(e -> {
            primaryStage.setScene(mainSelection);
        });

        // level selection scene
        GridPane levelPane = new GridPane();
        levelPane.setAlignment(Pos.TOP_LEFT);
        levelPane.setVgap(width);
        levelPane.setHgap(height);
        levelButtons.forEach((level, button) -> {
            button.setOnAction(e -> {
                if (routing == 1) {
                    primaryStage.setScene(gameScene.getScene(level));
                } else if (routing == 2) {
                    primaryStage.setScene(viewScores.getScene(level));
                }
            });
            levelPane.add(button, level, 1);
        });
        Button backButton = new Button("back");
        levelPane.add(backButton, 0, 0);
        backButton.setOnAction(a -> {
            primaryStage.setScene(mainSelection);
        });
        LevelSelection = new Scene(levelPane, 500, 500);

        primaryStage.setScene(mainSelection);
        primaryStage.setTitle("SNAKE GAME");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
