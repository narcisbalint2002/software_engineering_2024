import java.util.ArrayList;

public class Edge {

    // edge number value
    int edge_num;
    // ray object for this particular edge (both set to -1 as default value, this SHOULD NOT be kept same)
    Ray ray_obj = new Ray(new Coordinate(), -1, -1);

    // edge class constructor, changes edge number and ray properties
    public Edge(int e, Coordinate c, int x, int y) {
        edge_num = e;
        ray_obj.setCoordinate(c);
        ray_obj.setX_direction(x);
        ray_obj.setY_direction(y);
    }

    // set/get methods, self explanatory
    public void setEdgeNum(int edge_num) {
        this.edge_num = edge_num;
    }

    public int getEdgeNum() {
        return edge_num;
    }

    // toString of Edge incorporates Ray toString
    @Override
    public String toString() {
        return "\nEdge " + edge_num + ray_obj.toString();
    }
}
