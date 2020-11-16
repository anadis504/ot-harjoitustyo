package anadis.snakegame.ui;

import anadis.snakegame.data_access.FileScoreDao;
import anadis.snakegame.data_access.ScoreDao;
import anadis.snakegame.domain.Score;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author anadis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ScoreDao dao = new FileScoreDao("scores.txt");
        dao.add(new Score("bob", 12));
        System.out.println("Hello world!");
        Ui.main(args);
    }
    
}
