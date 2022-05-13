package game.maze;

/**
 * Enumeration for cardinal points
 */
public enum Orientation {
    NORTH("North"), SOUTH("South"), EAST("East"), WEST("West");

    private String string;
    
    Orientation(String name){
        this.string = name;
    }

    @Override
    public String toString(){
        return this.string;
    }
    
}


