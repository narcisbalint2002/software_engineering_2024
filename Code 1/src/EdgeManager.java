import java.util.ArrayList;

public class EdgeManager {

    /*
     * contains all edge nodes, every edge has a unique number, and has a ray object for that coordinate, in that
     * specific direction, because in board, hexagons will have more than one side rays can be shot from, this means
     * they have up to 3 possible directions (most have 2, corners have 3), this basically creates all those rays at
     * the beginning so when needed they can be accessed
     */
    ArrayList<Edge> edge_list = new ArrayList<>();
    TrajectoryManager trajectory_obj = new TrajectoryManager();

    /*
     * these methods below do the exact same as their child methods,
     * theyre just done for convenience, so the method call is shorter
     * "Manager" is just added so they dont have the same name, less
     * confusion in getting them mixed up
     */

    // returns an Edge node based on index inputted as argument
    public Edge getEdge(int index) {
        return edge_list.get(index);
    }

    // when we reach the edge of the board with a ray, we are at a particular set of coordinates with a particular
    // trajectory, with BOTH of these, we can find the edge of that coordinate with the OPPOSITE trajectory (meaning
    // it is pointing at where the ray came from), this means we successfully found our exit edge of our ray
    public Edge findEdgeByCoordinateAndTrajectory(Coordinate c, Trajectory t) {
        //// reverse the trajectory by multiplying its x and y by -1
        int traj_x = t.getTrajectory_direction().getX() * -1;
        int traj_y = t.getTrajectory_direction().getY() * -1;

        // goes thru entire edge list to see if current ray coordinates and inverse trajectory match any of those in list
        // the idea is that we meet an edge that shares the coordinate, but the trajectory will be opposite so we multiply its x and y by -1
        for (int i = 0; i < edge_list.size(); i++) {

//            System.out.println(getCoordinateManager(i).toString());
//            System.out.println(c.toString());
//            System.out.println(getTrajectoryManager(i).toString());
//            System.out.println("Trajectory: (" + traj_x + ", " +  traj_y + ")");

            // here we are getting the current x and y coordinates AND x and y direction the current edge stores
            int current_x = getCoordinateManager(i).getX();
            int current_y = getCoordinateManager(i).getY();
            int current_x_trajectory = getTrajectoryManager(i).getTrajectory_direction().getX();
            int current_y_trajectory = getTrajectoryManager(i).getTrajectory_direction().getY();

            // comparing current coordinate and trajectory to arguments passed in (except the trajectory is inverted because opposite direction)
            if ((current_x == c.getX()) && (current_y == c.getY()) && (current_x_trajectory == traj_x) && (current_y_trajectory == traj_y)) {
//                System.out.println("FOUND");
                return edge_list.get(i);
            }

        }
        // if we didnt find we lose :( (ideally this shouldnt happen)
        return null;
    }

    public int getEdgeNumManager(int i) {
        return edge_list.get(i).getEdgeNum();
    }
    public void setEdgeNumManager(int index, int num) {
        edge_list.get(index).setEdgeNum(num);
    }
    public Coordinate getCoordinateManager(int i) {
        return edge_list.get(i).ray_obj.getCoordinate();
    }
    public int getTrajectoryIndexManager(int i) {
        return edge_list.get(i).ray_obj.getTrajectoryIndex();
    }
    public Trajectory getTrajectoryManager(int i) {
        return edge_list.get(i).ray_obj.getTrajectory();
    }

    // class constructor, auto-constructs edge list with all edge nodes, each containing respective ray
    public EdgeManager() {
        // for iteration
        int i;

        // index for trajectory list (is an enum)
        int traj_index;

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
                traj_index = TrajectoryName.DOWN_RIGHT.getTrajectoryIndex(); // this means going down and right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index); // starting trajectory of ray from this edge
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                traj_index = TrajectoryName.RIGHT.getTrajectoryIndex(); // this means going right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
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
                traj_index = TrajectoryName.UP_RIGHT.getTrajectoryIndex(); // this means going up and right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                traj_index = TrajectoryName.RIGHT.getTrajectoryIndex(); // this means going right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
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
                traj_index = TrajectoryName.UP_RIGHT.getTrajectoryIndex(); // this means going up and right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                traj_index = TrajectoryName.UP_LEFT.getTrajectoryIndex(); // this means going up and left
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
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
                traj_index = TrajectoryName.LEFT.getTrajectoryIndex(); // this means going left
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                traj_index = TrajectoryName.UP_LEFT.getTrajectoryIndex(); // this means going up and left
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
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
                traj_index = TrajectoryName.LEFT.getTrajectoryIndex(); // this means going left
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                traj_index = TrajectoryName.DOWN_LEFT.getTrajectoryIndex(); // this means going up and right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
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
                traj_index = TrajectoryName.DOWN_RIGHT.getTrajectoryIndex(); // this means going down and right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
                edge_list.add(temp_edge);
            }
            // otherwise must be odd index
            else {
                traj_index = TrajectoryName.DOWN_LEFT.getTrajectoryIndex(); // this means going up and right
                Trajectory temp_traj = trajectory_obj.getTrajectory(traj_index);
                Edge temp_edge = new Edge(i + 1, temp_c, traj_index, temp_traj);
                edge_list.add(temp_edge);
            }
        }




    }

    public static void main(String args[]) {
//        // UNCOMMENT TO PRINT ENTIRE EDGE LIST (shows edge numbers and ray coordinates/direction)
//        EdgeManager board_edge_list = new EdgeManager();
//        for (int j = 0; j < board_edge_list.edge_list.size(); j++) {
//            System.out.println(board_edge_list.edge_list.get(j).toString());
//        }
    }
}
