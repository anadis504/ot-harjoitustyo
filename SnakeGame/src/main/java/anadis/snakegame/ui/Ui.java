/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author anadis
 */
public class Ui extends Application {

    @Override
    public void init() throws Exception {
      // ...
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
