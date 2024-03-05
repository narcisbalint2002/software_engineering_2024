import java.util.ArrayList;

public class Edge {

    // edge number value
    int edge_num;
    Ray ray_obj = new Ray(new ArrayList<>(), -1, -1);

    // edge class constructor
    public Edge(int e, ArrayList<Integer> c, int x, int y) {
        edge_num = e;

        ray_obj.setCoordinate(c);
        ray_obj.setX_direction(x);
        ray_obj.setY_direction(y);
    }

    public void setEdgeNum(int edge_num) {
        this.edge_num = edge_num;
    }

    public int getEdgeNum() {
        return edge_num;
    }

    @Override
    public String toString() {
        return "\nEdge " + edge_num + ray_obj.toString();
    }
}
