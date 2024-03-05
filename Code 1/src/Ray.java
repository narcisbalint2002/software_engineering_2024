import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class Ray {
    // list of coordinates ray is at
    ArrayList<Integer> coordinate;

    // ray directions
    int x_direction;
    int y_direction;


    public Ray(ArrayList<Integer> c, int x, int y) {
        coordinate = c;
        x_direction = x;
        y_direction = y;
    }

    @Override
    public String toString() {
//        String output = new String("\nCoordinates: [" + coordinate.get(0) + ", " + coordinate.get(1) + "]\nX-Direction: " + x_direction + "\n Y-Direction: " + y_direction + "\n");
//        return output;

        return "\nCoordinates: [" + coordinate.get(0) + ", " + coordinate.get(1) + "]\nX-Direction: " + x_direction + "\n Y-Direction: " + y_direction + "\n";
    }

    // set methods
    public void setCoordinate(ArrayList<Integer> coordinate) {
        this.coordinate = coordinate;
    }

    public void setX_direction(int x_direction) {
        this.x_direction = x_direction;
    }

    public void setY_direction(int y_direction) {
        this.y_direction = y_direction;
    }

    // getter methods
    public ArrayList<Integer> getCoordinate() {
        return coordinate;
    }

    public int getX_direction() {
        return x_direction;
    }

    public int getY_direction() {
        return y_direction;
    }
}
