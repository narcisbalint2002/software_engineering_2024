package UTILITY;

import MATH.Coordinate;
import MATH.Trajectory;

import java.util.ArrayList;


/**
 * The TrajectoryManager class manages trajectories.
 * Its functionality includes the creation of these possible trajectories
 * and adds them the list of trajectories.

 * This class also provides methods to retrieve the list of atoms created.
 */
public class TrajectoryManager {

    public ArrayList<Trajectory> trajectories;

    /**
     * Constructs TrajectoryManager object, initialising an empty list of trajectories
     * and calls the setTrajectories() method to populate the list with predefined trajectories.
     */
    public TrajectoryManager(){
        trajectories = new ArrayList<>();
        setTrajectories();
    }


    public static void main(String[] args) {//TESTING PURPOSES
        TrajectoryManager t = new TrajectoryManager();
    }

    /**
     * Generates a set of 6 trajectories possible in the hexagonal board and adds it to the list of trajectories.
     * Each trajectory consists of three coordinates representing the movement in the forward, right, and left directions.
     *
     * Note: The implementation uses hard-coded values derived from observations of direction propagation on the board.
     * These trajectories are essential for simulating the movement of rays across the board.
     */
    public void setTrajectories(){

        //                           MATH.Trajectory(trajectory                 , right_direction            , left_direction             );
        Trajectory trajectory1 = new Trajectory(new Coordinate(-1, 0), new Coordinate(-1, 1), new Coordinate(0, -1));
        Trajectory trajectory2 = new Trajectory(new Coordinate(-1, 1), new Coordinate(0, 1), new Coordinate(-1, 0));
        Trajectory trajectory3 = new Trajectory(new Coordinate(0, 1), new Coordinate(1, 0), new Coordinate(-1, 1));
        Trajectory trajectory4 = new Trajectory(new Coordinate(0, -1), new Coordinate(-1, 0), new Coordinate(1, -1));
        Trajectory trajectory5 = new Trajectory(new Coordinate(1, 0), new Coordinate(1, -1), new Coordinate(0, 1));
        Trajectory trajectory6 = new Trajectory(new Coordinate(1, -1), new Coordinate(0, -1), new Coordinate(+1, 0));

        trajectories.add(trajectory1);
        trajectories.add(trajectory2);
        trajectories.add(trajectory3);
        trajectories.add(trajectory4);
        trajectories.add(trajectory5);
        trajectories.add(trajectory6);
    }

    public ArrayList<Trajectory> getTrajectoriesList() {
        return trajectories;
    }
    public Trajectory getTrajectory(int index) {
        return trajectories.get(index);
    }
}
