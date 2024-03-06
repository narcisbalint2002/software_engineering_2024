import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class Ray {
    // list of coordinates ray is at (x, y)
    Coordinate coordinates;

    // ray directions (will ONLY ever be -1, 0 or 1)
    int x_direction;
    int y_direction;


    // set the coordinates and direction ray is going in
    public Ray(Coordinate c, int x, int y) {
        coordinates = c;
        x_direction = x;
        y_direction = y;
    }

    // returns string of current ray properties
    @Override
    public String toString() {
//        String output = new String("\nCoordinates: [" + coordinate.get(0) + ", " + coordinate.get(1) + "]\nX-Direction: " + x_direction + "\n Y-Direction: " + y_direction + "\n");
//        return output;

        return "\nCoordinates: " + coordinates.toString() + "\nX-Direction: " + x_direction + "\nY-Direction: " + y_direction + "\n";
    }

    // set methods
    public void setCoordinate(Coordinate coordinate) {
        this.coordinates = coordinate;
    }

    public void setX_direction(int x_direction) {
        this.x_direction = x_direction;
    }

    public void setY_direction(int y_direction) {
        this.y_direction = y_direction;
    }

    // get methods
    public Coordinate getCoordinate() {
        return coordinates;
    }

    public int getXDirection() {
        return x_direction;
    }

    public int getYDirection() {
        return y_direction;
    }
}
