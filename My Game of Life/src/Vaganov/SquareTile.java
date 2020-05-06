package Vaganov;

import javafx.scene.shape.Rectangle;

/**
 * A tile stores its adjacent tiles. It has an occupant and a next occupant.
 * the next occupant is to ensure the life forms do not over write each other 
 * when making their action. Also has coordinates.
 * @author John Vaganov
 *
 */
public class SquareTile extends Tile {
    
    /**
     * When a tile is created it is given coordinates and a parent board.
     * @param xx the x coordinate
     * @param yy the y coordinate
     * @param zz the z coordinate
     */
    public SquareTile(int xx, int yy, int zz, Board b) {
        parentBoard = b;
        x = xx;
        y = yy;
        z = zz;
    }
    
    /**
     * Used to make a tile image. Converts its attributes along with the
     * game info into a shape.
     * @param t tile to visualize
     * @return tile image as a Rectangle
     */
    public Rectangle makeBody() {
        int boardZ = parentBoard.getZ();
        int zOffset = GameInfo.getZOffset();
        double xScale = GameInfo.getXScale();
        double yScale = GameInfo.getYScale();
        
        Rectangle r = new Rectangle(
                x*xScale+zOffset*boardZ+1, y*yScale+1,
                xScale-1, yScale-1);
        r.setFill(getColor());
        
        return r;
    }
    
}
