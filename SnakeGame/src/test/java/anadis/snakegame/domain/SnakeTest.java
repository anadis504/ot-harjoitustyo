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
public class SnakeTest {

    private Snake snake;

    public SnakeTest() {
    }

    @Before
    public void setUp() {
        this.snake = new Snake(0, 5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initialSnakeIsOfSizeThree() {
        assertEquals(3, snake.getSnake().size());
    }

    @Test
    public void snakeInCorrectCoordinatesWhenInitiated() {
        for (Block block : snake.getSnake()) {
            assertEquals(0, block.getX());
            assertEquals(5, block.getY());
        }
    }

    @Test
    public void snakeMovesCorrectly() {
        snake.move(Direction.RIGHT);
        assertEquals(1, snake.getSnake().get(0).getX());
        assertFalse(snake.getSnake().get(0).equals(snake.getSnake().get(1)));
    }

    @Test
    public void snakeGrowsCorrectly() {
        snake.grow();
        assertEquals(4, snake.getSnake().size());
    }

    @Test
    public void snakesCoordinatesCorrectWhenSnakeMovesOutsideOutOfFrameAndBorderIsUsed() {
        snake.move(Direction.LEFT);
        assertTrue(snake.hitFrame());
        snake.border();
        assertFalse(snake.hitFrame());
        assertEquals(19, snake.getSnake().get(0).getX());
        snake.move(Direction.RIGHT);
        assertTrue(snake.hitFrame());
        snake.border();
        assertFalse(snake.hitFrame());
        assertEquals(0, snake.getSnake().get(0).getX());
        snake.getSnake().get(0).setY(0);
        snake.move(Direction.UP);
        assertTrue(snake.hitFrame());
        snake.border();
        assertFalse(snake.hitFrame());
        assertEquals(19, snake.getSnake().get(0).getY());
        snake.move(Direction.DOWN);
        assertTrue(snake.hitFrame());
        snake.border();
        assertFalse(snake.hitFrame());
        assertEquals(0, snake.getSnake().get(0).getY());
    }

    @Test
    public void bodyCrashWhenSnakeTurnsOppositeDirection() {
        snake.move(Direction.DOWN);
        assertFalse(snake.bodyCrash());
        snake.move(Direction.UP);
        assertTrue(snake.bodyCrash());
    }
}
