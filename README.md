# Ohjelmistotekniikka 2023

## Harjoitustyö :: Bläck Jäck

### Dokumentaatio:

[Tuntikirjanpito](https://github.com/Ro0pE/ohjelmistotekniikka/blob/master/harjoitustyo/dokumentaatio/tuntikirjanpito.md)

[Changelog](https://github.com/Ro0pE/ohjelmistotekniikka/blob/master/harjoitustyo/dokumentaatio/changelog.md)

[Testausdokumentti](https://github.com/Ro0pE/ohjelmistotekniikka/blob/master/harjoitustyo/dokumentaatio/testausdokumentti.md)

[Vaatimusmäärittely](https://github.com/Ro0pE/ohjelmistotekniikka/blob/master/harjoitustyo/dokumentaatio/vaatimusmaarittely.md)

[Käyttöohje](https://github.com/Ro0pE/ohjelmistotekniikka/blob/master/harjoitustyo/dokumentaatio/kayttoohje.md)

### Releaset

[Viikko 5](https://github.com/Ro0pE/ohjelmistotekniikka/releases/tag/viikko5)

[Loppupalautus](https://github.com/Ro0pE/ohjelmistotekniikka/releases/tag/Loppupalautus)

## Komentorivitoiminnot

Komennot suoritetaan projektin juuressa (sis. pom, target, src..)

### Ohjelman käynnistys

Komento:
```
mvn compile exec:java -Dexec.mainClass=game.main.Main
```

### Testaus

Komento:
```
mvn test
```

Testikattavuusraportin komento:
```
mvn jacoco:report
```
Kattavuusraportti löytyy selaimella: _target/site/jacoco/index.html_

### Jar-tiedoston generointi

Komento:
```
mvn package
```

Komennon jälkeen hakemistosta _target_ löytyy jar-tiedosto _BlackJack-1.0-SNAPSHOT.jar_

### Checkstyle

 Komento:
 ```
 mvn jxr:jxr checkstyle:checkstyle
 ```

 Checkstyle virheet löytyvät selaimella: _target/site/checkstyle.html_
 
### JavaDoc

Komento:

mvn javadoc:javadoc

JavaDocia voi tutkia selaimella avaamalla tiedoston target/site/apidocs/index.html
 
 
