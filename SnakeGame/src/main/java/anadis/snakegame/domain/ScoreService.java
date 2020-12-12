/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.dao.ScoreDao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
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
        if (generateRank(score, level) <= 20) {
            Score newScore = new Score(name, score, level,java.time.LocalDateTime.now());
//            newScore.setTimestamp(java.time.LocalDateTime.now());
            dao.add(newScore);
        }
    }

    public int generateRank(int points, int level) {
        List<Score> scores = getScoresForLevel(level);
        long rank = scores
                .stream()
                .filter(score -> score.getScore() >= points)
                .count();
        System.out.println(rank);
        return (int) rank + 1;
    }

    /**
     *
     * @param level
     * @return list of the scores in the memory
     */
    public List<String[]> getScores(int level) {

        ArrayList<String[]> scorelist = new ArrayList<>();

        for (Score score : getScoresForLevel(level)) {
            String[] scoretbl = {score.getName(), Integer.toString(score.getScore())};
            scorelist.add(scoretbl);
        }

        return scorelist;
    }

    public List<Score> getScoresForLevel(int level) {
        List<Score> scores = dao.getAll()
                .stream()
                .filter((score) -> level == score.getLevel())
                .collect(Collectors.toList());
        
        while (scores.size() > 20) {
            scores.remove(20);
        }
        for (Score s : scores) {
            System.out.println(s.getScore());
        }

        return scores;
    }
}
