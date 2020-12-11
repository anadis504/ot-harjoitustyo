/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.domain.Direction;
import anadis.snakegame.domain.ScoreService;
import java.io.FileInputStream;
import java.util.Properties;
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
    private ScoreScene viewScores;
    private GameScene gameScene;
    private ScoreService scoreService;
    public static Button back;
    private Scene mainSelection;

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
            primaryStage.setScene(viewScores.getScene());
        });

        newGame.setOnAction(e -> {
            primaryStage.setScene(gameScene.getScene());
        });

//        Scene scene = new Scene(borderPane, width * blocksize, height * blocksize);

        back.setOnAction(e -> {
            primaryStage.setScene(mainSelection);
        });

        primaryStage.setScene(mainSelection);
        primaryStage.setTitle("SNAKE GAME");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
