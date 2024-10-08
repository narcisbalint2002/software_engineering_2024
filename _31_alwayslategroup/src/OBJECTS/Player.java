package OBJECTS;

import MATH.Coordinate;
import OBJECTS.Atom;
import MATH.*;
import UTILITY.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    // number of rays this stupid player has shot (can just be calculated from length of ray_list)
    int num_rays;

    // dumb players score
    public int score;

    // by default is -1
    public static Coordinate current_atom = new Coordinate();

    public ArrayList<Coordinate> player_atoms = new ArrayList<>();


    // list of rays (each ray has entrance and exit points)
    public ArrayList<RayManager> ray_list = new ArrayList<>();



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

    public Coordinate atomCheck(Coordinate atom_coords) {

        // checks through all atoms in list, if coords already there, return true, because atom ALREADY guessed
        for (int i = 0; i < player_atoms.size(); i++) {
//            System.out.printf("\nAtom %d: [%d, %d]", i + 1, atoms.get(i).getX(), atoms.get(i).getY());

            if ((atom_coords.getX() == player_atoms.get(i).getX()) && (atom_coords.getY() == player_atoms.get(i).getY())) {
                return player_atoms.get(i);
            }
        }
        return null;
    }

    public void calculatePoints(ArrayList<Atom> atoms) {


        for (int j = 0; j < Main.NUM_ATOMS; j++) {
            // need to also display actual atoms and those that were NOT changed to green mustve NOT been
            // discovered so change those to orange
            GameLogic.board.changeHexagonColour(atoms.get(j).getX(), atoms.get(j).getY(), new Color(255, 165, 0));
        }
        for (int i = 0; i < Main.NUM_ATOMS; i++) {

            // for every INCORRECT atom, 5 points added (MORE POINTS IS BAD)
            if (!Utility.isAtom(player_atoms.get(i), atoms)) {
                score += 5;
                // if not an atom, paint red to display where players guess was and that it was wrong
                GameLogic.board.changeHexagonColour(player_atoms.get(i).getX(), player_atoms.get(i).getY(), new Color(251, 7, 7));
            } else {
                // otherwise guess was right, so display green for correct guess
                GameLogic.board.changeHexagonColour(player_atoms.get(i).getX(), player_atoms.get(i).getY(), new Color(0, 167, 59));
            }
        }

        if (score < 10) {
            GameLogic.board.player_points_text.color = new Color(0, 167, 59);
        } else if (score < 20) {
            GameLogic.board.player_points_text.color = new Color(255, 165, 0);
        }

        String s = "Player Score: " + score;

//        UTILITY.GameLogic.board.getGraphics().setColor(Color.BLACK);
//        UTILITY.GameLogic.board.getGraphics().drawString(s, 500, 20);

        GameLogic.board.player_points_text.text = s;
        GameLogic.board.player_points_text.invisible = false;

        // update board
        GameLogic.board.repaint();
    }


    // FUTURE IDEA: could be a function that is hooked up to a button on screen to do final guesses, then when
    // clicked returns true, which causes user to go into state of choosing atom coordinates to guess (by clicking
    // their hexagons on board)
    public void playerGuess() {

        int atoms_guessed = 0;
        // run loop while not all atoms guessed
        while (atoms_guessed < Main.NUM_ATOMS) {
            // player choice for atoms
            Scanner final_guess = new Scanner(System.in);  // Create a Scanner object

//            System.out.println("IM STUCK");
////            System.out.println(atoms);
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                System.out.println();;
//            }

            // if we did get a new atom guess (by default coordinates are (-1,-1), which are flagged as invalid because
            // there is no -1 row, BUT there are -1 columns, so this check needs to be done ONLY for rows of -1)
            if (current_atom.getX() != -1) {
                System.out.printf("\nx: %d, y: %d", current_atom.getX(), current_atom.getY());

                // need check for if NOT already in list, if it is then we will undo the guess
                if (atomCheck(current_atom) == null) {
                    Coordinate new_atom = new Coordinate(current_atom.getX(), current_atom.getY());
                    player_atoms.add(new_atom);
                    // update display of new GUI.hexagon to show the one we are guessing
                    GameLogic.board.changeHexagonColour(new_atom.getX(), new_atom.getY(), Color.GRAY);
                    GameLogic.board.repaint();
                    atoms_guessed++;
                } else {
                    player_atoms.remove(atomCheck(current_atom));
                    // update display of new GUI.hexagon to reset one we are guessing
                    GameLogic.board.changeHexagonColour(current_atom.getX(), current_atom.getY(), Color.BLACK);
                    GameLogic.board.repaint();
                    atoms_guessed--;
                }
                current_atom.x = -1;
                current_atom.y = -1;
            }

        }





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
