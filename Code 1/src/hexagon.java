public class hexagon {

    protected  int first;
    protected  int second;
    public static int p = 0;
    private int position;

    protected int row;
    protected int col;


    // construct hexagon using pixels on screen x and y for DRAWING ONLY (first, second),
    // and coordinates in the rest of our program for data (row, col)
    public hexagon(int first, int second, int row, int col)
    {
        // add drawing coords
        this.first = first;
        this.second = second;
        // add data coords
        this.row = row;
        this.col = col;
        // next hexagon
        position = p;
        p++;
    }

    public int getPosition()
    {
        return position;
    }
}
