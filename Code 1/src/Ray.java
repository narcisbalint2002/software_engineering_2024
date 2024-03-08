import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

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
            System.out.println("Atom is at Ray coordinate");
        }else if(isAtom(front, atoms)){
            System.out.println("Ray is absorbed.");
        }else if(isAtom(right, atoms)) {
            //Since atom is on the right, we go left.
            Coordinate temp = new Coordinate(current.getX() + trajectory.getLeft_direction().getX(),current.getY() + trajectory.getLeft_direction().getY());
            newTrajectory(ray, trajectories, left, temp);
            System.out.println("Ray Coordiantes and Trajectory Changed - R");

        }else if(isAtom(left, atoms)){

            Coordinate temp = new Coordinate(current.getX() + trajectory.getRight_direction().getX(),current.getY() + trajectory.getRight_direction().getY());
            newTrajectory(ray, trajectories, right, temp);
            System.out.println("Ray Coordiantes and Trajectory Changed - L");

        }else {
            System.out.println("Ray Coordiantes changed and Trajectory NOT CHANGED");
            ray.coordinates = new Coordinate(current.getX() + trajectory.getTrajectory_direction().getX(),
                    current.getY() + trajectory.getTrajectory_direction().getY());
        }

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
