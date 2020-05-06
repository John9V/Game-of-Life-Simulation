package Vaganov;

import javafx.scene.shape.Polygon;

/**
 * A tile stores its adjacent tiles. It has an occupant and a next occupant.
 * the next occupant is to ensure the life forms do not over write each other 
 * when making their action. Also has coordinates.
 * @author John Vaganov
 *
 */
public class HexTile extends Tile {
    boolean offsetToRight;
    static double r;
    
    /**
     * When a tile is created it is given coordinates and a parent board.
     * @param xx the x coordinate
     * @param yy the y coordinate
     * @param zz the z coordinate
     */
    public HexTile(int xx, int yy, int zz, Board b) {
        parentBoard = b;
        x = xx;
        y = yy;
        z = zz;
        if (y%2 == 0) {
            offsetToRight = false;
        } else {
            offsetToRight = true;
        }
    }
    
    /**
     * Used to make a tile image. Converts its attributes along with the
     * game info into a shape.
     * @param t tile to visualize
     * @return tile image as a Rectangle
     */
    public Polygon makeBody() {
        
        
        double scale = 14.1;
        double tileScale = GameInfo.getScale();
        r = scale*tileScale; // the inner radius from hexagon center to outer corner
        final double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
        final double TILE_HEIGHT = 2 * r;
        final double TILE_WIDTH = 2 * n;
        
        GameInfo.addToCanvasWidth(r);
        
        double scaleFactorX = (scale/10*0.67);
        double scaleFactorY = (scale/10*0.6);
        double xShift = (scale/10*9.111111);
        
        double xScale = GameInfo.getXScale();
        double yScale = (GameInfo.getYScale()*scaleFactorY)*(1.05);
        
        double xOffset = ((y%2) * xShift + 1)*tileScale;
        double yOffset = 12*tileScale;
        
        Polygon p = new Polygon(
                x*xScale+xOffset, y*yScale+yOffset,
                x*xScale+xOffset, y*yScale+yOffset + r,
                x*xScale+xOffset + n, y*yScale+yOffset + r * 1.5,
                x*xScale+xOffset + TILE_WIDTH, y*yScale+yOffset + r,
                x*xScale+xOffset + TILE_WIDTH, y*yScale+yOffset,
                x*xScale+xOffset + n, y*yScale+yOffset - r * 0.5);
        p.setFill(getColor());
        
        return p;
    }
    
    static double getRadius() {
        return r;
    }
    
    /**
     * Returns true for every second row.
     */
    protected boolean isOffset() {
        if (offsetToRight) {
            return true;
        } else {
            return false;
        }
    }
    
}
