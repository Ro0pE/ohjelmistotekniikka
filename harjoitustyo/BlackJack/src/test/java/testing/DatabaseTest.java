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
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suvik
 */
public class DatabaseTest {
    Player player;
    Database database;
    public DatabaseTest(){
           
    }
    @Before
    public void setUp() {
       player = new Player();
       player.setBalance(500);
       database = new Database();
       
    }
    
    @Test
    public void getCorrectAccountBalanceFromFile() throws IOException {
        
        File testFile = new File("accountBalance.txt");
        FileWriter saveFiles = new FileWriter(testFile);            
            saveFiles.write(Integer.toString((int) player.getAccountBalance()));
            saveFiles.close();
            
        double playerMoney = database.getAccountBalanceFromFile();
        assertEquals(500.0, playerMoney, 0.01);
    }

    

    
}
