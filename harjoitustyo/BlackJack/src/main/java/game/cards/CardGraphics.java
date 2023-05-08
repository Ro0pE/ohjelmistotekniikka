/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.cards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.image.Image;

/**
 *
 * @author suvik
 */
public class CardGraphics {
    public HashMap<String,Image> graphics;
    String hearts = "Hearts";
    String spades = "Spades";
    String diamonds = "Diamonds";
    String clubs = "Clubs";
    
    public CardGraphics() {
        this.graphics = new HashMap();
        
    }
    
    
    
    public HashMap<String,Image> setupCardGraphics() throws FileNotFoundException {
        String temp;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    temp = hearts;
                    break;
                case 1:
                    temp = clubs;
                    break;
                case 2:
                    temp = spades;
                    break;
                default:
                    temp = diamonds;
                    break;
            }
        FileInputStream getHeartsA = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts A.png");
        Image HeartsA = new Image(getHeartsA); 
        graphics.put(temp +" A", HeartsA);
        FileInputStream getHearts2 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 2.png");
        Image Hearts2 = new Image(getHearts2);
        graphics.put(temp +" 2", Hearts2);
        FileInputStream getHearts3 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 3.png");
        Image Hearts3 = new Image(getHearts3);
        graphics.put(temp +" 3", Hearts3);
        FileInputStream getHearts4 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 4.png");
        Image Hearts4 = new Image(getHearts4);
        graphics.put(temp +" 4", Hearts4);
        FileInputStream getHearts5 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 5.png");
        Image Hearts5 = new Image(getHearts5);
        graphics.put(temp +" 5", Hearts5);
        FileInputStream getHearts6 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 6.png");
        Image Hearts6 = new Image(getHearts6);
        graphics.put(temp +" 6", Hearts6);
        FileInputStream getHearts7 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 7.png");
        Image Hearts7 = new Image(getHearts7);
        graphics.put(temp +" 7", Hearts7);
        FileInputStream getHearts8 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 8.png");
        Image Hearts8 = new Image(getHearts8);
        graphics.put(temp +" 8", Hearts8);
        FileInputStream getHearts9 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 9.png");
        Image Hearts9 = new Image(getHearts9);
        graphics.put(temp +" 9", Hearts9);
        FileInputStream getHearts10 = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts 10.png");
        Image Hearts10 = new Image(getHearts10);
        graphics.put(temp +" 10", Hearts10);
        FileInputStream getHeartsJ = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts J.png");
        Image HeartsJ = new Image(getHeartsJ);
        graphics.put(temp +" J", HeartsJ);
        FileInputStream getHeartsQ = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts Q.png");
        Image HeartsQ = new Image(getHeartsQ);
        graphics.put(temp +" Q", HeartsQ);
        FileInputStream getHeartsK = new FileInputStream("C:\\blackjack_backup\\harjoitustyo\\BlackJack\\images\\Hearts K.png");
        Image HeartsK = new Image(getHeartsK);
        graphics.put(temp +" K", HeartsK);
            
        }

        
        return this.graphics;
    }
    
    public void test() throws FileNotFoundException{
        
        HashMap testi;
        testi = setupCardGraphics();
        
     
        
    }
}
