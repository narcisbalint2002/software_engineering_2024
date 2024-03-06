public class Coordinate {

    // coordinates
    int x;
    int y;

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

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
