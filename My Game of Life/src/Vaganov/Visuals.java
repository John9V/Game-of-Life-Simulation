package Vaganov;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Handles all visual JavaFX related tasks.
 * @author John Vaganov
 *
 */
public class Visuals extends Application {
    private static Board boardToDraw;
    static double width = GameInfo.getCanvasWidth();
    
    /**
     * Displays the chosen board.
     * @param b chosen board
     */
    public static void draw(Board b) {
        boardToDraw = b;
        launch();
    }
    
    /**
     * Erases the current snapshot of the game and draws the next turn.
     * @param b board to update
     */
    public static void updateTurn(Board b) {
        GameInfo.getCanvas().getChildren().clear(); // clears current image
        
        //double width = GameInfo.getCanvasWidth();
        double height = GameInfo.getCanvasHeight();
        Rectangle background = new Rectangle(width+1, height+1, Color.BLACK);
        GameInfo.getCanvas().getChildren().add(background);
        
        // draws the new tiles
        Tile[] tiles = b.getTiles();
        for (Tile t : tiles) {
            Shape hitbox = t.makeBody();
            GameInfo.getCanvas().getChildren().add(hitbox);
        }
    }
    
    /**
     * Initial display of the board.
     */
    public void start(Stage primaryStage) throws Exception {
        Tile[] tiles = boardToDraw.getTiles();
        double width = GameInfo.getCanvasWidth();
        double height = GameInfo.getCanvasHeight();
        
        Rectangle background = new Rectangle(width+1, height+1, Color.BLACK);
        GameInfo.getCanvas().getChildren().add(background);
        
        // creates and adds tiles to the playing field
        for (Tile t : tiles) {
            Shape hitbox = t.makeBody();
            GameInfo.getCanvas().getChildren().add(hitbox);
        }
        
        // sets an event listener on the whole playing field
        GameInfo.getCanvas().setOnMousePressed(ClickCatcher.getEventHandler());
        
        Scene scene = new Scene(GameInfo.getCanvas(), width, height, Color.LIGHTBLUE);
        primaryStage.setTitle("John Vaganov: My Game Of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
