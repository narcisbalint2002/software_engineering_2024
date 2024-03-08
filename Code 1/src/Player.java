import java.util.Scanner;

public class Player {

    // number of rays this stupid player has shot
    int num_rays = 0;

    // dumb players score
    int score;



    public Player() {
        // does nothing
    }

    // FUTURE IDEA: could be a function that is hooked up to a button on screen to do final guesses, then when
    // clicked returns true, which causes user to go into state of choosing atom coordinates to guess (by clicking
    // their hexagons on board)
    public boolean playerGuess() {

        // player choose whether want to guess final atoms
        Scanner user_input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Final guess of atoms? ");
        String end_game = user_input.nextLine();

        if (end_game.equals("y")) {
            return true;
        }
        else {
            return false;
        }
    }

}
