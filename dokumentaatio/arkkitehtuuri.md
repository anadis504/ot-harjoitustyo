# Architecture

## Architecture structure

The application has a three-layer architecture and the package structure is as follows

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/packagediagram.png">

Package snakegame.ui contains user interface implemented with javaFX. Package snakegame.domain contains the application logic and package snakegame.dao contains reading and writing to permanent memory logic.

## User Interface

Package snakegame.ui consists of three classes:
* Ui
* ScoreScene
* GameScene

Class Ui contains the start([Stage](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html) primaryStage) method that is launched from the _main_ method when the program starts.

User interface contains four different scenes:

* Main menu scene
* Level selection scene
* Score listeíng scene
* Game scene

Every scene is created by own [scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html) object that is brought to the [stage](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html).

Application loginc and user interface are separeted.

## Application logic

Application logic is in the domain package. Class Block is the primary block in the game application logic. Classes Snake and Food are using/extending Block. 

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Block%20logic.png" width=300>

Direction is of Enum type representing the four possible directions of the movement on the grid.
Scores are represented by the Score class and saved to permanent memory using ScoreDao. 

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/score%20class.png" width=100>

One instance of the class ScoreService is initialized during program run and manages the score logic.
The overall functionality of the program is described by:

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/package.png">

## Saving to permanent memory

The reading and writing to the permament memory is managed by ScoreDao object and follows the Data Access Object pattern. The concrete class of FileScoreDao is hidden behind the ScoreDao interface.

The file name is defined in the config.properties file in the root directory. The data is formatted as follows:

```
name;score;level;timestamp
other name;some score;level;timestamp
```
FileScoreDao class arranges the scores firstly by the game level, then by the score amount, then by the time of creation.

## Functionality

Functionality for selecting a new game of a given level:

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.png" width=400>
