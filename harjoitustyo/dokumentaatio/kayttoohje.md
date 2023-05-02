# Käyttöohje

Kloonaa projekti koneellesi:
git@github.com:Ro0pE/ohjelmistotekniikka.git

## Ohjelman käynnistäminen


Avaa projektihakemisto "harjoitustyo/BlackJack" NetBeanssilla ja paina vihreää nuolta "Run project" tai paina F6

(.jar tiedoston kanssa vielä ongelmia)


## Pelin pelaaminen

- Paina alkuvalikosta New Game
- Aseta panos. (Alussa rahaa on 100€), joten valitse summa 1-100 väliltä
- Paina Set
- Käynnistä peli painamalla Play
- Ylhäällä näet pelaajien käsien arvon. Voit ottaa lisäkortin painamalla Hit tai pysymällä nykyisessä kädessä painamalla Stay.
- Jos otit lisäkortin ja korttiesi summa on yli 22, hävisit. Jos korttien summa on alle 22, voit ottaa lisää.
- Jos korttiesi summa on alle 22 ja painat stay, vastustaja ottaa lisää kortteja kunnes hänen kätensä arvo on > 16 
- Lopuksi ohjelma laskee tuloksen. Jos häviät menetät panoksesi. Voittaessa panoksesi tuplaantuu ja se lisätään pelitilille.
