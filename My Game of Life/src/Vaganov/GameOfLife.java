package Vaganov;

/**
 * Has a board. Links the ClickCatcher and Visuals to the board when started.
 * @author John Vaganov
 *
 */
public class GameOfLife extends Game {
    private Board board;
    
    public GameOfLife(int choice, int sidelength) {
        GameInfo.setSideLength(sidelength);
        if (choice == 0) {
            board = new SquareBoard();
        } else {
            board = new HexBoard();
        }
    }
    
    public void start() {
        ClickCatcher.use(board);
        Visuals.draw(board);
    }
    
}
