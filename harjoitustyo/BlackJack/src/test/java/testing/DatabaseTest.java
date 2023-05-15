/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import game.database.Database;
import game.player.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suvik
 */
public class DatabaseTest {
   public Database database;

    @Before
    public void setUp() {
       database = new Database();
       
    }
    @Test
    public void createAccountBalanceFileWorks() {
        database.createAccountBalanceFile();
        File accountBalanceFile = new File("accountBalance.txt");
        assertTrue(accountBalanceFile.exists());
    }
    @Test
    public void createHighScoresFileWorks() {
        database.createHighScoresFile();
        File highScoresFile = new File("highScoresFile.txt");
        assertTrue(highScoresFile.exists());
    }
    @Test
    public void getHighScoresWorks() throws IOException {
        createDummyHighScoresFile();

        ArrayList<String> highScores = database.getHighScores();

        assertEquals(10, highScores.size());
    }
    @Test
    public void getAccountBalanceFromFileWorks() throws IOException {
        createDummyAccountBalanceFile();

        double accountBalance = database.getAccountBalanceFromFile();

        assertEquals(500, accountBalance,0.1);
    }
    @Test
    public void saveStatsWorks() {
        Player player = new Player();
        player.setBalance(1000);

        database.saveStats(player);

        double accountBalance = database.getAccountBalanceFromFile();
        assertEquals(1000, accountBalance, 0.1);
    }
    
    public void createDummyHighScoresFile() throws IOException {
        File highScoresFile = new File("highScoresFile.txt");
        FileWriter fileWriter = new FileWriter(highScoresFile);
        for (int i = 0; i < 10; i++) {
            fileWriter.write("Random Gambler 100\n");
        }
        fileWriter.close();
        database.highScoresFile = highScoresFile;
    }
    
    public void createDummyAccountBalanceFile() throws IOException {
        File accountBalanceFile = new File("accountBalance.txt");
        FileWriter fileWriter = new FileWriter(accountBalanceFile);
        fileWriter.write("500");
        fileWriter.close();
        database.accountBalance = accountBalanceFile;
    }


    

    
}
