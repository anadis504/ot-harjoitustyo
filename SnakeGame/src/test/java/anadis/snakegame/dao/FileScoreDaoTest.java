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
import java.io.FileWriter;
import java.time.LocalDateTime;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author anadis
 */
public class FileScoreDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    FileScoreDao scoreDao;
    File testFile;

    public FileScoreDaoTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.testFile = testFolder.newFile("testfile_scores.txt");
        LocalDateTime timestamp = java.time.LocalDateTime.now();

        try (FileWriter writer = new FileWriter(testFile.getAbsoluteFile())) {
            writer.write("bob;12;1;" + timestamp);
        }

        this.scoreDao = new FileScoreDao(testFile.getAbsolutePath());
    }

    @Test
    public void testfileExistsAndNotEmpty() {
        assertFalse(scoreDao.getAll().isEmpty());
    }

    @Test
    public void highestScoreComesFirst() {
        scoreDao.add(new Score("alice", 13, 1, java.time.LocalDateTime.now()));
        assertEquals("alice", scoreDao.getAll().get(0).getName());
        assertEquals(13, scoreDao.getAll().get(0).getScore());
    }

    @Test
    public void lowestLevelComesFirst() {
        scoreDao.add(new Score("bob", 22, 2, java.time.LocalDateTime.now()));
        assertEquals(1, scoreDao.getAll().get(0).getLevel());
        assertEquals(22, scoreDao.getAll().get(1).getScore());
    }

    @Test
    public void scoresAreSortedByTimestamp() {
        scoreDao.add(new Score("alice", 12, 1, java.time.LocalDateTime.now()));
        assertEquals("alice", scoreDao.getAll().get(1).getName());
        assertEquals("bob", scoreDao.getAll().get(0).getName());
    }

}
