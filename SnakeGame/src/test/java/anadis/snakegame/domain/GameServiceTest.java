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
public class GameServiceTest {

    private GameService gameService;

    public GameServiceTest() {

    }

    @Before
    public void setUp() {
        this.gameService = new GameService(1);
        int x = 4;
        int y = 4;
        for (Block part : gameService.getSnake()) {
            part.setX(x--);
            part.setY(y);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void oneGameunitMovesTheWholeSnakeRightByDefault() {
        gameService.gameUnit();
        int x = 5;
        for (Block part : gameService.getSnake()) {
            assertEquals(x--, part.getX());
            assertEquals(4, part.getY());
        }
    }

    @Test
    public void snakeTurnsWhenDirectionIsChangedAndTheWholeSnakeMoves() {
        gameService.setDirection(Direction.DOWN);
        gameService.gameUnit();
        assertEquals(4, gameService.getSnake().get(0).getX());
        assertEquals(4, gameService.getSnake().get(1).getX());
        assertEquals(5, gameService.getSnake().get(0).getY());
        assertEquals(4, gameService.getSnake().get(1).getY());
    }

    @Test
    public void whenSnakeHeadHitsFoodGameUnitCallsEatAndSnakeGrowsAndFoodRelocates() {
        gameService.getFood().setX(5);
        gameService.getFood().setY(4);
        gameService.gameUnit();
        assertEquals(4, gameService.getSnake().size());
        assertFalse(gameService.getFood().getX() == 5 && gameService.getFood().getY() == 4);
        assertFalse(gameService.getScore() == 0);
    }
    
    @Test
    public void whenSnakeTriesGoingBackwardsItHitsItsOwnBodyAndGameOver() {
        gameService.setDirection(Direction.LEFT);
        gameService.gameUnit();
        assertTrue(gameService.getGameOver());
    }
}
