/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.scenes;

import anadis.snakegame.domain.*;
import anadis.snakegame.ui.Ui;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 *
 * @author anadis
 */
public class GameScene {

    private int width;
    private int height;
    private GameService gameService;
    private AnimationTimer timer;

    public GameScene() {

        this.width = Ui.width * Ui.blocksize;
        this.height = Ui.height * Ui.blocksize;

    }

    public Pane getScene(Button back) {

        this.gameService = new GameService(back);
        GridPane root = new GridPane();
        root.setPrefSize(width, height);
        Canvas canvas = new Canvas(width, height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.getChildren().addAll(canvas, back);

        this.timer = new AnimationTimer() {
            long lastTimeInstance = 0;

            public void handle(long now) {
                if (lastTimeInstance == 0) {
                    lastTimeInstance = now;
                    gameService.timeInstance(context);
                }

                if (now - lastTimeInstance > 1000000000 / (gameService.getScore() + 5)) {
                    lastTimeInstance = now;
                    gameService.timeInstance(context);
                }

            }
        };
        timer.start();

        return root;
    }

    public GameService getService() {
        return this.gameService;
    }

    public void stopTimer() {
        if (this.timer != null) {
            this.timer.stop();
        }
    }

}
