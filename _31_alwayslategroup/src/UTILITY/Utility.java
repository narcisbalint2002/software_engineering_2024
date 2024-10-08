package UTILITY;

import MATH.Coordinate;
import MATH.Trajectory;
import OBJECTS.Atom;
import OBJECTS.Ray;
import UTILITY.TrajectoryManager;

import java.util.ArrayList;

public final class Utility {


    private Utility() {
        throw new AssertionError("Utility should not be instantiated");
    }

    public static void changeTrajectory(Ray ray, char new_direction) {


        Trajectory temp_t = null;
        TrajectoryManager traj_manager = new TrajectoryManager();

        // checks for direction we are CHANGING INTO (must be right, left or backwards 'r','l','b' )
        if (new_direction == 'r') {
            for (Trajectory t : traj_manager.trajectories) {
                if ((ray.getTrajectory().getRightDirection().getX() == t.getTrajectoryDirection().getX()) && (ray.getTrajectory().getRightDirection().getY() == t.getTrajectoryDirection().getY())) {
                    temp_t = t;
                }
            }
        }
        else if (new_direction == 'l') {
            for (Trajectory t : traj_manager.trajectories) {
                if ((ray.getTrajectory().getLeftDirection().getX() == t.getTrajectoryDirection().getX()) && (ray.getTrajectory().getLeftDirection().getY() == t.getTrajectoryDirection().getY())) {
                    temp_t = t;
                }
            }
        }
        // go back whence you came from (multiply current direction by -1)
        else if (new_direction == 'b') {
            for (Trajectory t : traj_manager.trajectories) {
                if (((ray.getTrajectory().getTrajectoryDirection().getX() * -1) == t.getTrajectoryDirection().getX()) && ((ray.getTrajectory().getTrajectoryDirection().getY() * -1) == t.getTrajectoryDirection().getY())) {
                    temp_t = t;
                }
            }
        }
        else {
            throw new IllegalArgumentException("New Ray Direction MUST be right, left or backwards ('r','l','b')");
        }

        // set new trajectory to new one found
        ray.setTrajectory(temp_t);
    }


    public static boolean isAtom(Coordinate coordinate, ArrayList<Atom> atoms) {

        for (Atom atom : atoms) {
            if (atom.getX() == coordinate.getX() && atom.getY() == coordinate.getY()) {
                return true;
            }
        }
        return false;
    }


    public static boolean inRange(int x, int y){ //In Progress for Liam.

        // turns out we actually dont need width of the GUI.hexagon, we can figure this out by just the max height
        // (i.e. number of rows in total)

        int max_height = 9;

        // upper limit calculated using height, basically if there are 9 rows, 9/2 = 4.5, floor(4.5) = 4,
        // this will be the upper limit of 0-4
        // dont actually need floor because 4.5 as int is just 4 (decimals are dropped),
        // but it makes more mathematical sense with it (can be removed if too confusing)
        int upper_column_limit = (int) Math.floor(max_height / 2);
        // lower limit always starts at 0 (0-4)
        int lower_column_limit = 0;


//        not in range of rows 0-9
        if ((x < 0) || (x > max_height)) {
            return false;
        }
        else {

            // if outside max ranges of columns (i.e. -4 - 4)
            if ((y < upper_column_limit * -1) || (y > upper_column_limit)) {
                return false;
            }

            // if row is halfway or less, do for increasing columns
            if (x <= max_height / 2) {
                // for loop for each row up to halfway (inclusive)
                for (int i = 0; i <= max_height / 2; i++) {
                    // for loop for each column in that row, does it for current limits
                    // i.e.
                    //      row 0:  0 - 4
                    //      row 1: -1 - 4
                    //          ...
                    //      row 4: -4 - 4
                    for (int j = lower_column_limit; j <= upper_column_limit; j++) {
                        // if x and y match current index, then we found the coordinate in the board
                        if ((x == i) && (y == j)) {
                            return true;
                        }
                    }
                    // makes sure next row STARTS at 1 less index (0 - 4   --->   -1 - 4)
                    lower_column_limit--;
                }
            }
            // for decreasing columns (will just skip to here if x > halfway)
            else {
                // lower limit will now be in negatives instead of 0, so if upper limit was 4, lower limit is -4
                lower_column_limit = upper_column_limit * -1;
                // because we are past halfway instead of -4 - 4, it is -4 - 3
                upper_column_limit -= 1;
                // for loop for each row after halfway to last row
                for (int i = (max_height / 2) + 1; i < max_height; i++) {
                    // for loop for each column in that row, does it for current limits
                    // i.e.
                    //      row 5: -4 - 3
                    //      row 6: -4 - 2
                    //          ...
                    //      row 8: -4 - 0
                    for (int j = lower_column_limit; j <= upper_column_limit; j++) {
                        if ((x == i) && (y == j)) {
                            return true;
                        }
                    }
                    // makes sure next row ENDS at 1 less index (-4 - 3   --->   -4 - 2)
                    upper_column_limit--;
                }
            }


        }

        // if got past all of the above, return false
        return false;
    }

    public static boolean containsPair(ArrayList<int[]> coordinates, int first_number, int second_number) {


        for (int[] pair : coordinates) {

            if(pair[0]==first_number && pair[1]==second_number) {
                return true;
            }

        }
        return false;
    }


    // // WIP, need to make proper tests for top corners, bottom corners, middle 3 rows, as well as out of bounds of all
    // // of those points (so just outside row or column range)
//    public static void main(String args[]) {
//        System.out.println(UTILITY.Utility.inRange(0,0));
//    }


}
