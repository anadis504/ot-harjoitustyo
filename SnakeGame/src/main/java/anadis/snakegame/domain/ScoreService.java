/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.dao.ScoreDao;
import anadis.snakegame.domain.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addScore(String name, int score, int level) {
        if (isTopTwenty(score)) {
            dao.add(new Score(name, score, level));
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
    public List<String[]> getScores(int level) {
        List<Score> scores = dao.topTwenty()
                    .stream().
                    filter((score) -> level==score.getLevel())
                    .collect(Collectors.toList());   
        
        System.out.println(scores.isEmpty());
        ArrayList<String[]> scorelist = new ArrayList<>();
//        if (scores.isEmpty()) {
//            System.out.println("shits empty");
//            String[] tbl = {"No hightscores yet"};
//            scorelist.add(tbl);
//        } else {
//            
            for (Score score : scores) {
                String[] scoretbl = {score.getName(), Integer.toString(score.getScore())};
                scorelist.add(scoretbl);
            }
        
        return scorelist;
    }
}
