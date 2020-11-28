/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        this.snake = new Snake();
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
            assertEquals(10, block.getY());
        }
    }

    @Test
    public void snakeMovesCorrectly() {
        snake.turn(Direction.RIGHT);
        assertEquals(1, snake.getSnake().get(0).getX());
        snake.move();
        assertTrue(snake.getSnake().get(0).equals(snake.getSnake().get(1)));
    }

    @Test
    public void snakeGrowsCorrectly() {
        snake.grow();
        assertEquals(4, snake.getSnake().size());
    }

    @Test
    public void testSetCoordinationForSnakeMoveAndExpectBlockInSamePlace() {
        snake.getSnake().get(0).setX(10);
        assertEquals(10, snake.getSnake().get(0).getX());
        snake.getSnake().get(0).setY(5);
        assertEquals(5, snake.getSnake().get(0).getY());
        assertFalse(snake.getSnake().get(0).equals(snake.getSnake().get(1)));
        snake.move();
        assertTrue(snake.getSnake().get(0).equals(snake.getSnake().get(1)));
    }
    
    @Test
    public void snakesCoordinatesCorrectWhenSnakeMovesOutsideOfFrame() {
        snake.turn(Direction.LEFT);
        assertEquals(19, snake.getSnake().get(0).getX());
        snake.turn(Direction.RIGHT);
        assertEquals(0, snake.getSnake().get(0).getX());
        snake.getSnake().get(0).setY(0);
        snake.turn(Direction.UP);
        assertEquals(19, snake.getSnake().get(0).getY());
        snake.turn(Direction.DOWN);
        assertEquals(0, snake.getSnake().get(0).getY());
    }
    
    @Test
    public void bodyCrashWhenSnakeTurnsOppositeDirection() {
        snake.turn(Direction.DOWN);
        assertFalse(snake.bodyCrash());
        snake.move();
        snake.turn(Direction.UP);
        assertTrue(snake.bodyCrash());
    }
}
