//// made by liam, method call for this in EdgeManager is way too long
//// its like:
////    TrajectoryManager.TrajectoryName var = TrajectoryManager.TrajectoryName.UP_LEFT;
public enum TrajectoryName {
    UP_LEFT(0),
    UP_RIGHT(1),
    RIGHT(2),
    LEFT(3),
    DOWN_RIGHT(4),
    DOWN_LEFT(5);

    private int trajectory_index;

    TrajectoryName(int trajectory_index) {
        this.trajectory_index = trajectory_index;
    }

    public int getTrajectoryIndex() {
        return trajectory_index;
    }


}