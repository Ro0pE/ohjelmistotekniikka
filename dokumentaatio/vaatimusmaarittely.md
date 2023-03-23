# Vaatimusmäärittely

## Sovelluksen tarkoitus

Pelin idea on pelata Black Jack - korttipeliä tietokonetta vastaan ja kerätä mahdollisimman suuri potti rahaa.
Black Jack - pelissä tarkoitus on voittaa jakaja ( eli tietokone )
Pelissä on tiettyjä sääntöjä:
 - Jakajan korteista vain toinen on aluksi esillä. Esimerkiksi jos jakajalla on ruutu 9 ja risti 8, pelaaja näkee vain, että jakalla on risti 8
 - Ensin tarkistetaan Black Jack, eli onko jommalla kummalla toinen korteista arvoltaan 10 (10,J,Q,K) ja toinen 11 (A, eli ässä)
 - Mikäli Black Jack löytyy jommalta kummalta, se pelaaja voittaa. Mikäli Black Jack löytyy molemmilta, panos palautuu pelaajan pelitilille.
 - Pelaaja aloittaa aina kierroksen
 - Pelaajan tarkoitus on saada suurempi summa kuin mitä jakalla on
 - Mikäli korttien summa on > 21, pelaaja tai jakaja häviää
 - Jakajan on otettava lisäkortti mikäli hänen korttien summa on < 17
 - Jos jakajan korttien summa on <= 17, jakaja ei ota lisää kortteja
 - Mikäli päädytään tasatulokseen, jakaja voittaa

 - 
## Käyttäjät

Pelillä on vain yksi käyttäjä, pelaaja itse.


## Pelin toiminnallisuus
- Peli alkaa, kun pelaaja painaa start game - nappia
- Pelaaja asettaa panoksen, minkä pitää olla < kuin pelitilin saldo
- Peli alkaa ja pelaajille (käyttäjä ja tietokone) jaetaan kortit
- Korttien summat ilmoitetaan pelaajille, tietokoneen korteista vain toisen kortin luku ilmoitetaan
- Pelaaja voi painaa nappia Hit, jolloin hän saa yhden kortin lisää
- Pelaajan korttien yhteisumma ilmoitetaan. Mikäli summa on > 21, pelaaja häviää ja peli loppuu
- Pelaaja voi painaa Hit - nappia uudestaan mikäli haluaa uuden kortin.
- Tämän jälkeen korttien summa ilmoitetaan taas. 
- Pelaaja voi painaa Stay - nappia, jolloin hän ei saa enää ottaa lisäkortteja ja vuoro siirtyy tietokoneelle
- Tietokoneen tuntematon kortti näytetään ja sen arvo lisätään tietokoneen käden yhteissummaan
- Tietokone ottaa joko lisäkortin tai sitten lopettaa korttien ottamisen, riippuen käden arvosta
- Tulos tarkistetaan, voittaja julistetaan ja mikäli pelaaja voittaa, voittosumma lasketaan ja lisätään pelaajan pelitilille


## Jatkokehitys

