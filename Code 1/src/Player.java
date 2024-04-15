import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    // number of rays this stupid player has shot (can just be calculated from length of ray_list)
    int num_rays;

    // dumb players score
    int score;

    // by default is -1
    static Coordinate current_atom = new Coordinate();

    ArrayList<Coordinate> player_atoms = new ArrayList<>();


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

    public boolean atomCheck(ArrayList<Atom> atoms, Coordinate atom_coords) {
        // checks through all atoms in list, if coords already there, return true, because atom ALREADY guessed
        for (int i = 0; i < atoms.size(); i++) {
            if ((atom_coords.getX() == atoms.get(i).getX()) && (atom_coords.getY() == atoms.get(i).getY())) {
                return true;
            }
        }
        return false;
    }

    // FUTURE IDEA: could be a function that is hooked up to a button on screen to do final guesses, then when
    // clicked returns true, which causes user to go into state of choosing atom coordinates to guess (by clicking
    // their hexagons on board)
    public boolean playerGuess(ArrayList<Atom> atoms) {

        int atoms_guessed = 0;
        // run loop while not all atoms guessed
        while (atoms_guessed < Main.NUM_ATOMS) {
            // player choice for atoms
            Scanner final_guess = new Scanner(System.in);  // Create a Scanner object

            // if we did get a new atom guess
            if ((current_atom.x != -1) && (current_atom.y != -1)) {
                // need check for if already guessed atom
                if (atomCheck(atoms, current_atom)) {
                    current_atom.x = -1;
                    current_atom.y = -1;
                } else {
                    player_atoms.add(current_atom);
                    atoms_guessed++;
                }
            }

        }


        return false;


//        // player choose whether want to guess final atoms
//        Scanner user_input = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Final guess of atoms? ");
//        String end_game = user_input.nextLine();
//
//        if (end_game.equals("y")) {
//            return true;
//        }
//        else {
//            return false;
//        }
    }

}
