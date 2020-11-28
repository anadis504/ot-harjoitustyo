/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.scenes;

import anadis.snakegame.domain.*;
import anadis.snakegame.ui.Ui;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;

/**
 *
 * @author anadis
 */
public class GameScene {

    private int width;
    private int height;
    private GameService gameService;

    public GameScene(GameService gameService) {
        this.gameService = gameService;
        this.width = Ui.width * Ui.blocksize;
        this.height = Ui.height * Ui.blocksize;
    }

    public Pane getScene() {

        Pane root = new Pane();
        root.setPrefSize(width, height);
        Canvas canvas = new Canvas(width, height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        new AnimationTimer() {
            long lastTimeInstance = 0;

            public void handle(long now) {
                if (lastTimeInstance == 0) {
                    lastTimeInstance = now;
                    gameService.timeInstance(context);
                    return;
                }

                if (now - lastTimeInstance > 1000000000 / (gameService.getScore() + 5)) {
                    lastTimeInstance = now;
                    gameService.timeInstance(context);
                }
            }
        }.start();

        return root;

    }
}
