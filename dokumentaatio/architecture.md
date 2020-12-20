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

User interface contains three different scenes:

* Main menu scene
* Score listeíng scene
* Game scene


## Application logic

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/package.png">


