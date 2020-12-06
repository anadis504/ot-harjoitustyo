/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.dao.ScoreDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anadis
 */
public class ScoreService {

    private ScoreDao dao;

    public ScoreService(ScoreDao dao) {
        this.dao = dao;
    }

    public void addScore(String name, int score) {
        if (isTopTwenty(score)) {
            dao.add(new Score(name, score));
        }
    }

    public boolean isTopTwenty(int points) {
        if (dao.topTwenty().size() < 20 || points > dao.topTwenty().get(19).getScore()) {
            return true;
        }
        return false;
    }
    
    public List<String[]> getScores() {
        ArrayList<String[]> scorelist = new ArrayList<>();
        List<Score> scores = dao.topTwenty();
        int rank = 1;
        if (scores.isEmpty()) {
            String[] tbl = {"No hightscores yet"};
            scorelist.add(tbl);
        } else {
            for (Score score : scores) {
                String[] scoretbl = {score.getName(), Integer.toString(score.getScore())};
                scorelist.add(scoretbl);
            }
        }
        return scorelist;
    }

}
