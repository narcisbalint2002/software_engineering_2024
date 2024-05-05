package GUI;

import java.awt.*;

public class hexagon {

    public int first;
    public int second;
    public static int p = 0;
    private int position;

    public Polygon polygon;

    public int row;
    public int col;

    public Color color;


    // construct GUI.hexagon using pixels on screen x and y for DRAWING ONLY (first, second),
    // and coordinates in the rest of our program for data (row, col)
    public hexagon(int first, int second, int row, int col, Color color)
    {
        // add drawing coords
        this.first = first;
        this.second = second;
        // add data coords
        this.row = row;
        this.col = col;
        // next GUI.hexagon
        position = p;
        p++;
        this.color = color;
    }


    public int getRow() { return row; }
    public int getCol() { return col; }

    public int getPosition()
    {
        return position;
    }
}
