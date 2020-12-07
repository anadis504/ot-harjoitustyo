/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.service;

import anadis.snakegame.dao.ScoreDao;
import anadis.snakegame.domain.Score;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides an interface between data storing and user interface. 
 * Manages data storing logic.
 * 
 * @author anadis
 */
public class ScoreService {

    private ScoreDao dao;

    /**
     * Receives ScoreDao object for storing data in constructor.
     * 
     * @param dao ScoreDao
     */
    public ScoreService(ScoreDao dao) {
        this.dao = dao;
    }

    /**
     * Method for adding new "name : score" pair to permanent memory.
     * 
     * @param name of player
     * @param score amount of points
     */
    public void addScore(String name, int score) {
        if (isTopTwenty(score)) {
            dao.add(new Score(name, score));
        }
    }

    /**
     * Checks if the amount of points belongs to the top twenty scores in memory
     *
     * @param points
     * @return true if the points belong to the top twenty, otherwise false
     */
    public boolean isTopTwenty(int points) {
        if (dao.topTwenty().size() < 20 || points > dao.topTwenty().get(19).getScore()) {
            return true;
        }
        return false;
    }
    
    /**
     *
     * @return list of the scores in the memory
     */
    public List<String[]> getScores() {
        ArrayList<String[]> scorelist = new ArrayList<>();
        if (dao.topTwenty().isEmpty()) {
            String[] tbl = {"No hightscores yet"};
            scorelist.add(tbl);
        } else {
            for (Score score : dao.topTwenty()) {
                String[] scoretbl = {score.getName(), Integer.toString(score.getScore())};
                scorelist.add(scoretbl);
            }
        }
        return scorelist;
    }
}
