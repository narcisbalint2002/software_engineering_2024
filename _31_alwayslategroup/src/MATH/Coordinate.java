package MATH;

import java.util.Objects;

public class Coordinate {

    // coordinates
    public int x;
    public int y;

    // constructs using parameters
    public Coordinate(int x, int y) {

        this.x = x;
        this.y = y;
    }

    // this is a default constructor (if you dont put an parameters)
    public Coordinate()
    {
        this(-1, -1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
