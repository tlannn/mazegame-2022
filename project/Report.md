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

Les exceptions :
- InvalidAdjacentCellException : on essaie d'enlever un mur entre 2 cases non adjacentes.

Une énumération :
- WallOrientation

On a un main qui affiche les 2 labyrinthes.


### Atteinte des objectifs
Les objectifs sont atteints : Les 2 algorithmes (Kruskal et Depth First Search)
fonctionnent correctement et les tests sont validés.

### Difficultés restant à résoudre
Aucune

## Livrable 2

Les classes des personnages et des items ont été codés. Nous avons également pris
de l'avance et créé les classes relatives aux indices et à la quête, étant donné
que les personnages et items en dépendent. Les tests ont été réalisés pour toutes
les classes créées.

Une modification a été réalisée sur la classe Cell, nous avons rajouté deux
attributs permettant de retrouver les items et personnages qui sont présents sur
une cellule, ainsi qu'une méthode permettant de déterminer les directions dans
lesquelles un personnage peut se déplacer en fonction des murs ouverts de la
cellule. Nous avons également renommé la classe WallOrientation en Orientation,
afin de la généraliser.

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

On ajoute à cela une classe Enigma, mais qui n'a pas été complétée (uniquement
dans le but de valider la compilation).

### Atteinte des objectifs

Les objectifs sont atteints : les personnages et les items ont été créés
(hormis les interactions, telles que parler aux personnages) et les tests sont
validés.

### Difficultés restant à résoudre

Aucune

## Livrable 3

### Atteinte des objectifs

Le projet est terminé dans son ensemble. Il est possible d'interragir avec les
items et les personnages, de se déplacer ainsi que de gagner la partie avec en
plus des conditions supplémentaires pour la quête.

Il reste quelques bugs à corriger, et également des tests à rédiger pour les
classes créées dernièrement. Il faut également revoir les anciens tests, car
de gros changements sont survenus sur l'architecture de l'application qui
peuvent rendre ces tests obsolètes (au niveau des appels de méthodes ou des
paramètres donnés).

### Difficultés restant à résoudre

Il n'y a pas de difficultés significatives, si ce n'est que les changements
dans l'architecture perturbent quelques-uns d'entre nous. Ces changements ont
pour objectif de faciliter l'extension du jeu ainsi que sa maintenance, mais
ils rendent la structure du code moins "évidente" au premier abord en raison
de l'utilisation de design patterns.

## Livrable 4

### Atteinte des objectifs

La plupart des objectifs ont été atteints. Les spécifications du cahier des
charges sont remplies, ainsi que celles du rendu final.


### Difficultés restant à résoudre

Il reste quelques bugs à résoudre et quelques points peuvent encore être
améliorés. Mais cela n'empêche pas le bon fonctionnement du jeu. Le joueur
peut effectuer toutes les actions et gagner la partie.

# Journal de bord

## Semaine 1

- Tout le monde: diagramme UML de la partie labyrinthe
- Emma : commence à coder la classe Cell
- Damien et Rayan : commencent à coder Maze et WallOrientation 
- Theo : création de la classe KruskalMaze (a priori terminée) qui utilise
l'algorithme de Kruskal pour générer un labyrinthe, et d'une classe Random
pour générer des entiers aléatoires dans un intervalle

## Semaine 2

- Emma : finit classe Cell. Vérification et compréhension de KruskalMaze
- Rayan et Damien : finit removeWall, ExternalWall, nbCell, et commencement
du toString
- Theo : tests pour la classe Cell

## Semaine 3

- Emma : finit classe Cell, reflexion sur les tests de KruskalMaze et
réflexion sur DepthFirstSearchMaze
- Emma et Damien : ont décidé de faire une liste qui retient le chemin de mur
déjà visité dans  DepthFirstSearchMaze
- Theo : correction d'un bug lors de la génération des labyrinthes dû
à un probable d'index dans une liste lors de l'accès aux cellules

## Semaine 4 (vacances)

- Damien et Rayan : réalisation des tests pour les algos de labyrinthe
(KruskalMaze et DepthFirstSearchMaze) + Doc de Maze
- Emma : finit le code de DepthFirstSearchMaze
- Theo : documentation des classes, revue des tests

## Semaine 5

- Tout le monde : UML sur NonPlayerCharacter et ses enfants (Trader, Sphinx,
Fool et Altruist) ainsi que les items
- Emma : vérification des tests

## Semaine 6

- Emma : UML sur les Hint + code le début de ItemPositionHint
- Damien et Rayan : code les classes Player, Character, Item, Parchment et les
différents enum + réalisation des tests pour Player
- Theo : UML dans la globalité + début sur la classe NonPlayerCharacter et ses
enfants, ainsi que les classes FakeHint et InputReader. Correctifs sur Player
et ses tests

## Semaine 7

- Emma : ajout de look dans Player et testItem
- Theo : résolution de la partie Quest de l'UML, et création des classes
Quest/QuestCondition
- Rayan : classes Player et Character
- Damien : classes Jewel et Parchment

## Semaine 8

- Emma : test de tous les items (TestParchment et TestJewel)
- Damien et Rayan : TestPlayer + TestCharacter mise en place des methodes
toString() et indices de conditions de victoire
- Theo : petites modifications (ajouts de getters/setters, modifications
de signatures) et travail sur les tests

## Semaine 9

- Emma : dans Game, création de l'IHM (playTurn)
- Theo : recherches sur une manière de suivre l'évolution des objectifs
de quête à partir d'évènements dans la partie

## Semaine 10

- Tout le monde : mise au point sur l'architecture du projet
- Emma : création du HintGenerator.
- Theo : implémentation d'un Observer pattern pour les conditions de quêtes
et ajout d'un inventaire au player

## Semaine 11

- Theo : correction d'erreurs, avancée sur la génération du jeu (GameGenerator)
et travail de groupe pour refaire le point

## Semaine 12 (vacances)

- Theo : ajout d'un Makefile et réflexion sur la réorganisation du code pour
l'extensibilité du jeu
- Emma : travaille sur la méthode generateLevel

## Semaine 13 (vacances)

- Theo : modification de l'architecture du projet, avec notamment
l'implémentation des patterns Command, State et Update + réalisation de
l'interaction avec les items
- Emma : travaille sur le generateLevel

## Semaine 14

- Emma : ajout les tests des actions et les des hints
