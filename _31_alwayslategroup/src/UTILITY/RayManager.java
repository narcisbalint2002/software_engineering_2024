package UTILITY;

import GUI.GameBoard;
import MATH.Coordinate;
import OBJECTS.Edge;
import OBJECTS.Ray;
import UTILITY.AtomManager;
import UTILITY.EdgeManager;

import java.awt.*;

public class RayManager {


    // ray that is managed by this object
    Ray current_ray;

    // edges ray enters and exits from should be -1 by default
    public int ray_entrance_edge = -1;
    public int ray_exit_edge = -1;


    // this will manage ONE ray at a time
    public RayManager(Edge e) {
        current_ray = e.getRayObj();
        ray_entrance_edge = e.getEdgeNum();
    }


    // to shorten method call of checking if next ray is out of range in board
    public int getNextFrontX() {
        return current_ray.getCoordinate().getX() + current_ray.getTrajectory().getTrajectoryDirection().getX();
    }
    public int getNextFrontY() {
        return current_ray.getCoordinate().getY() + current_ray.getTrajectory().getTrajectoryDirection().getY();
    }
    public int getNextRightX() {
        return current_ray.getCoordinate().getX() + current_ray.getTrajectory().getRightDirection().getX();
    }
    public int getNextRightY() {
        return current_ray.getCoordinate().getY() + current_ray.getTrajectory().getRightDirection().getY();
    }
    public int getNextLeftX() {
        return current_ray.getCoordinate().getX() + current_ray.getTrajectory().getLeftDirection().getX();
    }
    public int getNextLeftY() {
        return current_ray.getCoordinate().getY() + current_ray.getTrajectory().getLeftDirection().getY();
    }

    public Coordinate getNextFront() {
        int new_front_x = getNextFrontX();
        int new_front_y = getNextFrontY();
        return new Coordinate(new_front_x, new_front_y);
    }
    public Coordinate getNextRight() {
        int new_right_x = getNextRightX();
        int new_right_y = getNextRightY();
        return new Coordinate(new_right_x, new_right_y);
    }
    public Coordinate getNextLeft() {
        int new_left_x = getNextLeftX();
        int new_left_y = getNextLeftY();
        return new Coordinate(new_left_x, new_left_y);
    }

//    // loop through numbers list to change particular edge
//    public void edgeUpdate(int ray_entrance, int ray_exit) {
//
//        // colour updates, for absorbed, then reflection, then every other path
//        Color new_color = Color.BLACK;
//        if (ray_exit == -1) {
//            new_color = Color.GREEN;
//        } else if (ray_entrance == ray_exit_edge) {
//            new_color = Color.RED;
//        } else {
//            new_color = Color.BLUE;
//        }
//
////        GUI.GameBoard.numbers.get(ray_entrance - 1).color = new_color;
////
////        if (ray_exit > 0) {
////            GUI.GameBoard.numbers.get(ray_exit - 1).color = new_color;
////        }
//
//        int edge_number = ray_entrance;
//
//        // loop for all numbers in GUI.GameBoard list
//        for (int i = 0; i < GUI.GameBoard.numbers.size(); i++) {
//            // set current edge data as a variable (easier to read)
//            GUI.GameBoard.NumberInfo edge_data = GUI.GameBoard.numbers.get(i);
//            // if edge number passed in matches edge data number, update size to 0 (not clickable),
//            // then also change colour to that passed in
//            if (edge_number == edge_data.getNumber()) {
////                edge_data.number_size = 0;
//                edge_data.color = new_color;
//                if ((ray_exit > 0) && (edge_number != ray_exit)) {
//                    edge_number = ray_exit;
//                    i = 0;
//                }
//            }
//        }
//    }

    // loop through numbers list to change particular edge
    public void edgeUpdate(int entrance, int exit, Color new_color) {


        // loop for all numbers in GUI.GameBoard list
        for (int i = 0; i < GameBoard.numbers.size(); i++) {
            // set current edge data as a variable (easier to read)
            GameBoard.NumberInfo edge_data = GameBoard.numbers.get(i);
            // if edge number passed in matches edge data number, update size to 0 (not clickable),
            // then also change colour to that passed in
            if (entrance == edge_data.getNumber()) {
//                edge_data.number_size = 0;
                edge_data.setFont(edge_data.getFont().deriveFont(Font.BOLD));
                edge_data.setColor(new_color);
            }

            if (exit == edge_data.getNumber()) {
                edge_data.setNumber(entrance);
                edge_data.setFont(edge_data.getFont().deriveFont(Font.BOLD));
                edge_data.setColor(new_color);
            }
        }
    }

