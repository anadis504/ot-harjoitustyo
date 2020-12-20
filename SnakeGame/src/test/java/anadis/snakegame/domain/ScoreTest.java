/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import java.time.LocalDateTime;
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
public class ScoreTest {
    
    public ScoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void comparingEqualScoresFromSameLevelComparesAccordingToDate() {
        String name = "bob";
        int score = 12;
        int level = 1;
        LocalDateTime time1 = java.time.LocalDateTime.now();
        Score score1 = new Score(name, score, level, time1);
        LocalDateTime time2 = java.time.LocalDateTime.now();
        Score score2 = new Score(name,score, level, time2);
        assertEquals(time1.compareTo(time2), score1.compareTo(score2));        
    }
}
