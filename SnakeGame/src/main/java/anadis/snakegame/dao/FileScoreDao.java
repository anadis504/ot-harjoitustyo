/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.dao;

import anadis.snakegame.domain.Score;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Data Object Access class for storing game scores to permanent memory
 *
 * @author anadis
 */
public class FileScoreDao implements ScoreDao {

    private String file;
    private List<Score> scores;

    /**
     *
     * @param file the file where data is stored
     * @throws IOException
     */
    public FileScoreDao(String file) throws IOException {
        this.file = file;
        this.scores = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(";");
                scores.add(new Score(parts[0], Integer.valueOf(parts[1]), 
                        Integer.valueOf(parts[2]), LocalDateTime.parse(parts[3])));
            }
        } catch (Exception ex) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    /**
     *
     * @return List<Score> scores from memory
     */
    @Override
    public List<Score> getAll() {
        return scores;
    }

    /**
     *
     * @param newScore score to write to memory
     */
    @Override
    public void add(Score newScore) {
        scores.add(newScore);
        Collections.sort(scores);

        try {
            FileWriter writer = new FileWriter(file);
            for (Score score : scores) {
                String line = score.getName() + ";" + score.getScore()
                        + ";" + score.getLevel() + ";" + score.getTimestamp() 
                        + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (Exception ex) {
        }
    }
}
