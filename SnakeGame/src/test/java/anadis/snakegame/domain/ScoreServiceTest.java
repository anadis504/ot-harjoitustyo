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

    @Test
    public void testing() {

        
    }
}
