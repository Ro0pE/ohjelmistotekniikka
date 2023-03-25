package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void tarkistaSaldo() {
        int saldo = kortti.saldo();
        assertEquals(10, saldo);
    }
    
    @Test
    public void rahanLatausMuuttaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    @Test
    public void kortiltaVoiOttaaRahaaJosSaldoRiittaa() {
        Boolean voikoRahaaOttaa = kortti.otaRahaa(10);
        assertEquals(true, voikoRahaaOttaa);
        
    }
    @Test
    public void kortiltaEiVoiOttaaRahaaJosSaldoEiRiita(){
        Boolean voikoRahaaOttaa = kortti.otaRahaa(20);
        assertEquals(false, voikoRahaaOttaa);
        
        
    }
            
}
