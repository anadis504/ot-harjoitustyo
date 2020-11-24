/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.scenes;

import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.dao.ScoreDao;
import anadis.snakegame.domain.Score;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author anadis
 */
public class ScoreSceneTest {
    
    ScoreScene scene;
    ScoreDao dao;
    public ScoreSceneTest() {
    }
    
    @Before
    public void setUp() {
        this.dao = mock(FileScoreDao.class);
        this.scene = new ScoreScene(dao);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testingMockito() {
        List<Score> scores = new ArrayList<>();
        Score score = new Score("bob", 20);
        when(dao.topTwenty()).thenReturn(scores);
        
    }
}
