/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author anadis
 */
public class FoodTest {

    Food food;

    public FoodTest() {
    }

    @Before
    public void setUp() {
        this.food = new Food(0, 0);
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void foodInitedWithColor() {
//        assertTrue(food.getColor().toString().matches("0x(.*)"));
//    }
//
    @Test
    public void foodRelocatedSuccessfully() {
        Food newFood = new Food(0, 0);
        food.relocate();
        assertFalse(food.equals(newFood));
    }
    
    @Test
    public void allColorsAreLigitColors() {
        for (String color : food.getColors()) {
            assertEquals(Color.class, Color.web(color).getClass());
        }
    }
}
