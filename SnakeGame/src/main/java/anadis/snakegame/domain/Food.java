/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.ui.Ui;
import java.util.Random;

/**
 *
 * @author anadis
 */
public class Food extends Block {

    private String color;
    private Random random;
    private String[] colors = {"orange","pink","blue","grey","peru","tomato",
        "lightsteelblue","firebrick","skyblue","deeppink","steelblue",
        "palevioletred","olive","lightcoral","goldenrod","dodgerblue"};

    public Food(int x, int y) {
        super(x, y);
        this.random = new Random();
        this.color = colors[random.nextInt(colors.length)];
    }

    public String getColor() {

        return this.color;
    }

    public void relocate() {
        super.setX(random.nextInt(Ui.width));
        super.setY(random.nextInt(Ui.height));
        this.color = colors[random.nextInt(colors.length)];
    }
    
    public String[] getColors() {
        return this.colors;
    }
}
