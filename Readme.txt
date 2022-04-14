Junyi Tang U45214536
Daniel Kim U22249399

This program is a game of Legends of Valor.

To run this project
cd src
javac Main.java
cd ..
java -classpath src Main
Tips:
Whole project build on Windows Linux virtual machine.  
So please use terminal like Xterminal or git bash to compile and run rather than cmd.

------------Class Hierarchy Structure-------------
Main ----- main class the entry of the program
BeginFrame ----- to give a image UI to start game
Game ----- abstract class for a game
    RPGGame ----- abstract class extends Game
        LegendsOfValor ----- class extends RPGGame where the game algorithm lies

Attackable ----- interface items that are attackable
Equipable ----- interface items that are equipable
Usable ----- interface items that are usable

Item ----- abstract class for an item
    Armor ----- extends Item implements Equipable
    Weapon ----- extends Item implements Equipable, Attackable
    Potion ----- extends Item implements Usable
    Spell ----- extends Item Attackable
        IceSpell ----- extends Spell
        FireSpell ----- extends Spell
        LightningSpell ----- extends Spell

Grid ----- abstract class for the game map
    LovMap ----- class extends Grid where the game map algorithms are implemented

CellType ----- enum for cell types
Cell ----- abstract class represents a cell on the board. Each cell has a type
    PlainCell ----- class extends Cell
    InaccessibleCell ----- class extends Cell
    BushCell ----- class extends Cell
    CaveCell ----- class extends Cell
    KoulouCell ----- class extends Cell
    NexusCell ----- class extends Cell

Nexus - abstract class to implement algorithms for nexus
    HeroNexus ------ extends Nexus which spawn heroes
    Monster Nexus ----- extends Nexus which spawn monsters

Lane ----- class that represents a lane

Character ----- abstract class representing a character in the game
    Hero ----- abstract class extends Character representing a hero character
        Sorcerer ----- extends Hero
        Warrior ----- extendsHero
        Paladin ----- extends Hero
    Monster ----- abstract class extends Character representing a monster character
        Exoskeleton ----- extends Monster
        Dragon ----- extends Monster
        Spirit ----- extends Monster

Inventory ----- class contains weapons, potions, armors, and spells
Markets ----- class that represents the market, where shopping algorithm lies

Player ----- class representing a player
    LegendsPlayer ----- extends Player

Round ----- class represents a round of game

ScannerParser ----- class to parse scanner inputs. All functions are static
FileParser ------ class to parse input files into objects
Printer ------ class to print shop/ heroes/ monsters/ weapon/ spell/ potion/ armor and other info
Colors ------ class for console colors
Factory ----- class to create objects
AudioUtility ------ class to add audios


Design Pattern

Factory design pattern and Observers Pattern are used in this project.
Factory design pattern applied when creating new hero or monster and those items like arm, weapon, potion and spell.
Observers pattern applied in Lane class, which maintains a set of Heroes to update the lane.


Bonus part
This project use a image UI to begin game  and game over frame.
Add sound effect when playing.
Colorful UI.

Whole project build on Windows Linux virtual machine.  