/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

import game.player.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author suvik
 */
public class Database {
    public Double balance;
    public File accountBalance;
    public File highScoresFile;
    
    public Database() {
        
    }
    
    public void createAccountBalanceFile(){
        try {
          accountBalance = new File("accountBalance.txt");
        if (accountBalance.createNewFile()) {
          System.out.println("File created: " + accountBalance.getName());
          FileWriter saveFiles = new FileWriter("accountBalance.txt");
          saveFiles.write("500");
          saveFiles.close();
        } else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        
    }
 }
    public void createHighScoresFile(){
        try {
          highScoresFile = new File("highScoresFile.txt");
        if (highScoresFile.createNewFile()) {
          System.out.println("File created: " + highScoresFile.getName());
          FileWriter saveFiles = new FileWriter("highScoresFile.txt");
          for (int i = 0; i < 10; i++) {
              saveFiles.write("Random Gambler 100\n");
          }
          
          saveFiles.close();
        } else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        
    }
 }
    public double getAccountBalanceFromFile() {
            try {
                File statsFile = new File("accountBalance.txt");
                Scanner myReader = new Scanner(statsFile);
                while (myReader.hasNextLine()) {
                  String data = myReader.nextLine();
                  balance = Double.parseDouble(data);

                }
            myReader.close();
          } catch (IOException e) {
                System.out.println("An error occurred.!!!!!!");
            
          }
            return balance;
   }
    public void saveStats(Player player) {
            try {
                FileWriter saveFiles = new FileWriter("accountBalance.txt");
                //double newBalance = getAccountBalanceFromFile();

                //newBalance =+ player.getAccountBalance();               
                saveFiles.write(Integer.toString((int) player.getAccountBalance()));
                saveFiles.close();
                System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
                System.out.println("An error occurred.");
      
    }
  }
    
}
