/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author anadis
 */
public class Food extends Block {

    private int color;
    private Random random;
    
    public Food(int x, int y) {
        super(x, y);
        this.random = new Random();
        this.color = random.nextInt(6);
    }
    
    public Color getColor() {
        switch(color) {
            case 0: return Color.PINK;
            case 1: return Color.VIOLET;
            case 2: return Color.BLUE;
            case 3: return Color.BROWN;
            case 4: return Color.TOMATO;
            default: return Color.ORANGE;
        }
    }
    
    public void relocate(int width, int height) {
        super.setX(random.nextInt(width));
        super.setY(random.nextInt(height));
        this.color = random.nextInt(6);
    }
}
