package Vaganov;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * ClickCatched defines the mouse event used to handle mouse clicks on the
 * active board. Used for advancing to the next turn.
 * @author John Vaganov
 *
 */
public abstract class ClickCatcher {
    private static Board activeBoard;
    
    /**
     * EventHandler, listens for a click. When the active board is clicked it
     * starts to update all its tiles. Once all the tiles are updated the
     * Visuals draw the board again.
     */
    private static EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent arg0){
            //try{
                activeBoard.updateTiles();
                Visuals.updateTurn(activeBoard);
                
//            } catch (Exception except) {
//                System.out.println("Exception in ClickCatcher: " + except);
//            }
        }
    };
    
    /**
     * Used to get the mouse event and attach it to the visual group. For
     * example, if a Rectangle has this event handler and is clicked the
     * event will be triggered.
     * @return
     */
    public static EventHandler<MouseEvent> getEventHandler() {
        return mouseEvent;
    }
    
    /**
     * Used to specify which board to update upon click. Used once initially
     * at the start of the game.
     * @param b the board to update on click
     */
    public static void use(Board b) {
        activeBoard = b;
    }
    
}
