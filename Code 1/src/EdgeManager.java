import java.util.ArrayList;

public class EdgeManager {


    ArrayList<Edge> edge_list = new ArrayList<>();

    public int getEdgeNumManager(int i) {
        return edge_list.get(i).getEdgeNum();
    }

    public void setNumManager(int index, int num) {
        edge_list.get(index).setEdgeNum(num);
    }

    public ArrayList<Integer> getCoordinateManager(int i) {
        return edge_list.get(i).ray_obj.getCoordinate();
    }

    public int getXDirectionManager(int i) {
        return edge_list.get(i).ray_obj.getXDirection();
    }

    public int getYDirectionManager(int i) {
        return edge_list.get(i).ray_obj.getYDirection();
    }

    public EdgeManager() {
        int i;




        // loop for top left edge
        for (i = 0; i < 10; i++) {
            // (a new arraylist NEEDS to be created each time otherwise same values of old arraylist are added
            ArrayList<Integer> temp_c = new ArrayList<>();

            temp_c.add(i / 2);          // some quick maths
            temp_c.add((i / 2) * -1);   // some more quick maths


            // if edge index is even
            if (i % 2 == 0) {
                Edge temp_edge = new Edge(i + 1, temp_c, 1, 0);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                Edge temp_edge = new Edge(i + 1, temp_c, 0, 1);
                edge_list.add(temp_edge);
            }
        }

        // loop for bottom left edge
        for (i = 10; i < 19; i++) {
            // similar concept to that above, but basically needed % because this time
            // we start at 10 instead of 0, similar will apply later on
            ArrayList<Integer> temp_c = new ArrayList<>();
            temp_c.add( 4 + (((i + 1) % 10) / 2)); // quick maths
            temp_c.add(-4); // columns all same


            // if edge index is even
            if (i % 2 == 0) {
                Edge temp_edge = new Edge(i + 1, temp_c, -1, 1);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                Edge temp_edge = new Edge(i + 1, temp_c, 0, 1);
                edge_list.add(temp_edge);
            }
        }

        // loop for bottom edge
        for (i = 19; i < 28; i++) {
            // start at 19
            ArrayList<Integer> temp_c = new ArrayList<>();

            temp_c.add(8);           // some quick maths
            temp_c.add((4 - (((i + 1) % 19) / 2)) * -1);    // some more quick maths



            // if edge index is even
            if (i % 2 == 0) {
                Edge temp_edge = new Edge(i + 1, temp_c, -1, 1);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                Edge temp_edge = new Edge(i + 1, temp_c, -1, 0);
                edge_list.add(temp_edge);
            }
        }


        // loop for bottom right edge
        for (i = 28; i < 37; i++) {
            // start at 28
            ArrayList<Integer> temp_c = new ArrayList<>();

            temp_c.add(8 - (((i + 1) % 28) / 2));           // some quick maths
            temp_c.add(((i + 1) % 28) / 2);    // some more quick maths


            // if edge index is even
            if (i % 2 == 0) {
                Edge temp_edge = new Edge(i + 1, temp_c, 0, -1);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                Edge temp_edge = new Edge(i + 1, temp_c, -1, 0);
                edge_list.add(temp_edge);
            }
        }


        // loop for top right edge
        for (i = 37; i < 46; i++) {
            // start at 37
            ArrayList<Integer> temp_c = new ArrayList<>();
            temp_c.add(4 - (((i + 1) % 37) / 2)); // some quick maths
            temp_c.add(4); // all y are 4




            // if edge index is even
            if (i % 2 == 0) {
                Edge temp_edge = new Edge(i + 1, temp_c, 0, -1);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                Edge temp_edge = new Edge(i + 1, temp_c, 1, -1);
                edge_list.add(temp_edge);
            }
        }

        // loop for top edge
        for (i = 46; i < 54; i++) {
            // start at 46
            ArrayList<Integer> temp_c = new ArrayList<>();
            temp_c.add(0);           // some quick maths
            temp_c.add(4 - (((i + 1) % 46) / 2)); // some more quick maths


            // if edge index is even
            if (i % 2 == 0) {
                Edge temp_edge = new Edge(i + 1, temp_c, 1, 0);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                Edge temp_edge = new Edge(i + 1, temp_c, 1, -1);
                edge_list.add(temp_edge);
            }
        }




    }

    public static void main(String args[]) {

//        // UNCOMMENT TO PRINT ENTIRE EDGE LIST
//        EdgeManager board_edge_list = new EdgeManager();
//        for (int j = 0; j < board_edge_list.edge_list.size(); j++) {
//            System.out.println(board_edge_list.edge_list.get(j).toString());
//        }
    }
}
