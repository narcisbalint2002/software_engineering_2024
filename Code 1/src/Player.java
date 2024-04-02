import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    // number of rays this stupid player has shot (can just be calculated from length of ray_list)
    int num_rays;

    // dumb players score
    int score;


    // list of rays (each ray has entrance and exit points)
    ArrayList<RayManager> ray_list = new ArrayList<>();



    public Player() {
        // player starts with no score
        score = 0;
    }

    public boolean edgeCheck(int edge_num) {
        // checks through all edges ray has been shot from, if matches, returns true, otherwise edge not used yet
        for (int i = 0; i < ray_list.size(); i++) {
            if ((edge_num == ray_list.get(i).ray_entrance_edge) || (edge_num == ray_list.get(i).ray_exit_edge)) {
                return true;
            }
        }
        return false;
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
