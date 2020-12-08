/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import anadis.snakegame.service.ScoreService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author anadis
 */
public class ScoreScene {

    private ScoreService scoreService;

    public ScoreScene(ScoreService service) {
        this.scoreService = service;
    }

    public Parent getScene(Button backButton) {

        GridPane pane = new GridPane();

        Label tittle = new Label("Top Twenty Scores");

        pane.setAlignment(Pos.TOP_CENTER);
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.add(tittle, 0, 0);
        int i = 2;
        for (String[] score : scoreService.getScores()) {
            pane.add(new Label(Integer.toString(i - 1)), 0, i);
            pane.add(new Label(score[0]), 1, i);
            pane.add(new Label(" : "), 2, i);
            pane.add(new Label(score[1]), 3, i);
            i++;
        }
        Ui.back.setVisible(true);
        pane.add(backButton, 2, 0);

        return pane;
    }
}
