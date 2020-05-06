package Vaganov;

import javafx.scene.paint.Color;
import tags.Animal;
import tags.CarnivoreEdible;
import tags.OmnivoreEdible;

public class Carnivore extends LifeForm implements Animal, OmnivoreEdible {
    
    /**
     * Creates a carnivore on a tile and stores that tile. Also stores itself
     * on the tile as an occupant. Sets relevant requirements for procreation.
     * Red in color when instantiated.
     * @param t the tile to store as the current tile
     */
    public Carnivore(Tile t) {
        numOfOpenTilesReqToProcreate = 3;//4, 3
        numOfCarnivoreTilesReqToProcreate = 1;
        numOfCarnivoreFoodTiles = 2;
        maxHunger = 5;
        hunger = 0;
        currentTile = t;
        color = Color.RED;
    }
    
    protected void moveTo(Tile t) {
        if (t.getNextOccupant() instanceof CarnivoreEdible) {
            hunger = 0;
        }
        if (t.getNextTerrain() instanceof CarnivoreEdible) {
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
                || t.getNextOccupant() instanceof CarnivoreEdible)) {
            return true;
        } else {
            return false;
        }
    }
    
    protected void procreateTo(Tile t) {
        t.newOccupant(new Carnivore(t));
    }
    
}
