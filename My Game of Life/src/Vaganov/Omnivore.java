package Vaganov;

import javafx.scene.paint.Color;
import tags.Animal;
import tags.OmnivoreEdible;

public class Omnivore extends LifeForm implements Animal {
    
    /**
     * Creates an omnivore on a tile and stores that tile. Also stores itself
     * on the tile as an occupant. Sets relevant requirements for procreation.
     * Blue in color when instantiated.
     * @param t the tile to store as the current tile
     */
    public Omnivore(Tile t) {
        numOfOpenTilesReqToProcreate = 3;
        numOfOmnivoreTilesReqToProcreate = 1;
        numOfOmnivoreFoodTiles = 1;
        maxHunger = 5;
        hunger = 0;
        currentTile = t;
        color = Color.BLUE;
    }
    
    protected void moveTo(Tile t) {
        if (t.getNextOccupant() instanceof OmnivoreEdible) {
            hunger = 0;
        }
        if (t.getNextTerrain() instanceof OmnivoreEdible) {
            hunger = 0;
            t.newTerrain(null);
        }
        completeMove(t);
    }
    
    protected boolean canMoveTo(Tile t) {
        if (t == null) { // this catches any null pointers from before
            return false;
        }
        
        if ((t.getNextOccupant() == null
                || t.getNextOccupant() instanceof OmnivoreEdible)) {
            return true;
        } else {
            return false;
        }
    }
    
    protected void procreateTo(Tile t) {
        t.newOccupant(new Omnivore(t));
    }
    
}
