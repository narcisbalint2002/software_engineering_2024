public class RayManager {


    Ray current_ray;

    // edges ray enters and exits from should be -1 by default
    int ray_entrance_edge = -1;
    int ray_exit_edge = -1;


//    public class RayEdges {
//        // ray entrance and exit edges
//
//
//        public RayEdges() {
//
//        }
//
//    }

    // this will manage ONE ray at a time
    public RayManager(Edge e) {
        current_ray = e.getRayObj();
        ray_entrance_edge = e.getEdgeNum();
    }


    // to shorten method call of checking if next ray is out of range in board
    public int getNextX() {
        return current_ray.getCoordinate().getX() + current_ray.getTrajectory().getTrajectory_direction().getX();
    }
    public int getNextY() {
        return current_ray.getCoordinate().getY() + current_ray.getTrajectory().getTrajectory_direction().getY();
    }

    public void rayPath(EdgeManager board_edge_list) {

        // ADD CHECK FOR IF IMMEDIATELY ABSORBED OR REFLECTED

        // this does it while next x and y of ray in range (so if its next hexagon WOULD be on the board)
        while (Utility.inRange(getNextX(), getNextY())) {

            // ADD CHECKS FOR LEFT AND RIGHT DIRECTIONS

            // // only used to verify if ray is moving
//            System.out.println("\n\n--WOAH--");

            // sets current ray coordinates to be hexagon in front (so moves one hexagon forward)
            // this is for IF THE ABOVE CHECK FOR CIRCLES OF INFLUENCE EITHER SIDE IS FALSE
            current_ray.coordinates.setX(getNextX());
            current_ray.coordinates.setY(getNextY());
        }



        /* this finds the edge the ray would exit board from based on its coordinate and trajectory
         * what the function does is checks edges that share that coordinate, then the opposite direction ray is going
         * in (because, if you think about it, the ray is going to EXIT through there, meaning the opposite of if you
         * were to ENTER from that edge)
         */
        // WIP, is getting SUCK HERE
        Edge edge_found = board_edge_list.findEdgeByCoordinateAndTrajectory(current_ray.getCoordinate(), current_ray.getTrajectory());

        // ray exit edge is the edge number from the exit edge we JUST found above
        ray_exit_edge = edge_found.getEdgeNum();

    }

}
