import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EdgeManagerTest {

    // test to check correct edge numbers for particular edge indexes
    @Test
    public void EdgeNumberChecks() {
        EdgeManager test_edge = new EdgeManager();
        Assertions.assertEquals(1, test_edge.getEdgeNumManager(0));
        Assertions.assertEquals(32, test_edge.getEdgeNumManager(31));
        Assertions.assertEquals(54, test_edge.getEdgeNumManager(53));
        Assertions.assertEquals(9, test_edge.getEdgeNumManager(8));
    }

    // test to check if the edges that share same coordinate are correct
    @Test
    public void CornerCoordinatesCheck() {
        EdgeManager test_edge = new EdgeManager();
        Coordinate test_list = new Coordinate();


        test_list.x = 0; // set x
        test_list.y = 0; // set y
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(0).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(0).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(1).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(1).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(53).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(53).x);



        test_list.x = 8; // set x
        test_list.y = 0; // set y
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(26).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(26).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(27).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(27).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(28).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(28).x);



        test_list.x = 4; // set x
        test_list.y = -4; // set y
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(8).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(8).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(9).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(9).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(10).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(10).x);



        test_list.x = 4; // set x
        test_list.y = 4; // set y
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(35).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(35).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(36).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(36).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(37).x);
        Assertions.assertEquals(test_list.x, test_edge.getCoordinateManager(37).x);
    }


    // test to check correct direction for particular edge indexes
    @Test
    public void DirectionChecks() {
        EdgeManager test_edge = new EdgeManager();

        int index;
        int trajectory_index;
        TrajectoryManager test_trajectory_manager = new TrajectoryManager();
        Trajectory test_trajectory;

        // here is just testing the ray directions based on indexes of edge list
        // (index 0 in edge list is edge 1, index 1 is edge 2, etc.)
        index = 0;
        trajectory_index = TrajectoryName.DOWN_RIGHT.getTrajectoryIndex(); // this is the direction to in
        test_trajectory = test_trajectory_manager.trajectories.get(trajectory_index);
        Assertions.assertEquals(trajectory_index, test_edge.getTrajectoryIndexManager(index));
        Assertions.assertEquals(test_trajectory.toString(), test_edge.getTrajectoryManager(index).toString());



        index = 53;
        trajectory_index = TrajectoryName.DOWN_LEFT.getTrajectoryIndex(); // this is the direction to in
        test_trajectory = test_trajectory_manager.trajectories.get(trajectory_index);
        Assertions.assertEquals(trajectory_index, test_edge.getTrajectoryIndexManager(index));
        Assertions.assertEquals(test_trajectory.toString(), test_edge.getTrajectoryManager(index).toString());


        index = 18;
        trajectory_index = TrajectoryName.UP_RIGHT.getTrajectoryIndex(); // this is the direction to in
        test_trajectory = test_trajectory_manager.trajectories.get(trajectory_index);
        Assertions.assertEquals(trajectory_index, test_edge.getTrajectoryIndexManager(index));
        Assertions.assertEquals(test_trajectory.toString(), test_edge.getTrajectoryManager(index).toString());



        index = 9;
        trajectory_index = TrajectoryName.RIGHT.getTrajectoryIndex(); // this is the direction to in
        test_trajectory = test_trajectory_manager.trajectories.get(trajectory_index);
        Assertions.assertEquals(trajectory_index, test_edge.getTrajectoryIndexManager(index));
        Assertions.assertEquals(test_trajectory.toString(), test_edge.getTrajectoryManager(index).toString());


        index = 36;
        trajectory_index = TrajectoryName.LEFT.getTrajectoryIndex(); // this is the direction to in
        test_trajectory = test_trajectory_manager.trajectories.get(trajectory_index);
        Assertions.assertEquals(trajectory_index, test_edge.getTrajectoryIndexManager(index));
        Assertions.assertEquals(test_trajectory.toString(), test_edge.getTrajectoryManager(index).toString());


        index = 45;
        trajectory_index = TrajectoryName.DOWN_LEFT.getTrajectoryIndex(); // this is the direction to in
        test_trajectory = test_trajectory_manager.trajectories.get(trajectory_index);
        Assertions.assertEquals(trajectory_index, test_edge.getTrajectoryIndexManager(index));
        Assertions.assertEquals(test_trajectory.toString(), test_edge.getTrajectoryManager(index).toString());


        index = 31;
        trajectory_index = TrajectoryName.UP_LEFT.getTrajectoryIndex(); // this is the direction to in
        test_trajectory = test_trajectory_manager.trajectories.get(trajectory_index);
        Assertions.assertEquals(trajectory_index, test_edge.getTrajectoryIndexManager(index));
        Assertions.assertEquals(test_trajectory.toString(), test_edge.getTrajectoryManager(index).toString());


    }

}