import java.util.ArrayList;

public class TrajectoryManager {

    ArrayList<Trajectory> trajectories;

    public TrajectoryManager(){
        trajectories = new ArrayList<>();
        set_trajectories();
    }




    public static void main(String[] args) {//TESTING PURPOSES
        TrajectoryManager t = new TrajectoryManager();
    }

    public void set_trajectories(){ //In Progress - Abdullah

        //                           Trajectory(trajectory                 , right_direction            , left_direction             );
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

//        // Printing trajectories (UNCOMMENT if u want to print)
//        for (Trajectory trajectory : trajectories) {
//            System.out.println(trajectory.toString()+"\n");
//        }
    }

    public ArrayList<Trajectory> getTrajectoriesList() {
        return trajectories;
    }
    public Trajectory getTrajectory(int index) {
        return trajectories.get(index);
    }
}
