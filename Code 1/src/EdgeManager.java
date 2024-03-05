import java.util.ArrayList;

public class EdgeManager {


    ArrayList<Edge> edge_list;

    public EdgeManager() {
        int i;
        ArrayList<Integer> temp_c = new ArrayList<>();
        temp_c.add(-1);
        temp_c.add(-1);

        for (i = 0; i < 10; i++) {
            // all top row has x-coordinate as 0 and pattern is you can use divisibility by 2
            temp_c.set(0, 0);
            temp_c.set(1, i / 2);

            // if edge index is even
            if (i % 2 == 0) {
                Edge temp_edge = new Edge(i + 1, temp_c, 1, 0);
                edge_list.add(temp_edge);
            } else {
                Edge temp_edge = new Edge(i + 1, temp_c, 1, -1);
                edge_list.add(temp_edge);
            }
        }

    }

    public static void main(String args[]) {
        EdgeManager board_edge_list = new EdgeManager();
        for (int j = 0; j < board_edge_list.edge_list.size(); j++) {
            System.out.println(board_edge_list.edge_list.get(j).toString());
        }
    }
}
