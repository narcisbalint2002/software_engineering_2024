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
    public int getNextFrontX() {
        return current_ray.getCoordinate().getX() + current_ray.getTrajectory().getTrajectory_direction().getX();
    }
    public int getNextFrontY() {
        return current_ray.getCoordinate().getY() + current_ray.getTrajectory().getTrajectory_direction().getY();
    }
    public int getNextRightX() {
        return current_ray.getCoordinate().getX() + current_ray.getTrajectory().getRight_direction().getX();
    }
    public int getNextRightY() {
        return current_ray.getCoordinate().getY() + current_ray.getTrajectory().getRight_direction().getY();
    }
    public int getNextLeftX() {
        return current_ray.getCoordinate().getX() + current_ray.getTrajectory().getLeft_direction().getX();
    }
    public int getNextLeftY() {
        return current_ray.getCoordinate().getY() + current_ray.getTrajectory().getLeft_direction().getY();
    }

    public Coordinate getNextFront() {
        int new_front_x = getNextFrontX();
        int new_front_y = getNextFrontY();
        return new Coordinate(new_front_x, new_front_y);
    }
    public Coordinate getNextRight() {
        int new_right_x = getNextRightX();
        int new_right_y = getNextRightY();
        return new Coordinate(new_right_x, new_right_y);
    }
    public Coordinate getNextLeft() {
        int new_left_x = getNextLeftX();
        int new_left_y = getNextLeftY();
        return new Coordinate(new_left_x, new_left_y);
    }

//    public void setNextRight(Coordinate old_right) {
//        old_right.x = getNextRightX();
//        old_right.y = getNextRightY();
//    }
//
//    public void setNextLeft(Coordinate old_left) {
//        old_left.x = getNextLeftX();
//        old_left.y = getNextLeftY();
//    }

    public void rayPath(EdgeManager board_edge_list, AtomManager atomManager) {

        Coordinate front = new Coordinate(getNextFrontX(), getNextFrontY());
        Coordinate right = new Coordinate(getNextRightX(), getNextRightY());
        Coordinate left = new Coordinate(getNextLeftX(), getNextLeftY());

        // ADD CHECK FOR IF IMMEDIATELY ABSORBED OR REFLECTED


        // flag for if direct hit
        boolean direct_hit = false;

        // this does it while next x and y of ray in range (so if its next hexagon WOULD be on the board)
        while (Utility.inRange(getNextFrontX(), getNextFrontY())) {


            // start on a coordinate at edge of board
            if(Utility.isAtom(current_ray.getCoordinate(), atomManager.getAtoms())){
                System.out.println("DIRECT HIT CURRENT");
                direct_hit = true;
                break;
            }
            // if there is an atom in front
            else if(Utility.isAtom(front, atomManager.getAtoms())){
                System.out.println("DIRECT HIT NEXT");
                direct_hit = true;
                break;
            }

            else if(Utility.isAtom(right, atomManager.getAtoms())) {
//                //Since atom is on the right, we go left.
//                Coordinate temp = new Coordinate(left.getX(), left.getY());
                Utility.changeTrajectoryLeft(current_ray);
                System.out.println("Ray Coordinates and Trajectory Changed - R");

            }else if(Utility.isAtom(left, atomManager.getAtoms())){
//                //Since atom is on the left, we go right.
//                Coordinate temp = new Coordinate(current.getX() + trajectory.getRight_direction().getX(),current.getY() + trajectory.getRight_direction().getY());
//                newTrajectory(ray, trajectories, right, temp);
                Utility.changeTrajectoryRight(current_ray);
                System.out.println("Ray Coordinates and Trajectory Changed - L");

            }
            // check for if NO circles of influence either side
            else {
                System.out.println("Ray Coordinates changed and Trajectory NOT CHANGED - MOVED FORWARD");
                current_ray.setCoordinate(front);
//                front = getNextFront();
//                right = getNextRight();
//                left = getNextLeft();
                front = new Coordinate(getNextFrontX(), getNextFrontY());
                right = new Coordinate(getNextRightX(), getNextRightY());
                left = new Coordinate(getNextLeftX(), getNextLeftY());

            }
        }



        // if direct hit, exit is -1, otherwise its an edge we can find in the board
        if (direct_hit) {
            ray_exit_edge = -1;
        } else {
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

}
