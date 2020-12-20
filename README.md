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

[Testing documentation](https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/testing.md)

## Command line functions

### Running the program

Program code can be run from Netbeans or from terminal with command

```
mvn compile exec:java -Dexec.mainClass=anadis.snakegame.ui.Main
```

### Testing

Test can be run with command

```
mvn test
```

Code coverage report can be generated with command

```
mvn jacoco:report
```

The code coverage report can be reviewed by running file _target/site/jacoco/index.html_ in browser

### Generating a working jar file

Running command

```
mvn package
```

generates jar file _SnakeGame-1.0-SNAPSHOT.jar_ to the repositorio _target_ and can be run with command

```
java -jar SnakeGame-1.0-SNAPSHOT.jar
```

### Checkstyle

Style checking as defined in the document [checkstyle.xml](https://github.com/anadis504/ot-harjoitustyo/blob/master/SnakeGame/checkstyle.xml) can be run with command

```
 mvn jxr:jxr checkstyle:checkstyle
```

And possible style violations be reviewed by running file _target/site/checkstyle.html_ in browser

### JavaDoc

JavaDoc is generates by command

```
mvn javadoc:javadoc
```

and reviewed by running files found in _target/site/apidocs/_
