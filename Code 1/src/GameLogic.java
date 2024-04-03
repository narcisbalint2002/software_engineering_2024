import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {


    // variable used in gameBoard for mouse event click for current edge
    public static int ongoing_input;

    private GameLogic() {
        throw new AssertionError("GameLogic should not be instantiated");
    }

    private static void cycleScale() {
        // double scale until too large
        if ((Main.scale > 4.0) || ((Main.scale * 1000) > Main.width)) {
            Main.scale = 0.25;
        } else {
            Main.scale *= 2;
        }
    }

    public static void gameLoop() {
        // at start, games played is 0
        int games_played = 0;


        // all players in the game (to compare score at the end)
        ArrayList<Player> players = new ArrayList<>();

        AtomManager atom_manager = new AtomManager();




        // CHANGE TO HOWEVER MANY PLAYERS THERE ARE (RIGHT NOW 1 FOR TESTING ONLY 1 GAME)
        while (games_played < 1) {

            Player current_player = new Player();
            boolean final_guess = false; // while not final guess of ALL atom positions on board


            // create board with all lists and nodes
            ArrayList<Board.List> board = new ArrayList<>();
            Board.initialiseBoard(board);

            // terminal printing (for debugging)
            Board.printBoard(board);

            //// set all atoms in board
//            Board.setAtoms(board);
            System.out.println(atom_manager);

//            // terminal printing (for debugging)
//            Board.printBoard(board);


            EdgeManager board_edge_list = new EdgeManager();


            // // here you can create atoms manually for playing around with
//            atom_manager.createAtom(1,1);
//            atom_manager.createAtom(3,-3);
//            atom_manager.createAtom(7,-3);
//            atom_manager.createAtom(7,1);
//            atom_manager.createAtom(4,4);


            // runs while user does not have final guess of atom coordinates
            while(!final_guess) {

                // // OLD CODE, can delete once made sure all else works well
//                // player choose whether want to guess final atoms
//                Scanner user_input = new Scanner(System.in);  // Create a Scanner object
//                System.out.println("Edge number (as per diagrams)? ");
//                int edge_index = Integer.parseInt(user_input.nextLine()) - 1; // subtracting 1 to change from 1-54 to 0-53 (for indexing)
                ongoing_input = -1;

                // waits for user input, this will update when mouse click event for edge is triggered
                // !!WIP!!
                // need to also implement a check for if the edge was already chosen,
                //  OR
                // can instead just grey it out and remove its ability to be clicked in Swing file
                // (this option may be better)
                while (ongoing_input == -1) {
                    // player choose whether want to guess final atoms
                    Scanner user_input = new Scanner(System.in);  // Create a Scanner object

                    // to ensure new edge inputted is not already an entrance/exit point
                    if ((ongoing_input > 0) && (current_player.edgeCheck(ongoing_input))) {
                        ongoing_input = -1;
                    }
                    if (ongoing_input == -3) {
                        cycleScale();
                        ongoing_input = -1;
                    }

                }

                // if player clicked button during above loop to input final guess of all atoms
                if (ongoing_input == -2) {
                    System.out.printf("\n\n!!Final guess of atoms for player %d!!", games_played + 1);
                    break;
                }

                // current edge number (as per diagrams) used to get edge (by subtracting 1 to get edge index)
                Edge current_edge = board_edge_list.getEdge(ongoing_input - 1);

                // works
                System.out.println(current_edge.toString());


                // create a new manager for the ray from specified edge and calculate its path (i.e. entrance and exit)
                RayManager current_ray = new RayManager(current_edge);
                current_ray.rayPath(board_edge_list, atom_manager);





                // a ray has been shot so add it to list of rays player had (for entrance and exit points)
                current_player.ray_list.add(current_ray);
                // since a ray has been shot, score incremented
                current_player.score++;

                System.out.println("\nRay entered: " + current_ray.ray_entrance_edge);
                System.out.println("Ray exited: " + current_ray.ray_exit_edge);


//                // once final guess button is implemented, this method can be modified to be outside while loop
//                // AND to actually take in input for all 6 atom coordinates
//                final_guess = current_player.playerGuess();
            }


            games_played++;
        }


    }

}
