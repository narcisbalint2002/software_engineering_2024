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

        // here is just testing the ray directions based on indexes of edge list
        // (index 0 in edge list is edge 1, index 1 is edge 2, etc.)

        Assertions.assertEquals(1, test_edge.getXDirectionManager(0));
        Assertions.assertEquals(0, test_edge.getYDirectionManager(0));

        Assertions.assertEquals(1, test_edge.getXDirectionManager(53));
        Assertions.assertEquals(-1, test_edge.getYDirectionManager(53));

        Assertions.assertEquals(-1, test_edge.getXDirectionManager(18));
        Assertions.assertEquals(1, test_edge.getYDirectionManager(18));

        Assertions.assertEquals(0, test_edge.getXDirectionManager(9));
        Assertions.assertEquals(1, test_edge.getYDirectionManager(9));

        Assertions.assertEquals(0, test_edge.getXDirectionManager(36));
        Assertions.assertEquals(-1, test_edge.getYDirectionManager(36));

        Assertions.assertEquals(1, test_edge.getXDirectionManager(45));
        Assertions.assertEquals(-1, test_edge.getYDirectionManager(45));

        Assertions.assertEquals(-1, test_edge.getXDirectionManager(31));
        Assertions.assertEquals(0, test_edge.getYDirectionManager(31));
    }

}