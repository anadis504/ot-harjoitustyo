/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.scenes;

import anadis.snakegame.dao.ScoreDao;
import anadis.snakegame.domain.Score;
import anadis.snakegame.domain.ScoreService;
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
    
    public Parent getScene(Button back) {
        
        GridPane pane = new GridPane();
        
        Label tittle = new Label("Top Twenty Scores");
        
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        pane.add(tittle, 0, 0);
        
//        if (dao.topTwenty().size() == 0) {
//            pane.add(new Label("no highscores yet"), 0, 2);
//        }
        pane.add(scoreService.getScores(), 0, 2);
        pane.add(back, 2, 0);
        
        return pane;
    }
}
