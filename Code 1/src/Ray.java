import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Ray {
    // list of coordinates ray is at (x, y)
    Coordinate coordinates;

    // ray directions (will ONLY ever be -1, 0 or 1)
    int trajectory_index;
    Trajectory trajectory;


    // set the coordinates and direction ray is going in
    public Ray(Coordinate c, int t_i, Trajectory t) {
        coordinates = c;
        trajectory_index = t_i;
        trajectory = t;
    }


    public void setTrajectory(Trajectory t) {
        this.trajectory = t;
    }

    public void setTrajectoryIndex(int trajectory_index) {
        this.trajectory_index = trajectory_index;
    }

    public Trajectory getTrajectory() {
        return trajectory;
    }

    public int getTrajectoryIndex() {
        return trajectory_index;
    }

    // returns string of current ray properties
    @Override
    public String toString() {
//        String output = new String("\nCoordinates: [" + coordinate.get(0) + ", " + coordinate.get(1) + "]\nX-Direction: " + x_direction + "\n Y-Direction: " + y_direction + "\n");
//        return output;

        return "\nCoordinates: " + coordinates.toString() + "\nX-Direction: " + trajectory.getTrajectory_direction().x + "\nY-Direction: " + trajectory.getTrajectory_direction().y + "\n";
    }

    // set methods
    public void setCoordinate(Coordinate coordinate) {
        this.coordinates = coordinate;
    }

//    public void setX_direction(int x_direction) {
//        this.x_direction = x_direction;
//    }
//
//    public void setY_direction(int y_direction) {
//        this.y_direction = y_direction;
//    }

    // get methods
    public Coordinate getCoordinate() {
        return coordinates;
    }
//
//    public int getXDirection() {
//        return x_direction;
//    }
//
//    public int getYDirection() {
//        return y_direction;
//    }
}
