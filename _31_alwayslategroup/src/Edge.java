import java.util.ArrayList;

public class Edge {

    // edge number value
    int edge_num;


    // ray object for this particular edge (both set to -1 as default value, this SHOULD NOT be kept same)
    Ray ray_obj = new Ray(new Coordinate(), -1, new Trajectory());



    // edge class constructor, changes edge number and ray properties
    public Edge(int e, Coordinate c, int t_i, Trajectory t) {
        edge_num = e;
        ray_obj.setCoordinate(c);
        ray_obj.setTrajectoryIndex(t_i);
        ray_obj.setTrajectory(t);
    }

    // set/get methods, self explanatory
    public void setEdgeNum(int edge_num) {
        this.edge_num = edge_num;
    }

    public int getEdgeNum() {
        return edge_num;
    }

    public Ray getRayObj() {return ray_obj;};

    // toString of Edge incorporates Ray toString
    @Override
    public String toString() {
        return "\nEdge " + edge_num + ray_obj.toString();
    }
}
