# Testing document

The project has been unit and integration tested automatically by JUnit and exploratory testing of the running program.

## Unit and integration testing

### Application logic
Integration testing is focused on the application logic part: the two service classes, [ScoreServiceTest](https://github.com/anadis504/ot-harjoitustyo/blob/master/SnakeGame/src/test/java/anadis/snakegame/domain/ScoreServiceTest.java) and [GameServiceTest](https://github.com/anadis504/ot-harjoitustyo/blob/master/SnakeGame/src/test/java/anadis/snakegame/domain/GameServiceTest.java), in package [domain](https://github.com/anadis504/ot-harjoitustyo/tree/master/SnakeGame/src/main/java/anadis/snakegame/domain). The integration test of the ScoreService class uses [mockito](https://site.mockito.org/) objects to simulate FileDao class for storing data in permament memory.

The other classes of the application logic layer in the domain package are comprehensively unit tested to guarantee their functionality.

### DAO
FileScoreDao class is tested by creating a temporary file following [TemporaryFolder](https://junit.org/junit4/javadoc/4.12/org/junit/rules/TemporaryFolder.html) rules.

### Code coverage
Except from the user interface package [snakegame.ui](https://github.com/anadis504/ot-harjoitustyo/tree/master/SnakeGame/src/main/java/anadis/snakegame/ui) the coverage of the tested code is at 94% for row coverage and 97% for branches. 

<img src="https://github.com/anadis504/ot-harjoitustyo/tree/master/SnakeGame/src/main/java/anadis/snakegame/ui" width=750>
