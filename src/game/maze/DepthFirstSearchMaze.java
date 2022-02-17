package game.maze;

import game.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//on fait une pile qui retient le chemin de mur déjà visité
public class DepthFirstSearchMaze extends Maze {

    private Map <Cell, Boolean> AreVisited;

    public DepthFirstSearchMaze(int length, int height){
        super(length, height);
        this.AreVisited = new HashMap <Cell, Boolean>();
        for(int i = 0; i < length; i++ ){
            for(int j = 0; j < height; j++){
              AreVisited.put(this.cells[i][j], false);
            }
        }
    }

    /**
    *return the neighboring cells which are not already visited
    *@param cell : the cell we want the neighboring cells
    *@return the neighboring cells of the cell "cell" which are not already visited
    */

    private List<Cell> neighboringCells(Cell cell){
      List<Cell> neighboring = new ArrayList <Cell> ();
      if (cell.getY()!=0){//si la cellule est pas en haut, on ajoute l'index du dessus
        //la cellule du dessus n'a pas déjà eté visité
        //Cell cellFrontOf=this.cells[cell.getX()-1][cell.getY()];
        Cell cellFrontOf=this.getCell(cell.getX(),cell.getY()-1);
        if (! AreVisited.get(cellFrontOf)){
            neighboring.add(cellFrontOf);
        }
      }
      if (cell.getY()!=this.height-1){//si la cellule est pas en bas, on ajoute l'index du dessous
        Cell cellFrontOf=this.getCell(cell.getX(),cell.getY()+1);
        if (! AreVisited.get(cellFrontOf)){
            neighboring.add(cellFrontOf);
          }
      }

      if (cell.getX()!=0){//si la cellule est pas à gauche du tableau, on ajoute la cellule de gauche
        Cell cellFrontOf=this.getCell(cell.getX()-1,cell.getY());
        if (! AreVisited.get(cellFrontOf)){
            neighboring.add(cellFrontOf);
          }
      }

      if (cell.getX()!=this.length-1){//si la cellule est pas tout à droite du tableau, on ajoute la cellule de droite
        Cell cellFrontOf=this.getCell(cell.getX()+1,cell.getY());
        if (! AreVisited.get(cellFrontOf)){
            neighboring.add(cellFrontOf);
          }
      }
    return neighboring;
    }



    protected void generate(){
      int x = new Random().nextInt(this.length);
      int y = new Random().nextInt(this.height);
      Cell actualCell= this.getCell(x,y)



    //on crée une hashmap avec comme clé la cellule et comme valeur un boolean qui nous dit si elle a déjà été visité ou non
    //on créer une liste de cellule possible
    //on choisit une cellule parmis la liste
    //on la met dans la pile LIFO cellule visité et on met dans le hashmap qu'elle est visité
    //on regarde les cellules possible autour

    //on choisit une cellule parmi celle possible
    //on detruit le mur entre les 2

    //si aucune cellule possible on remonte à la dernière cellule observé (dans notre magnifique pile toute bien faites)
    }
}
