package Vaganov;

import java.util.ArrayList;

public class HexBoard extends Board{
    
    /**
     * Uses GameInfo to initialize variables. Creates tiles and puts life on
     * them.
     */
    public HexBoard() {
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
                tiles[count] = new HexTile(j, i, z, this);
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
                if ((Math.abs(x - t2.getX()) == 1 && Math.abs(y - t2.getY()) == 0)
                        || (Math.abs(x - t2.getX()) == 0 && Math.abs(y - t2.getY()) == 1)
                        || (x - t2.getX() == -1 && Math.abs(y - t2.getY()) == 1 && t.isOffset())
                        || (x - t2.getX() == 1 && Math.abs(y - t2.getY()) == 1 && !t.isOffset())) {
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
