/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.dao;

import anadis.snakegame.domain.Score;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author anadis
 */
public interface ScoreDao {
    List<Score> getAll();
    void add(String name, int points, int level, LocalDateTime timestamp);
}
