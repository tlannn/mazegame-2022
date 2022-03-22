# l2s4-projet-2022

# Equipe

- Theo LANNOY
- Emma EVERAERE
- Damien MICHELOT
- Rayan CHERGUI

# Sujet

[Le sujet 2022](https://www.fil.univ-lille1.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)

# Livrables

## Livrable 1

Les 2 algorithmes (Kruskal et Depth First Search) fonctionnent correctement.
Tous les tests sont faits sur toutes les classes et tous fonctionnent correctement.

On a donc les classes :  
- Maze (la classe parente de KruskalMaze et DepthFirstSearchMaze)  
- KruskalMaze  
- DepthFirstSearchMaze  
- Cell  
- Random

On a les exceptions:
- InvalidAdjacentCellException : on essaie d'enlever un mur entre 2 cases non adjacentes.

On a une enum:  
- WallOrientation

On a un main qui affiche les 2 labyrinthes.

## Exécution
Pour générer la javadoc:
```console
$ javadoc -sourcepath src -d docs -subpackages game # Crée la documentation dans le dossier 'docs'
```

Pour compiler les fichiers sources:
```console
$ javac -sourcepath src -d classes src/game/*.java
```

Pour exécuter le main:
``` console
# Compiler les classes au préalable
$ java -classpath classes game.Main
```

Pour lancer les tests:
```console
$ javac -sourcepath src -classpath test4poo.jar test/game/maze/*.java # Compilation des classes de test
$ java -jar test4poo.jar -classpath test game.maze.TestMaze # Lancement des tests de la classe MAze
```

Pour créer un jar:
```console
# Compiler les classes au préalable
$ jar cvfe  game.jar game.Main -C classes game # Création de l'archive
$ java -jar game.jar # Lancement du programme
```



### Atteinte des objectifs
Les objectifs sont atteints : Les 2 algorithmes (Kruskal et Depth First Search) fonctionnent correctement et les tests sont validés.

### Difficultés restant à résoudre
Aucune

## Livrable 2

Les classes des personnages et des items ont été codés. Nous avons également pris de l'avance et créé les classes relatives aux indices et à la quête, étant donné que les personnages et items en dépendent. Les tests ont été réalisés pour toutes les classes créées.

Une modification a été réalisée sur la classe Cell, nous avons rajouté deux attributs permettant de retrouver les items et personnages qui sont présents sur une cellule, ainsi qu'une méthode permettant de déterminer les directions dans lesquelles un personnage peut se déplacer en fonction des murs ouverts de la cellule. Nous avons également renommé la classe WallOrientation en Orientation, afin de la généraliser.

Concernant les personnages, nous trouvons donc les nouvelles classes suivantes :
- Character
- NonPlayerCharacter
- Player
- Trader
- Sphinx
- Fool
- Altruist

Pour les items :
- Item
- Parchment
- Jewel
- JewelRarity (une enum)

Pour les indices :
- Hint
- FixedHint
- DynamicHint
- FakeHint
- WinningCellCoordinatesHint
- ItemPositionHint
- QuestConditionHint
- WinningCellOrientationHint
- DistanceFromWinningCellHint

Pour la quête :
- Quest
- QuestCondition
- EarnGoldCondition
- MeetSpecificCharacterCondition

On trouve également les exceptions suivantes :
- NotEnoughGoldException
- UnknownItemException

On ajoute à cela une classe Enigma, mais qui n'a pas été complétée (uniquement dans le but de valider la compilation).

### Atteinte des objectifs

Les objectifs sont atteints : les personnages et les items ont été créés (hormis les interactions, telles que parler aux personnages) et les tests sont validés.

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

# Journal de bord

## Semaine 1
Tout le monde: diagramme UML de la partie labyrinthe on est ok dessus
Emma: commence à coder la classe Cell
Damien et Rayan: commencent à coder Maze et WallOrientation 
Theo : création de la classe KruskalMaze (à priori terminée) qui utilise l'algorithme de Kruskal pour générer un labyrinthe, et d'une classe Random pour générer des entiers aléatoires dans un intervalle

## Semaine 2
Emma : finit classe Cell. Vérification et compréhension de KruskalMaze
Rayan et Damien : Finit removeWall , ExternalWall, nbCell, et commencement du toString
Theo : tests pour la classe Cell

## Semaine 3
Emma: finit classe Cell, reflexion sur les tests de KruskalMaze et reflexion sur DepthFirstSearchMaze
Emma et Damien ont décidé de:  "on fait une liste qui retient le chemin de mur déjà visité" dans  DepthFirstSearchMaze
Theo : correction d'un bug lors de la génération des labyrinthes dû à un probable d'index dans une liste lors de l'accès aux cellules

## Semaine 4 (vacances)
Damien et Rayan : Realisation des tests pour les algos de labyrinthe (KruskalMaze et DepthFirstSearchMaze) + Doc de Maze
Emma : finit le code de DepthFirstSearchMaze.
Theo : Documentation des classes, revue des tests

## Semaine 5
Emma verification des tests.
Tout le monde: UML sur NonPlayerCharacter et ses enfants (Trader, Sphinx, Fool et Altruist) ainsi que les Items

## Semaine 6
Emma : UML sur les Hint + code le début de ItemPositionHint
Damien et Rayan code les class Player, Character, Item, Parchment et les différents enum + réalisation des tests pour Player
Theo : UML dans la globalité + début sur la classe NonPlayerCharacter et ses enfants, ainsi que les classes FakeHint et InputReader. Correctifs sur Player et ses tests

## Semaine 7
Emma: add look in Player, add testItem
Theo : résolution de la partie Quest de l'UML, et création des classes Quest/QuestCondition
Rayan : class player et character
Damien : class jewel et Parchment

## Semaine 8
Emma : test de tous les items (TestParchment et TestJewel)
Damien et Rayan : TestPlayer + TestCharacter mise en place des methodes toString() et indices de conditions de victoire.

## Semaine 9
Emma: dans game creation de l'IHM (playTurn)

## Semaine 10

## Semaine 11

## Semaine 12
