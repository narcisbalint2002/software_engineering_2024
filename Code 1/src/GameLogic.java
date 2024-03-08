import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {



    private GameLogic() {
        throw new AssertionError("GameLogic should not be instantiated");
    }

    public static void gameLoop() {
        // at start, games played is 0
        int games_played = 0;


        // all players in the game (to compare score at the end)
        ArrayList<Player> players = new ArrayList<>();

        AtomManager atomManager = new AtomManager();


        // CHANGE TO HOWEVER MANY PLAYERS THERE ARE
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
            System.out.println(atomManager);

//            // terminal printing (for debugging)
//            Board.printBoard(board);


            EdgeManager board_edge_list = new EdgeManager();


            // runs while user does not have final guess of atom coordinates
            while(!final_guess) {

                // player choose whether want to guess final atoms
                Scanner user_input = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Edge number (as per diagrams)? ");
                int end_game = Integer.parseInt(user_input.nextLine()) - 1; // subtracting 1 to change from 1-54 to 0-53 (for indexing)
                Edge current_edge = board_edge_list.edgeInput(end_game);

                // works
                System.out.println(current_edge.toString());


                // loop for current ray
                RayManager current_ray = new RayManager(current_edge);

                current_ray.rayPath(board_edge_list, atomManager);





                current_player.ray_list.add(current_ray);

                System.out.println("\nRay entered: " + current_ray.ray_entrance_edge);
                System.out.println("Ray exited: " + current_ray.ray_exit_edge);


                final_guess = current_player.playerGuess();
            }


            games_played++;
        }


    }

}
