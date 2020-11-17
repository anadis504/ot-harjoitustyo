# Ohjelmistotekniikka-hy 2020, harjoitustyö

## Snake game

## Dokumentaatio
[vaatimusmäärittely](https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusm%C3%A4%C3%A4rittely.md)

[työaikakirjanpito](https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Projektin koodi suoritetaan komennolla 

```
mvn compile exec:java -Dexec.mainClass=pakkaus.Paaohjelma
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
