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
-Maze (la classe parente de KruskalMaze et DepthFirstSearchMaze)  
-KruskalMaze  
-DepthFirstSearchMaze  
-Cell  
-Random

On a les exceptions:
InvalidAdjacentCellException : on essaie d'enlever un mur entre 2 cases non adjacentes.

On a une enum:  
-WallOrientation

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
Les objectifs sont atteints : Les 2 algorithmes (Kruskal et Depth First Search) fonctionnent correctement et les tests sont valides.

### Difficultés restant à résoudre
Aucune

## Livrable 2

### Atteinte des objectifs

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

## Semaine 3
Emma: finit classe Cell, reflexion sur les test de KruskalMaze et reflexion sur DepthFirstSearchMaze
Emma et Damien ont décidé de:  "on fait une liste qui retient le chemin de mur déjà visité" dans  DepthFirstSearchMaze

## Semaine 4 (vacances)
Damien et Rayan : Realisation des tests pour les algos de labyrinthe (KruskalMaze et DepthFirstSearchMaze) + Doc de Maze
Emma : finit le code de DepthFirstSearchMaze.

## Semaine 5
Emma verification des tests.
Tout le monde: UML sur les NonePlayerCharacter

## Semaine 6

## Semaine 7

## Semaine 8

## Semaine 9

## Semaine 10

## Semaine 11

## Semaine 12
