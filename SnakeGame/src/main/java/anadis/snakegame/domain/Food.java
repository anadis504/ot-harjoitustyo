/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.ui.Ui;
import java.util.Random;

/**
 * Class that represents food for the snake inherits class Block. Inherits class
 * Block. Contains property "color" and a random number generator for relocation
 * and color changing.
 *
 *
 * @author anadis
 */
public class Food extends Block {

    private String color;
    private Random random;
    private String[] colors = {"orange", "pink", "blue", "grey", "peru", "tomato",
        "lightsteelblue", "firebrick", "skyblue", "deeppink", "steelblue",
        "palevioletred", "olive", "lightcoral", "goldenrod", "dodgerblue"};

    /**
     * Initial with given X and Y coordinates and random color
     *
     * @param x coordinate of X
     * @param y coordinate of Y
     */
    public Food(int x, int y) {
        super(x, y);
        this.random = new Random();
        this.color = colors[random.nextInt(colors.length)];
    }

    /**
     *
     * @return color of the food
     */
    public String getColor() {

        return this.color;
    }

    /**
     * uses random number generator to reset coordinates and reset color
     */
    public void relocate() {
        super.setX(random.nextInt(Ui.width));
        super.setY(random.nextInt(Ui.height));
        this.color = colors[random.nextInt(colors.length)];
    }

    /**
     *
     * @return array of all the colors
     */
    public String[] getColors() {
        return this.colors;
    }
}
