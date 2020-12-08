/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.dao;

import anadis.snakegame.domain.Score;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;

/**
 *
 * @author anadis
 */
public class FileScoreDaoTest {

    FileScoreDao scoreDao;
    File test = new File("testfilescoreDao.txt");

    public FileScoreDaoTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.scoreDao = new FileScoreDao("testfilescoreDao.txt");
    }

    @After
    public void tearDown() {
        test.deleteOnExit();
    }

    @Test
    public void testfileExistsAndNotEmptyAfterFirstAddition() {
        scoreDao.add(new Score("bob", 12));
        assertFalse(scoreDao.topTwenty().isEmpty());
    }

    @Test
    public void highestScoreComesFirst() {
        int highest = scoreDao.topTwenty().get(0).getScore();
        scoreDao.add(new Score("alice", ++highest));
        assertEquals("alice", scoreDao.topTwenty().get(0).getName());
        assertEquals(highest, scoreDao.topTwenty().get(0).getScore());
    }
    
    @Test
    public void scorefileContainOnlyHighestTwentyscoreDao() {
        for (int i = 1; i <= 22; i++) {
            scoreDao.add(new Score("bob"+i, i));
        }
        assertEquals(20, scoreDao.topTwenty().size());
        Score alice = new Score("alice", 1);
        scoreDao.add(alice);
        assertFalse(scoreDao.topTwenty().contains(alice));
    }
}
