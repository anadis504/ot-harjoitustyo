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
