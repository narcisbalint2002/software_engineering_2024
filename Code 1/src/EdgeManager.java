import java.util.ArrayList;

public class EdgeManager {

    /*
     * contains all edge nodes, every edge has a unique number, and has a ray object for that coordinate, in that
     * specific direction, because in board, hexagons will have more than one side rays can be shot from, this means
     * they have up to 3 possible directions (most have 2, corners have 3), this basically creates all those rays at
     * the beginning so when needed they can be accessed
     */
    ArrayList<Edge> edge_list = new ArrayList<>();

    /*
     * these methods below do the exact same as their child methods,
     * theyre just done for convenience, so the method call is shorter
     * "Manager" is just added so they dont have the same name, less
     * confusion in getting them mixed up
     */

    public int getEdgeNumManager(int i) {
        return edge_list.get(i).getEdgeNum();
    }
    public void setEdgeNumManager(int index, int num) {
        edge_list.get(index).setEdgeNum(num);
    }
    public Coordinate getCoordinateManager(int i) {
        return edge_list.get(i).ray_obj.getCoordinate();
    }
    public int getXDirectionManager(int i) {
        return edge_list.get(i).ray_obj.getXDirection();
    }
    public int getYDirectionManager(int i) {
        return edge_list.get(i).ray_obj.getYDirection();
    }

    // class constructor, auto-constructs edge list with all edge nodes, each containing respective ray
    public EdgeManager() {
        // for iteration
        int i;

        /*
        * much of the maths below is just modulus and addition done as a way to find some pattern in iteratively
        * generating all the edge numbers as well as respective coordinates and directions, dont trip over it too much,
        * the important thing is it works, and it just takes advantage of the pattern each edge has in its layout
        * */

        // loop for top left edge
        for (i = 0; i < 10; i++) {
            // (a new arraylist NEEDS to be created each time otherwise same values of old arraylist are added
            Coordinate temp_c = new Coordinate();

            temp_c.x = i / 2;          // some quick maths
            temp_c.y = (i / 2) * -1;   // some more quick maths


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
            Coordinate temp_c = new Coordinate();
            temp_c.x = 4 + (((i + 1) % 10) / 2); // quick maths
            temp_c.y = -4; // columns all same


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
            Coordinate temp_c = new Coordinate();

            temp_c.x = 8;           // some quick maths
            temp_c.y = (4 - (((i + 1) % 19) / 2)) * -1;    // some more quick maths



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
            Coordinate temp_c = new Coordinate();

            temp_c.x = 8 - (((i + 1) % 28) / 2);           // some quick maths
            temp_c.y = ((i + 1) % 28) / 2;    // some more quick maths


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
            Coordinate temp_c = new Coordinate();
            temp_c.x = 4 - (((i + 1) % 37) / 2); // some quick maths
            temp_c.y = 4; // all y are 4




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
            Coordinate temp_c = new Coordinate();
            temp_c.x = 0;           // some quick maths
            temp_c.y = 4 - (((i + 1) % 46) / 2); // some more quick maths


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
//        // UNCOMMENT TO PRINT ENTIRE EDGE LIST (shows edge numbers and ray coordinates/direction)
        EdgeManager board_edge_list = new EdgeManager();
        for (int j = 0; j < board_edge_list.edge_list.size(); j++) {
            System.out.println(board_edge_list.edge_list.get(j).toString());
        }
    }
}
