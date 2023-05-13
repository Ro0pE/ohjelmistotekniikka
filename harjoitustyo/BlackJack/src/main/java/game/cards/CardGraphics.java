/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.cards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
            for (int f = 2; f < 15; f++) {
            String value;
            switch (f) {
                case 11:
                    value = "J";
                    break;
                case 12:
                    value = "Q";
                    break;
                case 13:
                    value = "K";
                    break;
                case 14:
                    value = "A";
                    break;
                default:
                    value = "" + String.valueOf(f);
                    break;
            }
                System.out.println(temp + value +".png");
         
      
       InputStream getCardFront = this.getClass().getClassLoader().getResourceAsStream(temp + value + ".png");
       Image cardFront = new Image(getCardFront); 
       graphics.put(temp + " " + value, cardFront);
        }
        }
        //ClassLoader classLoader2 = getClass().getClassLoader();
       // File file = new File(classLoader2.getResource("cardback.png").getFile());
       // InputStream getCardBack = new FileInputStream(file);
        InputStream getCardBack = this.getClass().getClassLoader().getResourceAsStream("cardback.png");
        Image cardBack = new Image(getCardBack);
        graphics.put("card back", cardBack);

        
        return this.graphics;
    }
    
    public void test() throws FileNotFoundException{
        
        HashMap testi;
        testi = setupCardGraphics();
        
     
        
    }
}
