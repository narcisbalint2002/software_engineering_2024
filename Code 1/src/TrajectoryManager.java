import java.util.ArrayList;

public class TrajectoryManager {

    private ArrayList<Trajectory> trajectories;

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
        Trajectory trajectory5 = new Trajectory(new Coordinate(1, 0), new Coordinate(+1, -1), new Coordinate(0, 1));
        Trajectory trajectory6 = new Trajectory(new Coordinate(1, -1), new Coordinate(0, -1), new Coordinate(+1, 0));

        trajectories.add(trajectory1);
        trajectories.add(trajectory2);
        trajectories.add(trajectory3);
        trajectories.add(trajectory4);
        trajectories.add(trajectory5);
        trajectories.add(trajectory6);

        // Printing trajectories
        for (Trajectory trajectory : trajectories) {
            System.out.println(trajectory.toString()+"\n");
        }
    }



    public class Trajectory {
        //not actual coordinate but direction (1, -1) can only  be 0, -1, 1.
        private final Coordinate trajectory_direction;
        private final Coordinate right_direction;
        private final Coordinate left_direction;



        public Trajectory(Coordinate t, Coordinate r, Coordinate l) {
            this.trajectory_direction = t;
            this.right_direction = r;
            this.left_direction = l;
        }

        public Coordinate getTrajectory_direction(){
            return trajectory_direction;
        }

        public Coordinate getRight_direction(){
            return trajectory_direction;
        }

        public Coordinate getLeft_direction(){
            return trajectory_direction;
        }

        @Override
        public String toString() {
            return "Trajectory Direction: (" + trajectory_direction.getX() + ", " + trajectory_direction.getY() + ")\n" +
                    "Right Direction: (" + right_direction.getX() + ", " + right_direction.getY() + ")\n" +
                    "Left Direction: (" + left_direction.getX() + ", " + left_direction.getY() + ")";
        }


    }

}
