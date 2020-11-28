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
import java.io.File;

/**
 *
 * @author anadis
 */
public class FileScoreDaoTest {

    FileScoreDao scores;
    File test = new File("testfilescores.txt");

    public FileScoreDaoTest() {
    }

    @Before
    public void setUp() {
        this.scores = new FileScoreDao("testfilescores.txt");
    }

    @After
    public void tearDown() {
        test.deleteOnExit();
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
        Score newcomer = new Score("newcomer", 10);
        scores.add(newcomer);
        assertTrue(scores.topTwenty().contains(newcomer));
    }

    @Test
    public void tooLowScoreNotAddedToTheScoreList() {
        for (int i = 0; i < 20; i++) {
            scores.add(new Score("alice", i));
        }
        int newScore = scores.topTwenty().get(19).getScore() - 1;
        Score notEnough = new Score("LOOSER", newScore);
        assertFalse(scores.topTwenty().contains(notEnough));
    }

    @Test
    public void testingNewHighScore() {
        for (int i = 0; i < 20; i++) {
            scores.add(new Score("alice", i));
        }
        int newScore = scores.topTwenty().get(18).getScore();
        assertTrue(scores.newHighscore(newScore));
    }
}
