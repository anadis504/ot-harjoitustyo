/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import anadis.snakegame.dao.FileScoreDao;
import anadis.snakegame.dao.ScoreDao;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author anadis
 */
public class ScoreService {

    private ScoreDao dao;

    public ScoreService(ScoreDao dao) {
        this.dao = dao;
        dao.add(new Score("bob", 12));
    }

    public void addScore(String name, int score) {
        if (dao.newHighscore(score)) {
            dao.add(new Score(name, score));
        }
    }

    public List<String> getScores() {
        VBox scorelist = new VBox();
        ArrayList<String> list = new ArrayList<>();
        List<Score> scores = dao.topTwenty();
        int rank = 1;
        if (scores.size() == 0) {
            scorelist.getChildren().add(new Label("No hightscores yet"));
            list.add("No hightscores yet");
        } else {
            for (Score score : scores) {
                list.add(String.format("%-3d", rank) + String.format("%-20s", score.getName()) + " : "+ String.format("%12d", score.getScore()));
                StringBuilder sb = new StringBuilder();
                if (rank < 10) {
                    sb.append("  ").append(rank++).append(" :   ");
                } else {
                    sb.append(rank++).append(" :   ");
                }
                sb.append(score.getName());
                for (int i = 0; i < 16 - score.getName().length(); i++) {
                    sb.append(" ");
                }
                sb.append(":   ");
                scorelist.getChildren().add(new Label(sb.toString() + score.getScore()));
            }
        }
        return list;
    }

}
