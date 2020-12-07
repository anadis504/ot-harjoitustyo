/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.service.ScoreService;
import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.dao.ScoreDao;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author anadis
 */
public class ScoreServiceTest {

    ScoreDao dao;
    ScoreService scoreService;

    public ScoreServiceTest() {
    }

    @Before
    public void setUp() {
        this.dao = mock(FileScoreDao.class);
        this.scoreService = new ScoreService(dao);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void whenDaoIsEmptyServiceReturnAppropriateMessage() {
        when(dao.topTwenty()).thenReturn(new ArrayList<Score>());
        assertTrue(scoreService.getScores().get(0)[0].equals("No hightscores yet"));
        verify(dao, times(1)).topTwenty();
    }

    @Test
    public void whenDaoIsEmptyAnyNewScoreIsAddedToDao() {
        when(dao.topTwenty()).thenReturn(new ArrayList<Score>());
        scoreService.addScore("bob", 12);
        verify(dao, times(1)).topTwenty();
        verify(dao).add(anyObject());
    }

    @Test
    public void tooLowScoreIsNotAddedToDao() {
        ArrayList<Score> scorelist = new ArrayList<>();
        for (int i = 2; i <= 22; i++) {
            scorelist.add(new Score("bob" + i, i));
        }
        when(dao.topTwenty()).thenReturn(scorelist);
        scoreService.addScore("bob", 1);
        verify(dao, times(0)).add(anyObject());
    }
    
    
}
