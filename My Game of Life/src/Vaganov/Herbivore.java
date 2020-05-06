package Vaganov;

import javafx.scene.paint.Color;
import tags.Animal;
import tags.CarnivoreEdible;
import tags.HerbivoreEdible;
import tags.OmnivoreEdible;

/**
 * Herbivore is a LifeForm that has hunger, can move, and can die.
 * @author John Vaganov
 *
 */
public class Herbivore extends LifeForm implements Animal, CarnivoreEdible, OmnivoreEdible {
    
    /**
     * Creates a herbivore on a tile and stores that tile. Also stores itself
     * on the tile as an occupant. Sets relevant requirements for procreation.
     * yellow in color when instantiated.
     * @param t the tile to store as the current tile
     */
    public Herbivore(Tile t) {
        numOfOpenTilesReqToProcreate = 2;//4, 2
        numOfPlantTilesReqToProcreate = 2;//3, 2
        numOfHerbivoreTilesReqToProcreate = 1;
        maxHunger = 5;
        hunger = 0;
        currentTile = t;
        color = Color.YELLOW;
    }
    
    protected void moveTo(Tile t) {
        if (t.getNextOccupant() instanceof HerbivoreEdible) {
            hunger = 0;
        }
        if (t.getNextTerrain() instanceof HerbivoreEdible) {
            hunger = 0;
            t.newTerrain(null);
        }
        completeMove(t);
    }
    
    protected boolean canMoveTo(Tile t) {
        if (t == null) { // this catches any null pointers from before
            return false;
        }
        
        if ((t.getNextOccupant() == null)) {
            return true;
        } else {
            return false;
        }
    }
    
    protected void procreateTo(Tile t) {
        t.newOccupant(new Herbivore(t));
    }
    
    
    
}
