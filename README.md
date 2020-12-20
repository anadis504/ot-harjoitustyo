# Ohjelmistotekniikka-hy 2020, harjoitustyö

## Snake game

The classic snake-game desctop application: a single-player computer game where the player manouvers a growing line (snake) moving on the screen.

## Releases
[Viikko 5](https://github.com/anadis504/ot-harjoitustyo/releases/tag/Viikko5)

## Documentation
[Project Specification](https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusm%C3%A4%C3%A4rittely.md)

[Working hours](https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Architecture description](https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[User Manual](https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/k%C3%A4ytt%C3%B6ohje.md)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Projektin koodi suoritetaan Netbeansissa vihreää nappia painamalla tai komennolla 

```
mvn compile exec:java -Dexec.mainClass=anadis.snakegame.ui.Main
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _SnakeGame-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/anadis504/ot-harjoitustyo/blob/master/SnakeGame/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### JavaDoc

JavaDoc on generoitavissa komennolla 

```
mvn javadoc:javadoc
```

Generoitu JavaDoc löytyy hakemistosta _target/site/apidocs/_
