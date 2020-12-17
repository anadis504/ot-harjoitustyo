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
        scoreDao.add(new Score("bob", 12, 1, null));
        assertFalse(scoreDao.getAll().isEmpty());
    }

    @Test
    public void highestScoreComesFirst() {
        scoreDao.add(new Score("bob", 12, 1, null));
        scoreDao.add(new Score("alice", 13, 1, null));
        assertEquals("alice", scoreDao.getAll().get(0).getName());
        assertEquals(13, scoreDao.getAll().get(0).getScore());
    }
    
}
