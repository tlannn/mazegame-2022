package game.item;


public class Character {
    
    private Cell currentCell;
    protected String name;

    public Character(){

    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void setCurentCell(Cell cell){
        this.currentCell = cell;
    }
}
