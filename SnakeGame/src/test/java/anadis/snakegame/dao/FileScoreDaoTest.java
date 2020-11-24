/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.dao;

import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.domain.Score;
import java.nio.file.Path;
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
public class FileScoreDaoTest {

    FileScoreDao scores;

    public FileScoreDaoTest() {
    }

    @Before
    public void setUp() {
        this.scores = new FileScoreDao("testfilescores.txt");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testfileExistsAndNotEmptyAfterFirstAddition() {
        scores.add(new Score("bob", 12));
        assertTrue(!scores.topTwenty().isEmpty());
    }

    @Test
    public void highestScoreComesFirst() {
        int highest = scores.topTwenty().get(0).getScore();
        scores.add(new Score("alice", ++highest));
        assertEquals("alice", scores.topTwenty().get(0).getName());
        assertEquals(highest, scores.topTwenty().get(0).getScore());
    }

    @Test
    public void checkNewHeghtscoreIsSeen() {
        int newScore = scores.topTwenty().get(10).getScore();
        Score newcomer = new Score("newcomer", newScore);
        scores.add(newcomer);
        assertTrue(scores.topTwenty().contains(newcomer));
    }

    @Test
    public void tooLowScoreNotAddedToTheScoreList() {
        int newScore = scores.topTwenty().get(19).getScore() - 1;
        Score notEnough = new Score("LOOSER", newScore);
        assertFalse(scores.topTwenty().contains(notEnough));
    }
}
