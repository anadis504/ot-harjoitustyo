/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.domain.ScoreService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Class for viewing the list of top twenty scores in gui
 * 
 */
public class ScoreScene {

    private ScoreService scoreService;

    /**
     * Receives ScoreService instance in the constructor 
     * 
     * @param service
     */
    public ScoreScene(ScoreService service) {
        this.scoreService = service;
    }

    /**
     *
     * @param level
     * @return Scene, the scene created to show the list of top twenty scores of
     * given level
     */
    public Scene getScene(int level) {

        BorderPane rootPane = new BorderPane();
        GridPane pane = new GridPane();

        Label tittle = new Label("Top Twenty Scores for level " + level);

        pane.setAlignment(Pos.TOP_CENTER);
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.add(tittle, 1, 0);
        int i = 2;
        for (String[] score : scoreService.getScores(level)) {
            pane.add(new Label(Integer.toString(i - 1) + " "), 0, i);
            pane.add(new Label(score[0]), 1, i);
            pane.add(new Label("  :  "), 2, i);
            pane.add(new Label(score[1]), 3, i);
            i++;
        }

        Button back = new Button("back to menu");
        back.setOnAction(e -> {
            Ui.back.fire();
        });
        pane.add(back, 3, 0);

        rootPane.setCenter(pane);
        Scene scoreScene = new Scene(rootPane, Ui.width * Ui.blocksize, Ui.height * Ui.blocksize);
        return scoreScene;
    }
}
