package Vaganov;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Tile {
    protected Board parentBoard;
    protected ArrayList<Tile> adjacentTiles;
    protected LifeForm occupant;
    protected LifeForm nextOccupant;
    protected LifeForm terrain;
    protected LifeForm nextTerrain;
    protected int x;
    protected int y;
    protected int z;
    
    protected abstract Shape makeBody();
    
    protected boolean isOffset() {
        return false;
    }
    
    /**
     * When the board instructs each tile to take action the tile calls its
     * occupan't action if it has one.
     */
    public void action() {
        if (terrain != null) {
            terrain.action();
        }
        if (occupant != null) {
            occupant.action();
        }
    }
    
    /**
     * Used to transition to the next occupant. Prevents overlapping of
     * life forms.
     */
    public void confirmTurn() {
        occupant = nextOccupant;
        terrain = nextTerrain;
    }
    
    public ArrayList<Tile> getAdjacentTiles() {
        return adjacentTiles;
    }
    
    /**
     * When a life form moves to the tile it is registered as the next
     * occupant, not current to prevent overlapping.
     * @param life the new occupant of the tile
     */
    public void newOccupant(LifeForm life) {
        nextOccupant = life;
    }
    
    public void newTerrain(LifeForm life) {
        nextTerrain = life;
    }
    
    /**
     * Clears the life from from the tile. Makes the tile empty.
     */
    public void removeOccupant() {
        nextOccupant = null;
    }
    
    /**
     * This is used by the board at the start to give each tile
     * its adjacent tiles
     * @param tiles the adjacent tiles to store
     */
    public void setAdjacentTiles(ArrayList<Tile> tiles) {
        adjacentTiles = tiles;
    }
    
    /**
     * Returns the color of the occupant. If no occupant returns white.
     * @return
     */
    public Color getColor() {
        if (occupant != null && occupant.getColor() != null) {
            return nextOccupant.getColor();
        }
        else if (terrain != null && terrain.getColor() != null) {
            return terrain.getColor();
        }
        else {
            return Color.WHITE;
        }
    }
    
    /**
     * Sets initial occupants of a tile.
     * @param life initial occupant of the tile
     */
    public void setOccupant(LifeForm life) { // for initial setup
        occupant = life;
        nextOccupant = life;
    }
    
    public void setTerrain(LifeForm life) { // for initial setup
        terrain = life;
        nextTerrain = life;
    }
    
    public LifeForm getOccupant() {
        return occupant;
    }
    
    public LifeForm getTerrain() {
        return terrain;
    }
    
    public LifeForm getNextOccupant() {
        return nextOccupant;
    }
    
    public LifeForm getNextTerrain() {
        return nextTerrain;
    }
    
    public boolean hasOccupant() {
        if (occupant == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getZ() {
        return z;
    }
    
    /**
     * Used for testing. Returns some tile attributes.
     */
    public String toString() {
        return "x:" + x + " y:" + y + " z:" + z + "  \toccupant:" + occupant
                + "\tnextOccupant:" + nextOccupant
                + "\tadjacentTiles registered: " + adjacentTiles.size()
                + "\toffset: " + isOffset();
    }
    
}
