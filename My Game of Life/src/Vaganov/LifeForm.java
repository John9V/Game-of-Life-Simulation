package Vaganov;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import tags.Animal;
import tags.CarnivoreEdible;
import tags.OmnivoreEdible;
import tags.Terrain;

/**
 * An abstract form of life. Has color and a tile that it is on.
 * Also can perform an action.
 * @author John Vaganov
 *
 */
public abstract class LifeForm {
    protected Tile currentTile;
    protected Color color;
    protected int hunger;
    protected int maxHunger;
    protected int hungerPerTurn = 1;
    protected int numOfOpenTilesReqToProcreate;
    protected int numOfPlantTilesReqToProcreate;
    protected int numOfHerbivoreTilesReqToProcreate;
    protected int numOfCarnivoreTilesReqToProcreate;
    protected int numOfCarnivoreFoodTiles;
    protected int numOfOmnivoreTilesReqToProcreate;
    protected int numOfOmnivoreFoodTiles;
    
    /**
     * Specifies what to do when its time to take a turn using adjacent tiles.
     */
    public void action() {
        if (hunger >= maxHunger) {
            die();
        } else {
            procreate();
            move();
        }
    }
    
    /**
     * Changes the stored values of the Herbivore and the tiles to represent
     * the move.
     * @param t tile to move to
     */
    protected abstract void moveTo(Tile t);
    
    /**
     * Checks conditions for the move and returns a boolean value.
     * @param t tile to check
     * @return boolean value: true if can go to tile, false if can not
     */
    protected abstract boolean canMoveTo(Tile t);
    
    /**
     * Transitions the Herbivore to another tile.
     */
    protected void move() {
        ArrayList<Tile> adjTiles = currentTile.getAdjacentTiles();
        Tile chosenTile; // the tile chosen to test for movement
        int index = 0;
        
        for (int i = 0; i < 10; i++) {
            index = RandomGenerator.nextNumber(18);
            
            // the index might be out of bound
            try {
                chosenTile = adjTiles.get(index);
            } catch (Exception e) {
                chosenTile = null; // null if could not choose tile
            }
            
            if (this.canMoveTo(chosenTile)) {
                moveTo(chosenTile);
                break;
            }
        }
        
        hunger+=hungerPerTurn;
    }
    
    /**
     * Transfers life to the specified tile.
     * @param t the tile to move to.
     */
    protected void completeMove(Tile t) {
        t.newOccupant(this);
        currentTile.removeOccupant();
        currentTile = t;
    }
    
    /**
     * creates a new instance of the life form on the specified tile.
     * @param t the tile to spread to.
     */
    protected abstract void procreateTo(Tile t);
    
    /**
     * Calculates the number of life forms in adjacent tiles and compares it to the requirements needed to procreate.
     * @param toTile the to procreate to.
     * @return true if can procreate to the tile, false if can not.
     */
    protected boolean canProcreateTo(Tile toTile) {
        int numPlantTiles = 0;
        int numOpenTiles = 0;
        int numHerbivoreTiles = 0;
        int numCarnivoreTiles = 0;
        int numCarnivoreFood = 0;
        int numOmnivoreTiles = 0;
        int numOmnivoreFood = 0;
        
        // this catches any null pointers from before
        if (toTile == null) {
            return false;
        }
        
        ArrayList<Tile> adjTiles = currentTile.getAdjacentTiles();
        
        for (Tile t : adjTiles) {
            if (t.getTerrain() instanceof Plant) {
                numPlantTiles++;
            }
            if (t.getOccupant() instanceof Herbivore) {
                numHerbivoreTiles++;
            }
            if (t.getOccupant() instanceof Carnivore) {
                numCarnivoreTiles++;
            }
            if (t.getNextTerrain() == null && t.getNextOccupant() == null) {
                numOpenTiles++;
            }
            if (t.getNextTerrain() instanceof CarnivoreEdible || t.getNextOccupant() instanceof CarnivoreEdible) {
                numCarnivoreFood++;
            }
            if (t.getOccupant() instanceof Omnivore) {
                numOmnivoreTiles++;
            }
            if (t.getNextTerrain() instanceof OmnivoreEdible || t.getNextOccupant() instanceof OmnivoreEdible) {
                numOmnivoreFood++;
            }
        }
        
        if ((this instanceof Terrain && toTile.getNextTerrain() == null)
                && numPlantTiles >= numOfPlantTilesReqToProcreate
                && numOpenTiles >= numOfOpenTilesReqToProcreate) {
            return true;
        } else if ((this instanceof Animal && toTile.getNextOccupant() == null)
                && numPlantTiles >= numOfPlantTilesReqToProcreate
                && numOpenTiles >= numOfOpenTilesReqToProcreate
                && numHerbivoreTiles >= numOfHerbivoreTilesReqToProcreate
                && numCarnivoreTiles >= numOfCarnivoreTilesReqToProcreate
                && numCarnivoreFood >= numOfCarnivoreFoodTiles
                && numOmnivoreTiles >= numOfOmnivoreTilesReqToProcreate
                && numOmnivoreFood >= numOfOmnivoreFoodTiles) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks adjacent tiles to procteate.
     */
    protected void procreate() {
        ArrayList<Tile> adjTiles = currentTile.getAdjacentTiles();
        Tile chosenTile; // the tile chosen to test for movement
        int index = 0;
        
        for (int i = 0; i < 10; i++) {
            index = RandomGenerator.nextNumber(18);
            
            // the index might be out of bound
            try {
                chosenTile = adjTiles.get(index);
            } catch (Exception e) {
                chosenTile = null; // null if could not choose tile
            }
            
            if (this.canProcreateTo(chosenTile)) {
                procreateTo(chosenTile);
                break;
            }
        }
        
    }
    
    /**
     * Specifies how to die. Removes the Herbivore self from the current tile
     * and sets its current tile as null which is not on the board.
     */
    public void die() {
        if (currentTile != null && currentTile.getOccupant() != null) {
            currentTile.removeOccupant();
        }
        currentTile = null;
    }
    
    public Color getColor() {
        return color;
    }
    
}