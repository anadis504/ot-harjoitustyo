/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.scenes;

import anadis.snakegame.domain.ScoreService;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author anadis
 */
public class NewScoreScene {
    
    private ScoreService scoreService;
    
    public NewScoreScene(ScoreService service) {
        this.scoreService = service;
    }
    
    public VBox getInputForm(int score) {
        VBox inputForm = new VBox();
        Label namelabel = new Label("your name");
        TextField inputField = new TextField();
        Button submit = new Button("save");
        
        submit.setOnMouseClicked(e -> {
            String nickname = inputField.getText();
            scoreService.addScore(nickname, score); 
        });
        
        return inputForm;
    }
}
