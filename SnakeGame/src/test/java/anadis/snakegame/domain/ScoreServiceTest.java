/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.dao.ScoreDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
    Score score;

    public ScoreServiceTest() {
    }

    @Before
    public void setUp() {
        this.dao = mock(FileScoreDao.class);
        this.scoreService = new ScoreService(dao);
        this.score = new Score("bob", 12, 1, java.time.LocalDateTime.now());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void whenDaoIsEmptyAnyNewScoreIsAddedToDao() {
        when(dao.getAll()).thenReturn(new ArrayList<>());
        scoreService.addScore("bob", 12, 1);
        verify(dao, times(1)).getAll();
        verify(dao).add(eq("bob"), eq(12), eq(1), anyObject());
    }

    @Test
    public void tooLowScoreIsNotAddedToDao() {
        ArrayList<Score> scorelist = new ArrayList<>();
        for (int i = 2; i <= 22; i++) {
            scorelist.add(new Score("bob" + i, i, 1, null));
        }
        when(dao.getAll()).thenReturn(scorelist);
        scoreService.addScore("bob", 1, 1);
        verify(dao, times(0)).add(anyString(), anyInt(), anyInt(), anyObject());
    }

    @Test
    public void scoresAreSortedByGameLevels() {
        ArrayList<Score> scorelist = new ArrayList<>();
        scorelist.add(score);
        scorelist.add(new Score("bob", 21, 2, java.time.LocalDateTime.now()));
        when(dao.getAll()).thenReturn(scorelist);
        assertEquals(1, scoreService.getScores(1).size());
        assertEquals("12", scoreService.getScores(1).get(0)[1]);
        assertEquals("21", scoreService.getScores(2).get(0)[1]);
    }

    @Test
    public void scoresAreInitializedWithTimestampAndSortedCorrectly() {
        ArrayList<Score> scorelist = new ArrayList<>();
        scorelist.add(score);
        scorelist.add(new Score("alice", 12, 1, java.time.LocalDateTime.now()));
        when(dao.getAll()).thenReturn(scorelist);
        assertEquals(score, scoreService.getScoresForLevel(1).get(0));
        assertEquals("alice", scoreService.getScores(1).get(1)[0]);
    }

    @Test
    public void askingForRankGiveTheRightEstimate() {
        ArrayList<Score> scorelist = new ArrayList<>();
        scorelist.add(score);
        when(dao.getAll()).thenReturn(scorelist);
        assertEquals(1, scoreService.generateRank(13, 1));
        assertEquals(2, scoreService.generateRank(12, 1));
    }
    
    @Test
    public void inputtingEmptyNameSavesPlayerAsAnonymous() {
        scoreService.addScore("", 1, 1);
        verify(dao).add(eq("Anonymous"), anyInt(), anyInt(), anyObject());
    }
    
    @Test
    public void tooLongNicknameIsShortened() {
        scoreService.addScore("asdfghjklpoiuytrnmvbdkfjghtud", 1, 3);
        verify(dao).add(eq("asdfghjklpoiuytrnmvb..."), anyInt(), anyInt(), anyObject());
    }
}
