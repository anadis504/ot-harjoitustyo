/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.dao.ScoreDao;
import java.time.LocalDateTime;
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
     * Method for adding new score data to permanent memory. Generates timestamp
     * automatically
     *
     * @param name of player
     * @param score amount of points
     * @param level
     */
    public void addScore(String name, int score, int level) {
        if (generateRank(score, level) <= 20) {
            if (name.equals("")) {
                name = "Anonymous";
            }
            if (name.length() > 20) {
                name = name.substring(0, 20) + "...";
            }
            LocalDateTime timestamp = java.time.LocalDateTime.now();

            dao.add(name, score, level, timestamp);
        }
    }

    /**
     * Calculates the rank of the new score in the existing score list.
     *
     * @param points
     * @param level
     *
     * @return int rank
     */
    public int generateRank(int points, int level) {
        List<Score> scores = getScoresForLevel(level);
        long rank = scores
                .stream()
                .filter(score -> score.getScore() >= points)
                .count();
        return (int) rank + 1;
    }

    /**
     * Creates a list of String tables for presentation of the score lists in
     * gui
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

    /**
     * Collects the twenty highest scores of given level from the existing score
     * list
     *
     * @param level
     * @return
     */
    public List<Score> getScoresForLevel(int level) {
        List<Score> scores = dao.getAll()
                .stream()
                .filter((score) -> level == score.getLevel())
                .collect(Collectors.toList());

        while (scores.size() > 20) {
            scores.remove(20);
        }

        return scores;
    }
}
