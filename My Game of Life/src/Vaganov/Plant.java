package Vaganov;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import tags.HerbivoreEdible;
import tags.OmnivoreEdible;
import tags.Terrain;

/**
 * A plant can be eaten by a Herbivore. It can seed to adjacent tiles based on
 * how many tiles are open and how many other plans are round.
 * @author John Vaganov
 *
 */
public class Plant extends LifeForm implements HerbivoreEdible, OmnivoreEdible, Terrain{
    
    /**
     * Creates a plant on a tile and stores that tile. Also stores itself
     * on the tile as an occupant. Sets relevant requirements for procreation.
     * Green in color when instantiated.
     * @param t the tile to store as the current tile
     */
    public Plant(Tile t) {
        hunger = 0;
        hungerPerTurn = 0;
        maxHunger = 9;
        numOfOpenTilesReqToProcreate = 3; //3, 2
        numOfPlantTilesReqToProcreate = 2; //4, 2
        currentTile = t;
        color = Color.GREEN;
    }
    
    protected void moveTo(Tile t) {
        // does not move
    }
    
    protected boolean canMoveTo(Tile t) {
        return false;
    }
    
    
    protected void procreate() {
        ArrayList<Tile> adjTiles = currentTile.getAdjacentTiles();
        boolean allNulls = false; // used for the tilesToTry array
        int numOpenTiles = 0;
        int index = 0;
        
        // counts the number of open adjacent tiles
        for (Tile t : adjTiles) {
            if (t.getTerrain() == null && t.getOccupant() == null) {
                numOpenTiles++;
            }
        }
        
        // creates and fills the tilesToTry with open adjacent tiles
        Tile[] tilesToTry = new Tile[numOpenTiles];
        for (Tile t : adjTiles) {
            if (t.getTerrain() == null && t.getOccupant() == null) {
                tilesToTry[index] = t;
                index++;
            }
        }
        
        // while there are tiles to try, keep trying to seed to a random one
        if (tilesToTry.length > 0) {
            while (!allNulls) {
                index = RandomGenerator.nextNumber(tilesToTry.length);
                
                // if can seed, seeds
                if (this.canProcreateTo(tilesToTry[index])) {
                    procreateTo(tilesToTry[index]);
                    break;
                } else {
                    tilesToTry[index] = null;
                }
                
                // updates the condition
                allNulls = true;
                for (Tile t : tilesToTry) {
                    if (t != null) {
                        allNulls = false;
                    }
                }
                
            }
        }
    }
    
    
    
    protected void procreateTo(Tile t) {
        t.newTerrain(new Plant(t));
    }
    
}
