import java.util.ArrayList;

public final class Utility {


    private Utility() {
        throw new AssertionError("Utility should not be instantiated");
    }






    public static void calculate_Tracjectory(Ray ray, ArrayList<Atom> atoms, ArrayList<Trajectory> trajectories){ //In Progress

        Coordinate current = ray.getCoordinate(); //Ray Coordinates

        Trajectory trajectory = ray.getTrajectory(); //Ray Trajectory

        //Calculate the coordinates in front, right, and left of the ray.
        Coordinate front = new Coordinate(current.getX() + trajectory.getTrajectory_direction().getX(),
                current.getY() + trajectory.getTrajectory_direction().getY());

        Coordinate right = new Coordinate(current.getX() + trajectory.getRight_direction().getX(),
                current.getY() + trajectory.getRight_direction().getY());

        Coordinate left = new Coordinate(current.getX() + trajectory.getLeft_direction().getX(),
                current.getY() + trajectory.getLeft_direction().getY());


        if(isAtom(current, atoms)){
            System.out.println("DIRECT HIT");
        }else if(isAtom(front, atoms)){
            System.out.println("DIRECT HIT");
        }else if(isAtom(right, atoms)) {
            //Since atom is on the right, we go left.
            Coordinate temp = new Coordinate(current.getX() + trajectory.getLeft_direction().getX(),current.getY() + trajectory.getLeft_direction().getY());
            newTrajectory(ray, trajectories, left, temp);
            System.out.println("Ray Coordinates and Trajectory Changed - R");

        }else if(isAtom(left, atoms)){

            Coordinate temp = new Coordinate(current.getX() + trajectory.getRight_direction().getX(),current.getY() + trajectory.getRight_direction().getY());
            newTrajectory(ray, trajectories, right, temp);
            System.out.println("Ray Coordinates and Trajectory Changed - L");

        }else {
            System.out.println("Ray Coordinates changed and Trajectory NOT CHANGED");
            ray.coordinates = new Coordinate(current.getX() + trajectory.getTrajectory_direction().getX(),
                    current.getY() + trajectory.getTrajectory_direction().getY());
        }

    }

    public static void changeTrajectory(Ray ray, char new_direction) {


        Trajectory tempT = null;
        TrajectoryManager traj_manager = new TrajectoryManager();

        // checks for direction we are CHANGING INTO (must be right, left or backwards 'r','l','b' )
        if (new_direction == 'r') {
            for (Trajectory t : traj_manager.trajectories) {
                if ((ray.getTrajectory().getRight_direction().getX() == t.getTrajectory_direction().getX()) && (ray.getTrajectory().getRight_direction().getY() == t.getTrajectory_direction().getY())) {
                    tempT = t;
                }
            }
        }
        else if (new_direction == 'l') {
            for (Trajectory t : traj_manager.trajectories) {
                if ((ray.getTrajectory().getLeft_direction().getX() == t.getTrajectory_direction().getX()) && (ray.getTrajectory().getLeft_direction().getY() == t.getTrajectory_direction().getY())) {
                    tempT = t;
                }
            }
        }
        // go back whence you came from (multiply current direction by -1)
        else if (new_direction == 'b') {
            for (Trajectory t : traj_manager.trajectories) {
                if (((ray.getTrajectory().getTrajectory_direction().getX() * -1) == t.getTrajectory_direction().getX()) && ((ray.getTrajectory().getTrajectory_direction().getY() * -1) == t.getTrajectory_direction().getY())) {
                    tempT = t;
                }
            }
        }
        else {
            throw new IllegalArgumentException("New Ray Direction MUST be right, left or backwards ('r','l','b')");
        }

        // set new trajectory to new one found
        ray.setTrajectory(tempT);
    }

    public static void changeTrajectoryLeft(Ray ray) {
        // changes coordinates of ray to left
        int x_new = ray.coordinates.getX() + ray.trajectory.getLeft_direction().getX();
        int y_new = ray.coordinates.getY() + ray.trajectory.getLeft_direction().getY();

        // if new x and y are NOT in range
        if(Utility.inRange(x_new, y_new)) {
            // sets coordinates to new ones
            ray.setCoordinate(new Coordinate(x_new, y_new));
        }

        Trajectory tempT = null;
        TrajectoryManager traj_manager = new TrajectoryManager();
        for (Trajectory t : traj_manager.trajectories) {
            if ((ray.getTrajectory().getLeft_direction().getX() == t.getTrajectory_direction().getX()) && (ray.getTrajectory().getLeft_direction().getY() == t.getTrajectory_direction().getY())) {
                tempT = t;
            }
        }
        ray.setTrajectory(tempT);
    }
    public static void changeTrajectoryRight(Ray ray) {
        // changes coordinates of ray to left
        int x_new = ray.coordinates.getX() + ray.trajectory.getRight_direction().getX();
        int y_new = ray.coordinates.getY() + ray.trajectory.getRight_direction().getY();


        // if new x and y are NOT in range
        if(Utility.inRange(x_new, y_new)) {
            // sets coordinates to new ones
            ray.setCoordinate(new Coordinate(x_new, y_new));
        }




        Trajectory tempT = null;
        TrajectoryManager traj_manager = new TrajectoryManager();
        for (Trajectory t : traj_manager.trajectories) {
            if ((ray.getTrajectory().getRight_direction().getX() == t.getTrajectory_direction().getX()) && (ray.getTrajectory().getRight_direction().getY() == t.getTrajectory_direction().getY())) {
                tempT = t;
            }
        }
        ray.setTrajectory(tempT);
    }

    private static void newTrajectory(Ray ray, ArrayList<Trajectory> trajectories, Coordinate right, Coordinate temp) {
        ray.setCoordinate(temp);
        Trajectory tempT = null;
        for (Trajectory t : trajectories) {
            if (right.getX() == t.getLeft_direction().getX() && t.getLeft_direction().getY() == right.getY()) {
                tempT = t;
            }
        }
        ray.setTrajectory(tempT);
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

        //Still need to figure out the ranges for each row.
        //mybe we can add enum; when we make the board to hold row ranges.
        //mybe we can add the information to row nodes.
        //mybe a function that goes through the board structure, and collects the information.
        //Will do after new board implementation.

        // turns out we actually dont need width of the hexagon, we can figure this out by just the max height
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

    public static boolean containsPair(ArrayList<int[]> coordinates, int firstNumber, int secondNumber) {


        for (int[] pair : coordinates) {

            if(pair[0]==firstNumber && pair[1]==secondNumber) {
                return true;
            }

        }
        return false;
    }


    // // WIP, need to make proper tests for top corners, bottom corners, middle 3 rows, as well as out of bounds of all
    // // of those points (so just outside row or column range)
//    public static void main(String args[]) {
//        System.out.println(Utility.inRange(0,0));
//    }


}
