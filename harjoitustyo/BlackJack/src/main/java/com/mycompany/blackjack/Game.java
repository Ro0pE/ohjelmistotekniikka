/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

import java.util.Scanner;

/**
 *
 * @author suvik
 */
public class Game {
    Boolean gameIsOn;
    Player player; 
    Player ai;
    Scanner scanner;
    int bet;
    Boolean checkBalance;
    int profit;
    
    int input;
    public Game(){
        bet = 0;
        profit = 0;
        scanner = new Scanner(System.in);
        player = new Player("Boris");
        ai = new Player("AI");
        gameIsOn = true;
        checkBalance = false;
        
    }
    
    public void gameOptions() {
            GameService newGame = new GameService(ai,player);
   
            while (checkBalance == false) {
            System.out.println("Your balance is " + player.getAccountBalance());
            System.out.println("Set bet!");
                bet = scanner.nextInt();
            if (bet > player.getAccountBalance()) {
                System.out.println("Not enough money.Bet again");
                
            } else {
                checkBalance = true;                 
            }
            }

            newGame.startGame();
            System.out.println("Game is on!");
            System.out.println("Dealing cards:");
            newGame.dealAI();
            newGame.dealAI();
            System.out.println(newGame.ai.getHandValue());
            newGame.dealPlayer();
            newGame.dealPlayer();
            System.out.println(newGame.player.getHandValue());
            newGame.checkBlackJack(bet);
        while(!player.loser && !player.winner) {    
            System.out.println("[1] Hit");
            System.out.println("[2] Stay");
            System.out.println("[3] Double");
            input = scanner.nextInt();
            
            switch (input) {
                case 1:                   
                    newGame.dealPlayer();
                    System.out.println("You got now:");
                    newGame.player.showHand();
                    System.out.println(newGame.player.getHandValue());
                    if (this.player.getHandValue() > 21) {
                        System.out.println("OVER! You lose!");
                        this.player.setLoser();
                        player.setBalance(-bet);
                        
                    }
                    break;
                case 2:
                    
                    while (ai.getHandValue() < 17) {
                        System.out.println("AI takes more");
                        newGame.dealAI();
                        System.out.println("Ai got now:");
                        newGame.ai.showHand();
                    }
                    if (ai.getHandValue() >= 17) {
                        System.out.println("AI final hand:");
                        newGame.ai.showHand();
                        System.out.println("Ai hand value: " + newGame.ai.getHandValue());
                        System.out.println("Your final hand:");
                        newGame.player.showHand();
                        System.out.println("Player hand value: " + newGame.player.getHandValue());
                        newGame.checkWinner(bet);
                    }
                    break;
                case 3:
                    if (player.getAccountBalance() < (bet * 2)) {
                        System.out.println("Not enough funds to double!");
                    } else {
                        
                    System.out.println("Double! Dealing one card to the player");
                    newGame.dealPlayer();
                    System.out.println("Player got now:");
                    newGame.player.showHand();
                    System.out.println("Player hand value is " + player.getHandValue());
                    if (player.getHandValue() > 21) {
                        System.out.println("OVER! You lose!");
                        this.player.setLoser();
                        bet = bet * 2 ;
                        player.setBalance(-bet);
                        
                    } else {
                       while (ai.getHandValue() < 17) {
                        System.out.println("AI takes more");
                        newGame.dealAI();
                        System.out.println("Ai got now:");
                        newGame.ai.showHand();
                        System.out.println("Ai hand value "+ newGame.ai.getHandValue());
                    }
                       if (ai.getHandValue() >= 17) {
                        System.out.println("AI final hand:");
                        newGame.ai.showHand();
                        System.out.println("Ai hand value: " + newGame.ai.getHandValue());
                        System.out.println("Your final hand:");
                        newGame.player.showHand();
                        System.out.println("Player hand value: " + newGame.player.getHandValue());
                        bet = bet * 2;
                        newGame.checkWinner(bet);
                    }
                      
                    }
                        
                    }
                    break;
                        
                default:
                    System.out.println("Incorrect input");
                    break;
            }
            
            
            
            gameIsOn = false;
        }
    }
    public void afterGameOptions() {
         System.out.println("[3] Quit");
         System.out.println("[4] Play again");
         System.out.println("[5] Deposit money");
         input = scanner.nextInt();
            
            switch (input) {
                case 3:
                    System.out.println("Closing game");
                    scanner.close();
                    break;
                case 4:
                    System.out.println("New game!");
                    player.clearStats();
                    ai.clearStats();
                    this.checkBalance = false;
                    this.startGame();
                    break;
                case 5:
                    System.out.println("How much you want to invest: (1-1000)");
                    input = scanner.nextInt();
                    if (input < 1 || input > 1000) {
                        System.out.println("incorrect amount");
                    } else {
                        System.out.println("Deposit " + input + " to your account");
                        player.setBalance(input);
                        System.out.println("Current balance is " + player.getAccountBalance());
                        afterGameOptions();
                        
                    }
                default:
                    afterGameOptions();
                    break;
            }
        
    }
    
    public void startGame() {

            gameOptions();
            
    
            afterGameOptions();
            
                    
    }
    
    
    
}