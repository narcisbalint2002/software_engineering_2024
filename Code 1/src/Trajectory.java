public class Trajectory {
    private Coordinate trajectory_direction;
    private Coordinate right_direction;
    private Coordinate left_direction;



    public Trajectory(Coordinate t, Coordinate r, Coordinate l) {
        this.trajectory_direction = t;
        this.right_direction = r;
        this.left_direction = l;
    }

    public static void main(String[] args) {//Testing Purposes
        Utility.set_trajectories();
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
