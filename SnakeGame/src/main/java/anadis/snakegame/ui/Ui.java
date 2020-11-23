/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.scenes.ScoreScene;
import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.dao.ScoreDao;
import anadis.snakegame.domain.Score;
import anadis.snakegame.scenes.GameScene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author anadis
 */
public class Ui extends Application {

    private ScoreDao dao;

    @Override
    public void init() throws Exception {
        this.dao = new FileScoreDao("scores.txt");
        dao.add(new Score("bob", 12));
    }

    @Override
    public void start(Stage window) {

        ScoreScene viewScores = new ScoreScene(dao);
        GameScene playSnake = new GameScene(20, 20);

        BorderPane pane = new BorderPane();

        HBox selection = new HBox();
        selection.setPadding(new Insets(20, 20, 20, 20));
        selection.setSpacing(10);

        Button newGame = new Button("New game");
        Button scores = new Button("Scores");

        selection.getChildren().addAll(newGame, scores);
        pane.setCenter(selection);

        scores.setOnAction((event) -> pane.setCenter(viewScores.getScene()));
        newGame.setOnAction(e -> window.setScene(playSnake.getScene()));

        Scene scene = new Scene(pane, 400, 300);
        window.setScene(scene);
        window.setTitle("SNAKE GAME");
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
