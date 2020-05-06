package Vaganov;

import java.util.Scanner;

/**
 * This is the start of the whole program. A new game is created and started.
 * @author John Vaganov
 *
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int board = -1;
        int sideLength;
        int defaultSideLength = 50;
        boolean valid = false;
        
        while (!valid) {
            System.out.print("Please enter 1 for a hex board or 0 for a"
                    + " regular board: ");
            try {
                board = Integer.parseInt(scan.nextLine());
                if (board == 1 || board == 0) {
                    valid = true;
                }
            } catch (Exception e) {
                valid = false;
            }
            
        }
        
        System.out.print("\nEnter the side length of the board in tiles"
                + "(optional). The recommended size is between 25 and 100: ");
        try {
            sideLength = Integer.parseInt(scan.nextLine());
            System.out.println("Setting side length to " + sideLength);
        } catch (Exception e) {
            System.out.println("Input is not a number. Side length to " + defaultSideLength);
            sideLength = defaultSideLength;
        }
        if (sideLength < 1 || sideLength > 150) {
            System.out.println("Input is out of valid range. Side length to " + defaultSideLength);
            sideLength = defaultSideLength;
        }
        
        scan.close();
        
        System.out.print("\nStarting game. Please open the game tab.");
        Game g1 = new GameOfLife(board, sideLength);
        g1.start();
    }
    
}
