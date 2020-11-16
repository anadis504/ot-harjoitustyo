/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author anadis
 */
public class Score {
    
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
}
