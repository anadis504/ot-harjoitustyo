/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

/**
 * Class that represents the score of the ended game to be added to database
 * 
 * @author anadis
 */
public class Score implements Comparable<Score> {
    
    private String nickname;
    private int score;
    
    /**
     *
     * @param name String name
     * @param score int amount of points
     */
    public Score(String name, int score) {
        this.nickname = name;
        this.score = score;
    }
    
    /**
     *
     * @return String name
     */
    public String getName() {
        return this.nickname;
    }
    
    /**
     *
     * @return int amount of points
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Method compares two scores by the amount of points
     * 
     * @param Score other score to be compared to
     * @return int point-difference of the two scores
     */
    @Override
    public int compareTo(Score other) {
        return other.getScore() - this.score;
    }

}
