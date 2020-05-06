package Vaganov;

/**
 * A general board that sets up life.
 * @author John
 *
 */
public abstract class Board {
    protected int totalTiles;
    protected int sideLength;
    protected Tile[] tiles;
    protected int x;
    protected int y;
    protected int z;
    
    /**
     * This creates tiles using appropriate coordinates and stores them.
     */
    protected abstract void initiateTiles();
    
    /**
     * Creates tiles and stores them in an array.
     */
    protected abstract void createTiles();
    
    /**
     * Sets adjacent tiles for each tile. Each tile will store its
     * adjacent tiles.
     */
    protected abstract void setAdjacents();
    
    /**
     * Uses the RandomGenerator class to put life on tiles.
     */
    protected void setUpLife() {
        for (Tile t : tiles) {
            int chance = RandomGenerator.nextNumber(99);
            if (chance >= 80) { // 20% chance
                t.setOccupant(new Herbivore(t));
            } else if (chance >= 60) { // 20% chance
                t.setTerrain(new Plant(t));
            } else if (chance >= 50) { // 10% chance
                t.setOccupant(new Carnivore(t));
            } else if (chance >= 45) { // 5% chance
                t.setOccupant(new Omnivore(t));
            }
        }
    }
    
    /**
     * Calls action for all tiles and after all tiles made an action the turn
     * is confirmed. When a turn is confirmed it means the tile attributes are
     * updated. For example the next occupant becomes the current occupant.
     */
    protected void updateTiles() {
        for (Tile t : tiles) {
            t.action();
        }
        for (Tile t : tiles) {
            t.confirmTurn();
        }
    }
    
    /**
     * Returns the tile array stored in this board.
     * @return Tile[] of the board
     */
    public Tile[] getTiles() {
        return tiles;
    }
    
    /**
     * Returns the x coordinate of the board.
     * @return x coordinate of the board
     */
    public int getX() {
        return x;
    }
    
    /**
     * Returns the y coordinate of the board.
     * @return y coordinate of the board
     */
    public int getY() {
        return y;
    }
    
    /**
     * Returns the z coordinate of the board.
     * @return z coordinate of the board
     */
    public int getZ() {
        return z;
    }
    
    /**
     * Used for testing and debugging. Prints the toString of all the tiles
     * on the board.
     */
    public void printTiles() {
        int count = 0;
        for (Tile t : tiles) {
            System.out.println("" + count + "  " + t.toString());
            count++;
        }
    }
    
}
