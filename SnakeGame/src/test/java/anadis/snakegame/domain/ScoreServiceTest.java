/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.dao.ScoreDao;
import org.junit.After;
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

//    @Test
//    public void testingCallAddingMethod() {
//        when(dao.newHighscore(anyInt())).thenReturn(true);
//        scoreService.addScore("bob", 12);
//        verify(dao, times(1)).newHighscore(12);
//    }
//    
//    @Test
//    public void serviceDoesNotAddWhenScoreTooLow() {
//        when(dao.newHighscore(anyInt())).thenReturn(false);
//        scoreService.addScore("bob", 11);
//        verify(dao, times(0)).add(anyObject());
//    }
    
//    @Test
//    public void returnsLabelWhenAskedForScores() {
//        List<Score> scores = new ArrayList<>();
//        scores.add(new Score("bob", 12));
//        when(dao.topTwenty()).thenReturn(scores);
//        scoreService.getScores();
//        verify(dao, times(1)).topTwenty();
//    }
}
