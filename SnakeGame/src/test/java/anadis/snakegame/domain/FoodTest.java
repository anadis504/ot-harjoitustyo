/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.scenes.GameScene;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        System.out.println(food.getColor().toString());
        assertTrue(food.getColor().toString().matches("0x(.*)"));
    }

    @Test
    public void foodRelocatedSuccessfully() {
        Food newFood = new Food(0, 0);
        assertTrue(newFood.getColor().toString().matches("0x(.*)"));
        food.relocate(20, 20);
        assertTrue(food.getColor().toString().matches("0x(.*)"));
        assertFalse(food.equals(newFood));
    }
}
