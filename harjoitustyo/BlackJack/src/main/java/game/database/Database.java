/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.database;

import game.player.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author suvik
 */
public class Database {

    public Double balance;
    public File accountBalance;
    public File highScoresFile;
    public ArrayList<String> highScoreList;

    public Database() {
        this.highScoreList = new ArrayList<>();

    }

    public void createAccountBalanceFile() {
        try {
            accountBalance = new File("accountBalance.txt");
            if (accountBalance.createNewFile()) {

                FileWriter saveFiles = new FileWriter("accountBalance.txt");
                saveFiles.write("500");
                saveFiles.close();
            } else {

            }
        } catch (IOException e) {

        }
    }

    public void createHighScoresFile() {
        try {
            highScoresFile = new File("highScoresFile.txt");
            if (highScoresFile.createNewFile()) {

                FileWriter saveFiles = new FileWriter("highScoresFile.txt");
                for (int i = 0; i < 10; i++) {
                    saveFiles.write("Random Gambler 100\n");
                }

                saveFiles.close();
            } else {

            }
        } catch (IOException e) {

        }
    }

    public ArrayList getHighScores() {
        try {
            File highscoreFile = new File("highScoresFile.txt");
            Scanner myReader = new Scanner(highscoreFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                highScoreList.add(data);

            }
            myReader.close();
        } catch (IOException e) {

        }
        return highScoreList;
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

        }
        return balance;
    }

    public void saveStats(Player player) {
        try {
            FileWriter saveFiles = new FileWriter("accountBalance.txt");            
            saveFiles.write(Integer.toString((int) player.getAccountBalance()));
            saveFiles.close();

        } catch (IOException e) {

        }
    }

}
