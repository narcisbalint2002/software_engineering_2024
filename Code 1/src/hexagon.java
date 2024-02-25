public class hexagon {

    protected  int first;
    protected  int second;
    public static int p = 0;
    private int position;

    public hexagon(int first, int second)
    {
        this.first = first;
        this.second = second;
      position = p;
        p++;
    }

    public int getPosition()
    {
        return position;
    }
}
