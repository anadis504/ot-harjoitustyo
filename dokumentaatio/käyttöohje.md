# User Manual

Download the [SnakeGame.jar](https://github.com/anadis504/ot-harjoitustyo/releases/tag/viikko6) file.

### Running program
Run the command _java -jar SnakeGame.jar_ from the folder where the .jar file is located.

### Start

The program starts by showing the main menu window:

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/main_menu.png" height=400 position=left>

### Level Selection

By choosing any of the alternatives "Scores" or "New game" the program takes you to a level selection scene where you can choose the level of the game you want to play or what level of the game you want to view the scores for.

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/level_selection_scene.png" height=400>

### Starting new game

By clicking button "New game" a new game starts

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Game-start.png" height=400 position=left>


The game is played by navigating the continuously moving snake line in the frame by pressing arrow-keys. Points are achieved by catching 
the colored food-object that appears in the frame one at a time. When getting the food snake grows one unit longer and the food-ball appears in a
different location. The speed of the snake accelerates as the snake grows longer making the game more challenging. If the snake crashes in its
own body the game is lost and the screen reads Game Over in red. There are in total four different levels to choose from. The main variation is an open or closed frame and stable and unstable food.

* Levels 1 and 3 are with an open frame meaning the head of the snake will reappear on the opposite side of the game frame when hitting the border. 
* Levels 2 and 4 on the other hand are fully framed and hitting the outer wall will cause crash and Game Over. 
* Levels 1 and 2 have stable food object that stands still untill it is caught.
* Levels 3 and 4 have unstable food that will automatically relocate itself after a given time period. The remaining time is shown in the screen

<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/level4with_counter.png" height=400 position=left>


When Game Over: if your score after the game is not high enough to be in the list of Top Twenty scores for the given level button "back to menu" will appear and take you back to the main menu:


<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Game_Over_too_low_score.png" height=400 position=left>

If however at the end of the game your score if high enough to be in the list of Top Twenty you will be prompt to fill in your name to add your 
highscore to the list after which it will automatically take you back to the main menu:


<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Game_Over.png" height=400 position=left>

### Viewing highscores

By navigating to the button "Scores" in the main menu you may list the Top Twenty highscores:


<img src="https://github.com/anadis504/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/score-list.png" height=400 position=left>


The button "back to menu" will again take you back to the main menu.