    public void rayPath(EdgeManager board_edge_list, AtomManager atomManager) {
        // flag for if direct hit
        boolean direct_hit = false;
        boolean total_reflection = false;

        // booleans for current atom
        boolean atom_current = false;
        boolean atom_front = false;
        boolean atom_right = false;
        boolean atom_left = false;

        // ADD CHECK FOR IF IMMEDIATELY REFLECTED BACK OUT DUE TO CIRCLE OF INFLUENCE
        Coordinate front = new Coordinate(getNextFrontX(), getNextFrontY());
        Coordinate right = new Coordinate(getNextRightX(), getNextRightY());
        Coordinate left = new Coordinate(getNextLeftX(), getNextLeftY());

        /* this is JUST done to check atoms that arent directly in front of ray (this would affect certain edges LIKE
         * for example shooting a ray from edge 36, which would begin in (4,4) but if theres an atom in (5,3) the
         * circle of influence should reflect it out, so this accounts for that) */
        Utility.changeTrajectory(current_ray, 'b'); // flip ray temporarily
        Coordinate right_back = new Coordinate(getNextRightX(), getNextRightY());
        Coordinate left_back = new Coordinate(getNextLeftX(), getNextLeftY());
        Utility.changeTrajectory(current_ray, 'b'); // flip ray back

        /* literally only used for edge cases if circle of influence of atom just behind reflects it out
         * as soon as ray enters board
         * now we check if any of those atoms whose circle of influence reflects them totally
         * (ONLY applies at very edge so we dont do this every while loop) */
        boolean atom_right_back = Utility.isAtom(right_back, atomManager.getAtoms());
        boolean atom_left_back = Utility.isAtom(left_back, atomManager.getAtoms());

        // this does it while next x and y of ray in range (so if its next GUI.hexagon WOULD be on the board)
        while (Utility.inRange(getNextFrontX(), getNextFrontY())) {

            atom_current = Utility.isAtom(current_ray.getCoordinate(), atomManager.getAtoms());
            atom_front = Utility.isAtom(front, atomManager.getAtoms());
            atom_right = Utility.isAtom(right, atomManager.getAtoms());
            atom_left = Utility.isAtom(left, atomManager.getAtoms());

            // in case circle of influence immediately reflects back the atom
            if (atom_right_back || atom_left_back) {
                Utility.changeTrajectory(current_ray, 'b');
                System.out.println("OBJECTS.Ray INSTANTLY Reflected");
                total_reflection = true;
                break;
            }
            else if(atom_right && atom_left) {
//                //Since atom is on the right, we go left.
//                MATH.Coordinate temp = new MATH.Coordinate(left.getX(), left.getY());
                Utility.changeTrajectory(current_ray, 'b');
                System.out.println("OBJECTS.Ray Reflected");

                total_reflection = true;
            }
            else if(atom_right) {
//                //Since atom is on the right, we go left.
//                MATH.Coordinate temp = new MATH.Coordinate(left.getX(), left.getY());
                Utility.changeTrajectory(current_ray, 'l');
                System.out.println("OBJECTS.Ray Coordinates and MATH.Trajectory Changed - (OBJECTS.Atom was on Right)");
            }else if(atom_left){
//                //Since atom is on the left, we go right.
//                MATH.Coordinate temp = new MATH.Coordinate(current.getX() + trajectory.getRightDirection().getX(),current.getY() + trajectory.getRightDirection().getY());
//                newTrajectory(ray, trajectories, right, temp);
                Utility.changeTrajectory(current_ray, 'r');
                System.out.println("OBJECTS.Ray Coordinates and MATH.Trajectory Changed - (OBJECTS.Atom was on Left)");
            }

            // start on a coordinate at edge of board
            else if(atom_current){
                System.out.println("DIRECT HIT CURRENT");
                direct_hit = true;
                break;
            }
            // if there is an atom in front
            else if(atom_front){
                System.out.println("DIRECT HIT NEXT");
                direct_hit = true;
                break;
            }


            // check for if NO circles of influence either side
            else {
                System.out.println("OBJECTS.Ray Coordinates changed and MATH.Trajectory NOT CHANGED - MOVED FORWARD");
                current_ray.setCoordinate(front);
            }
            // // can uncomment for slightly more detail in terminal
//            System.out.println("-DIRECTION-");
//            System.out.println("F: " + front);
//            System.out.println("R: " + right);
//            System.out.println("L: " + left);
            front = new Coordinate(getNextFrontX(), getNextFrontY());
            right = new Coordinate(getNextRightX(), getNextRightY());
            left = new Coordinate(getNextLeftX(), getNextLeftY());
        }


        // if direct hit, exit is -1, otherwise its an edge we can find in the board
        if (direct_hit) {
            ray_exit_edge = -1;
            // update entrance edge number in GUI.GameBoard class to green for "absorbed"
            edgeUpdate(ray_entrance_edge, ray_exit_edge, new Color(0, 167, 59));

        }
        // if total reflection, we know came out same way came in
        else if (total_reflection) {
            ray_exit_edge = ray_entrance_edge;
            // update entrance edge number in GUI.GameBoard class to red for "reflection"
            edgeUpdate(ray_entrance_edge, ray_exit_edge, new Color(251, 7, 7));
        }
        else {

            // FINAL check (if reaches edge of board and an atom either left or right would affect its exit point)
            if (Utility.isAtom(right, atomManager.getAtoms())) {
                Utility.changeTrajectory(current_ray, 'l');
                System.out.println("OBJECTS.Ray Coordinates and MATH.Trajectory Changed At OBJECTS.Edge - (OBJECTS.Atom was on Right)");
            } else if (Utility.isAtom(left, atomManager.getAtoms())) {
                Utility.changeTrajectory(current_ray, 'r');
                System.out.println("OBJECTS.Ray Coordinates and MATH.Trajectory Changed At OBJECTS.Edge - (OBJECTS.Atom was on Left)");
            }

            /* this finds the edge the ray would exit board from based on its coordinate and trajectory
             * what the function does is checks edges that share that coordinate, then the opposite direction ray is going
             * in (because, if you think about it, the ray is going to EXIT through there, meaning the opposite of if you
             * were to ENTER from that edge)
             */
            // WIP, is getting SUCK HERE
            Edge edge_found = board_edge_list.findEdgeByCoordinateAndTrajectory(current_ray.getCoordinate(), current_ray.getTrajectory());

            // ray exit edge is the edge number from the exit edge we JUST found above
            ray_exit_edge = edge_found.getEdgeNum();


            // update BOTH entrance and exit edge numbers in GUI.GameBoard class to blue for "path"
            edgeUpdate(ray_entrance_edge, ray_exit_edge, new Color(9, 119, 210));
        }


    }

}
