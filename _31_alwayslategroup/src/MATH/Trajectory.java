package MATH;

import MATH.Coordinate;

public class Trajectory {
    // not actual coordinate but direction (1, -1) can only  be 0, -1, 1.
    private final Coordinate trajectory_direction;
    private final Coordinate right_direction;
    private final Coordinate left_direction;




    public Trajectory() {
        this.trajectory_direction = new Coordinate();
        this.right_direction = new Coordinate();
        this.left_direction = new Coordinate();
    }

    public Trajectory(Coordinate t, Coordinate r, Coordinate l) {
        this.trajectory_direction = t;
        this.right_direction = r;
        this.left_direction = l;
    }

    public Coordinate getTrajectoryDirection(){
        return trajectory_direction;
    }

    public Coordinate getRightDirection(){
        return right_direction;
    }

    public Coordinate getLeftDirection(){
        return left_direction;
    }

    public void setTrajectoryDirection(int x, int y){
        trajectory_direction.setX(x);
        trajectory_direction.setY(y);
    }

    public void setRightDirection(int x, int y){
        right_direction.setX(x);
        right_direction.setY(y);
    }

    public void setLeftDirection(int x, int y){
        left_direction.setX(x);
        left_direction.setY(y);
    }

    @Override
    public String toString() {
        return "Trajectory Direction: (" + trajectory_direction.getX() + ", " + trajectory_direction.getY() + ")\n" +
                "Right Direction: (" + right_direction.getX() + ", " + right_direction.getY() + ")\n" +
                "Left Direction: (" + left_direction.getX() + ", " + left_direction.getY() + ")";
    }


}