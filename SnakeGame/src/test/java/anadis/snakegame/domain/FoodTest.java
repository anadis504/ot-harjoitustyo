/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

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

    @Test
    public void foodInitedWithColor() {
        assertTrue(food.getColor().toString().matches("0x(.*)"));
    }

    @Test
    public void foodRelocatedSuccessfully() {
        Food newFood = new Food(0, 0);
        assertTrue(newFood.getColor().toString().matches("0x(.*)"));
        food.relocate();
        assertTrue(food.getColor().toString().matches("0x(.*)"));
        assertFalse(food.equals(newFood));
    }
}
