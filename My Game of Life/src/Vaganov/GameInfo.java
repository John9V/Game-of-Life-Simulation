package Vaganov;

import javafx.scene.Group;

/**
 * Contains relevant information about visually displaying the board and
 * its tiles.
 * @author John Vaganov
 *
 */
public class GameInfo {
    private static boolean adjestedWidth = false;
    private static Group canvas = new Group();
    private static int sideLength = 25;
    private static int totalTiles = sideLength * sideLength;
    private static int globalZOffset = 700;
    private static double canvasWidth = 675;
    private static double canvasHeight = 650;
    private static double tileWidth = canvasWidth / sideLength;
    private static double tileHeight = canvasHeight / sideLength;
    private static double xSpacing = (canvasWidth-1) / tileWidth;
    private static double ySpacing = (canvasHeight-1) / tileHeight;
    private static double scale = canvasHeight / sideLength / 25;
    
    public static void setSideLength(int num) {
        sideLength = num;
        totalTiles = sideLength * sideLength;
        scale = canvasHeight / sideLength / 25;
        tileWidth = (canvasWidth-1) / sideLength;
        tileHeight = (canvasHeight-1) / sideLength;
        xSpacing = tileWidth;
        ySpacing = tileHeight;
    }
    
    public static int getSideLength() {
        return sideLength;
    }
    
    public static double getScale() {
        return scale;
    }
    
    public static Group getCanvas() {
        return canvas;
    }
    
    public static int getTotalTiles() {
        return totalTiles;
    }
    
    public static int getZOffset() {
        return globalZOffset;
    }
    
    public static double getCanvasWidth() {
        return canvasWidth;
    }
    
    public static void addToCanvasWidth(double width) {
        if (!adjestedWidth) {
            canvasWidth -= width;
            tileWidth = (canvasWidth-1) / sideLength;
            tileHeight = (canvasHeight-1) / sideLength;
            xSpacing = tileWidth;
            ySpacing = tileHeight;
            adjestedWidth = true;
        }
        
    }
    
    public static double getCanvasHeight() {
        return canvasHeight;
    }
    
    public static double getXScale() {
        return xSpacing;
    }
    
    public static double getYScale() {
        return ySpacing;
    }
    
}
