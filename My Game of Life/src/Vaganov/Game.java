package Vaganov;

/**
 * An abstract game. Exists in case another version of the game is needed.
 * Can be ensure functionality of all future variations of the game.
 * @author John Vaganov
 *
 */
public abstract class Game {
    
    /**
     * Should specify what to do at the very start of the game and which
     * classes to use and link.
     */
    public abstract void start();
    
}
