package Vaganov;

import java.util.ArrayList;

/**
 * The board is used to store and work with tiles. It also has its own
 * coordinates to correctly display the visuals.
 * @author John Vaganov
 * 
 */
public class SquareBoard extends Board {
    
    /**
     * Uses GameInfo to initialize variables. Creates tiles and puts life on
     * them.
     */
    public SquareBoard() {
        totalTiles = GameInfo.getTotalTiles();
        sideLength = GameInfo.getSideLength();
        tiles = new Tile[totalTiles];
        
        initiateTiles();
        setUpLife();
    }
    
    protected void initiateTiles() {
        createTiles();
        setAdjacents();
    }
    
    protected void createTiles() {
     // creates tiles for each spot in the array
        int count = 0;
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                tiles[count] = new SquareTile(j, i, z, this);
                count++;
            }
        }
    }
    
    protected void setAdjacents() {
     // sets adjacent tiles
        for (Tile t : tiles) {
            ArrayList<Tile> adjacentTiles = new ArrayList<Tile>();
            x = t.getX();
            y = t.getY();
            for (Tile t2 : tiles) {
                // if tile is adjacent
                if ((Math.abs(x - t2.getX()) == 1 && Math.abs(y - t2.getY()) <= 1) ||
                        (Math.abs(x - t2.getX()) <=1 && Math.abs(y - t2.getY()) == 1)) {
                    // and if not already in the list
                    if (!adjacentTiles.contains(t2)) {
                        adjacentTiles.add(t2);
                    }
                    
                }
            }
            t.setAdjacentTiles(adjacentTiles);
        }
    }
    
}
