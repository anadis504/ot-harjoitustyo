/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

/**
 *
 * @author anadis
 */
public class Score implements Comparable<Score> {
    
    private String nickname;
    private int score;
    
    public Score(String name, int score) {
        this.nickname = name;
        this.score = score;
    }
    
    public String getName() {
        return this.nickname;
    }
    
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(Score other) {
        return other.getScore() - this.score;
    }

}
