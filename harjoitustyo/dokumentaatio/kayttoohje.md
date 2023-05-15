# Käyttöohje

Lataa ohjelma [BlackJack-1.0-SNAPSHOT.jar](https://github.com/Ro0pE/ohjelmistotekniikka/releases/tag/Loppupalautus)


## Ohjelman käynnistäminen


Ohjelma käynnistyy komennolla 

```
java -jar BlackJack-1.0-SNAPSHOT.jar
```
## Konfigurointi

Ohjelman käynnistyessä ensimmäisen kerran se luo kaksi tekstitiedostoa:

Pelaajan rahavarat:

```
accountBalance.txt
```

Huipputulokset:

```
highScoresFile.txt
```

## Pelin pelaaminen

- Paina alkuvalikosta Play
- Aseta panos. (Alussa rahaa on 55€), joten valitse summa väliltä 1-500
- Paina Set bet
- Käynnistä peli painamalla Deal
- Pelaajille jaetaan kortit. Voit ottaa lisäkortin painamalla Hit tai pysymällä nykyisessä kädessä painamalla Stay.
- Jos otit lisäkortin ja korttiesi summa on yli 22, hävisit. Jos korttien summa on alle 22, voit ottaa lisää.
- Jos korttiesi summa on alle 22 ja painat stay, vastustaja ottaa lisää kortteja kunnes hänen kätensä arvo on > 16 
- Lopuksi ohjelma laskee tuloksen. Jos häviät menetät panoksesi. Voittaessa panoksesi tuplaantuu ja se lisätään pelitilille.
- Jos jommalla kummalla on aluksi black jack (kortti numero 10 tai kuvakortti JA ässä) niin hän voittaa. Pelaajan panos maksetaan 1.5 kertaisena.
- Jos molemmilla on black jack niin panos palautuu pelaajalle.
- Voit lopettaa pelaamisen painalla "cash out" - nappia, mistä pääsee alkuvalikkoon. "Quit game" nappi sammuttaa ohjelman
