/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import domain.Score;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anadis
 */
public class FileScoreDao implements ScoreDao {

    private String file;
    private List<Score> scores;
    
    public FileScoreDao(String file) {
        this.file = file;
        this.scores = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(";");
                scores.add(new Score(parts[0], Integer.valueOf(parts[1])));
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public List<Score> topTwenty() {
        return scores;
    }

    @Override
    public void add(Score newScore) {
        scores.add(newScore);
        
        try {
            FileWriter writer = new FileWriter(file);
            for (Score score : scores) {
                String line = score.getName()+";"+score.getScore()+"\n";
                writer.write(line);
            }
            
            writer.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
}
