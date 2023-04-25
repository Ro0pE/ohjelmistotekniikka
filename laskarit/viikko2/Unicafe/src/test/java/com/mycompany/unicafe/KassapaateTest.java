package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(10000);
    }
    
    @Test
    public void kassassaOikeaSummaRahaaKunMyyntiaEiOle(){
        int summaKassassa = kassapaate.kassassaRahaa();
        int myytyjaMaukkaita = kassapaate.maukkaitaLounaitaMyyty();
        int myytyjaEdullisia = kassapaate.edullisiaLounaitaMyyty();
        int yhtMyytyja = myytyjaEdullisia + myytyjaMaukkaita;
        assertEquals(100000, summaKassassa);
        assertEquals(0, yhtMyytyja);
           
    }
    @Test
    public void maukkaidenOstoLisaaRahaaKassaan() {  
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    @Test
    public void edullistenOstoLisaaRahaaKassaan() {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
         
    }
    @Test
    public void vaihtorahanSummaOnOikeaKunSyoMaukkaasti() {
        int vaihtoraha = kassapaate.syoMaukkaasti(500);
        assertEquals(100, vaihtoraha);
        
    }
    @Test
    public void vaihtorahanSummaOnOikeaKunSyoEdullisesti() {
        int vaihtoraha = kassapaate.syoEdullisesti(300);
        assertEquals(60, vaihtoraha);
        
    }    
    
    @Test
    public void maukkaidenLounaidenMyyntiKasvaaKunOstoToteutuu() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullistenLounaidenMyyntiKasvaaKunOstoToteutuu() {
        kassapaate.syoEdullisesti(500);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    

    

    @Test
    public void rahaPalautuuJosEiRiitaEdullisenOstoon() {
        int rahaaPalautuu = kassapaate.syoEdullisesti(200);
        assertEquals(200, rahaaPalautuu);
        
    }
   
    @Test
    public void rahaPalautuuJosEiRiitaMaukkaanOstoon() {
        int rahaaPalautuu = kassapaate.syoMaukkaasti(300);
        assertEquals(300, rahaaPalautuu);
        
    }   
    @Test
    public void myyntiaEiTapahduJosRahatEdulliseenEiRiita(){
        kassapaate.syoEdullisesti(200);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myyntiaEiTapahduJosRahatMaukkaaseenEiRiita(){
        kassapaate.syoMaukkaasti(200);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassavaratEiKasvaJosMaukkaanOstaminenEiOnnistu(){
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void kassavaratEiKasvaJosEdullisenOstaminenEiOnnistu(){
        kassapaate.syoEdullisesti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }    
    
    @Test
    public void maukkaanOstoOnnistuuJosKortillaRahaa(){
        Boolean ostoOnnistuu = kassapaate.syoMaukkaasti( kortti);
        assertEquals(true, ostoOnnistuu);
        
    }
    @Test
    public void edullisenOstoOnnistuuJosKortillaRahaa(){
        Boolean ostoOnnistuu = kassapaate.syoEdullisesti(kortti);
        assertEquals(true, ostoOnnistuu);
        
    }    
    @Test
    public void maukkaidenMyytyjenLounaidenMaaraKasvaaJosKortillaOnRahaa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullistenMyytyjenLounaidenMaaraKasvaaJosKortillaOnRahaa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void josKortillaEiRahaaNiinKortinSaldoEiMuutuKunOstaaMaukkaita(){
        Maksukortti uusiKortti = new Maksukortti(2);
        kassapaate.syoMaukkaasti(uusiKortti);
        assertEquals(2, uusiKortti.saldo());
        
    }
    @Test
    public void josKortillaEiRahaaNiinKortinSaldoEiMuutuKunOstaaEdullisia(){
        Maksukortti uusiKortti = new Maksukortti(2);
        kassapaate.syoEdullisesti(uusiKortti);
        assertEquals(2, uusiKortti.saldo());
        
    }    
    @Test
    public void kortillaOstoEiMuutaKassanKateisvarojaKunOstaaEdullisia() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void kortillaOstoEiMuutaKassanKateisvarojaKunOstaaMaukkaita() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }    
    @Test
    public void rahanLatausToimii() {
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(10500, kortti.saldo());
    }
    @Test
    public void rahanLatausAlleNollaEuroaToimii(){
        kassapaate.lataaRahaaKortille(kortti, -50);
        assertEquals(10000, kortti.saldo());
    }
}
