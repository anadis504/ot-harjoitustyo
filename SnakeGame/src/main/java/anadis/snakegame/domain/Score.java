/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that represents the score of the ended game to be added to database
 * 
 * @author anadis
 */
public class Score implements Comparable<Score> {
    
    private String nickname;
    private int score;
    private int level;
    private LocalDateTime timestamp;
    
    /**
     *
     * @param name String name
     * @param points
     * @param level
     */
    public Score(String name, int points, int level, LocalDateTime time) {
        this.nickname = name;
        this.score = points;
        this.level = level;
        this.timestamp = time;
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

    public void setTimestamp(LocalDateTime timest) {
        this.timestamp = timest;
    }
    
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    /**
     * Method compares two scores by the amount of points
     * 
     * @param other Score to compare to
     * @return int point-difference of the two scores
     */
    @Override
    public int compareTo(Score other) {
        if (this.level != other.getLevel()) {
            return this.level - other.level;
        }
        if (this.score == other.getScore()) {
//            System.out.println(this.timestamp.compareTo(other.getTimestamp()));
            return this.timestamp.compareTo(other.getTimestamp());
        }
        return other.getScore() - this.score;
    }

}
