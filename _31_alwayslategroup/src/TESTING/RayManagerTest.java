package TESTING;

import GUI.GameBoard;
import OBJECTS.Edge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import OBJECTS.*;
import MATH.*;
import GUI.*;
import UTILITY.*;

class RayManagerTest {

    GameBoard test_board = new GameBoard();

    // test to check correct entrance and exit points for ray with NO atoms in its path
    @Test
    public void rayNoAtoms() {
        EdgeManager test_edge = new EdgeManager();
        // using the constructor specifically made for testing so we get empty board,
        // this is done so we can MANUALLY set atoms (if theyre random tests may fail
        // if we change the code such that the seed produces different results)
        AtomManager test_atom_manager = new AtomManager('t');

        // edge 1 has index 0
        int edge_index = 0;
        Edge current_edge = test_edge.getEdge(edge_index);
        RayManager current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points
        Assertions.assertEquals(1, current_ray.ray_entrance_edge);
        Assertions.assertEquals(28, current_ray.ray_exit_edge);
    }

    // test to check if direct hit occurred (meaning ray exit point will be -1)
    @Test
    public void rayDirectHit() {
        EdgeManager test_edge = new EdgeManager();
        // using the constructor specifically made for testing so we get empty board,
        // this is done so we can MANUALLY set atoms (if theyre random tests may fail
        // if we change the code such that the seed produces different results)
        AtomManager test_atom_manager = new AtomManager('t');

        // setting atom in centre of board (along main diagonal)
        test_atom_manager.createAtom(4,0);

        // edge 1 has index 0
        int edge_index = 0;
        Edge current_edge = test_edge.getEdge(edge_index);
        RayManager current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter at 1, get absorbed)
        Assertions.assertEquals(1, current_ray.ray_entrance_edge);
        Assertions.assertEquals(-1, current_ray.ray_exit_edge);




        /*
            now we test the direct hit if atom is at the edge of board
         */

        // setting atom at edge of board
        test_atom_manager.createAtom(3,-3);

        // edge 7 has index 6
        edge_index = 6;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter at 7, get absorbed)
        Assertions.assertEquals(7, current_ray.ray_entrance_edge);
        Assertions.assertEquals(-1, current_ray.ray_exit_edge);

    }


    // test to check simple paths (one atom direction change)
    @Test
    public void raySimplePath() {
        EdgeManager test_edge = new EdgeManager();
        // using the constructor specifically made for testing so we get empty board,
        // this is done so we can MANUALLY set atoms (if theyre random tests may fail
        // if we change the code such that the seed produces different results)
        AtomManager test_atom_manager = new AtomManager('t');
        int edge_index = -1;
        Edge current_edge;
        RayManager current_ray;

        // setting atoms for testing
        test_atom_manager.createAtom(1,0);
        test_atom_manager.createAtom(7,-3);


        /*
            -- Simple Path --
        */
        // testing edge 54 for simple path
        edge_index = 53;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter, change path once and exit another edge)
        Assertions.assertEquals(54, current_ray.ray_entrance_edge);
        Assertions.assertEquals(2, current_ray.ray_exit_edge);


        // testing edge 29 for simple path
        edge_index = 28;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter, change path once and exit another edge)
        Assertions.assertEquals(29, current_ray.ray_entrance_edge);
        Assertions.assertEquals(21, current_ray.ray_exit_edge);


        // testing edge 9 for simple path
        edge_index = 8;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter, change path once and exit another edge)
        Assertions.assertEquals(9, current_ray.ray_entrance_edge);
        Assertions.assertEquals(17, current_ray.ray_exit_edge);


    }

    // test to check complex path (multiple atoms changing direction)
    @Test
    public void rayComplexPath() {
        EdgeManager test_edge = new EdgeManager();
        // using the constructor specifically made for testing so we get empty board,
        // this is done so we can MANUALLY set atoms (if theyre random tests may fail
        // if we change the code such that the seed produces different results)
        AtomManager test_atom_manager = new AtomManager('t');
        int edge_index = -1;
        Edge current_edge;
        RayManager current_ray;

        // setting atoms for testing
        test_atom_manager.createAtom(1,1);
        test_atom_manager.createAtom(3,-3);
        test_atom_manager.createAtom(7,-3);
        test_atom_manager.createAtom(7,1);
        test_atom_manager.createAtom(4,4);




        /*
            -- Complex Path --
        */
        // testing edge 1 for changing path multiple times and exiting correct edge
        edge_index = 0;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit other edge)
        Assertions.assertEquals(1, current_ray.ray_entrance_edge);
        Assertions.assertEquals(49, current_ray.ray_exit_edge);


        // testing edge 25 for changing path multiple times and exiting correct edge
        edge_index = 24;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit other edge)
        Assertions.assertEquals(25, current_ray.ray_entrance_edge);
        Assertions.assertEquals(5, current_ray.ray_exit_edge);


    }

    // test to check reflections (either from circle of influence at edge or atoms either side)
    @Test
    public void rayReflection() {
        EdgeManager test_edge = new EdgeManager();
        // using the constructor specifically made for testing so we get empty board,
        // this is done so we can MANUALLY set atoms (if theyre random tests may fail
        // if we change the code such that the seed produces different results)
        AtomManager test_atom_manager = new AtomManager('t');
        int edge_index = -1;
        Edge current_edge;
        RayManager current_ray;

        // setting atoms for testing
        test_atom_manager.createAtom(2,1);
        test_atom_manager.createAtom(3,-1);
        test_atom_manager.createAtom(7,-4);
        test_atom_manager.createAtom(5,3);



        /*
            -- Total Reflection From Circle Of Influence --
        */
        // testing edge 15 for total reflection based on circle of influence
        edge_index = 14;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(15, current_ray.ray_entrance_edge);
        Assertions.assertEquals(15, current_ray.ray_exit_edge);


        // testing edge 18 for total reflection based on circle of influence
        edge_index = 17;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(18, current_ray.ray_entrance_edge);
        Assertions.assertEquals(18, current_ray.ray_exit_edge);


        // testing edge 33 for total reflection based on circle of influence
        edge_index = 32;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(33, current_ray.ray_entrance_edge);
        Assertions.assertEquals(33, current_ray.ray_exit_edge);


        // testing edge 36 for total reflection based on circle of influence
        edge_index = 35;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(36, current_ray.ray_entrance_edge);
        Assertions.assertEquals(36, current_ray.ray_exit_edge);


        /*
            -- Total Reflection From Two Atoms Either Side --
        */
        // testing edge 1 for total reflection based on circle of influence
        edge_index = 0;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(1, current_ray.ray_entrance_edge);
        Assertions.assertEquals(1, current_ray.ray_exit_edge);


        // testing edge 28 (opposite side of board) for total reflection based on circle of influence
        edge_index = 27;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(28, current_ray.ray_entrance_edge);
        Assertions.assertEquals(28, current_ray.ray_exit_edge);

    }


    // test to check ALL paths
    @Test
    public void rayAllPath() {
        EdgeManager test_edge = new EdgeManager();
        // using the constructor specifically made for testing so we get empty board,
        // this is done so we can MANUALLY set atoms (if theyre random tests may fail
        // if we change the code such that the seed produces different results)
        AtomManager test_atom_manager = new AtomManager('t');
        int edge_index = -1;
        Edge current_edge;
        RayManager current_ray;

        // setting atoms for testing
        test_atom_manager.createAtom(0,3);
        test_atom_manager.createAtom(6,-3);
        test_atom_manager.createAtom(3,3);
        test_atom_manager.createAtom(5,2);
        test_atom_manager.createAtom(1,2);
        test_atom_manager.createAtom(8,0);






        /*
            -- Simple Direct Hit --
        */
        // testing edge 39 for direct hit
        edge_index = 38;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and be absorbed)
        Assertions.assertEquals(39, current_ray.ray_entrance_edge);
        Assertions.assertEquals(-1, current_ray.ray_exit_edge);

        // testing edge 45 for direct hit
        edge_index = 44;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and be absorbed)
        Assertions.assertEquals(45, current_ray.ray_entrance_edge);
        Assertions.assertEquals(-1, current_ray.ray_exit_edge);


        /*
            -- Simple Path --
        */
        // testing edge 34 for simple path
        edge_index = 33;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter, change path once and exit another edge)
        Assertions.assertEquals(34, current_ray.ray_entrance_edge);
        Assertions.assertEquals(38, current_ray.ray_exit_edge);


        /*
            -- Complex Direct Hit --
        */
        // testing edge 31 for changing path twice then eventually directly hitting atom
        edge_index = 30;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and be absorbed)
        Assertions.assertEquals(31, current_ray.ray_entrance_edge);
        Assertions.assertEquals(-1, current_ray.ray_exit_edge);


        /*
            -- Complex Path --
        */
        // testing edge 46 for changing path multiple times and exiting correct edge
        edge_index = 45;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit other edge)
        Assertions.assertEquals(46, current_ray.ray_entrance_edge);
        Assertions.assertEquals(41, current_ray.ray_exit_edge);


        // testing edge 24 for changing path multiple times and exiting correct edge
        edge_index = 23;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit other edge)
        Assertions.assertEquals(24, current_ray.ray_entrance_edge);
        Assertions.assertEquals(44, current_ray.ray_exit_edge);


        /*
            -- Total Reflection From Circle Of Influence --
        */
        // testing edge 30 for total reflection based on circle of influence
        edge_index = 29;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(30, current_ray.ray_entrance_edge);
        Assertions.assertEquals(30, current_ray.ray_exit_edge);


        // testing edge 30 for total reflection based on circle of influence
        edge_index = 46;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(47, current_ray.ray_entrance_edge);
        Assertions.assertEquals(47, current_ray.ray_exit_edge);


        /*
            -- Total Reflection From Two Atoms Either Side --
        */
        // testing edge 37 for total reflection based on circle of influence
        edge_index = 36;
        current_edge = test_edge.getEdge(edge_index);
        current_ray = new RayManager(current_edge);

        // do path
        current_ray.rayPath(test_edge, test_atom_manager);

        // testing ray entrance and exit points (should enter and exit same edge)
        Assertions.assertEquals(37, current_ray.ray_entrance_edge);
        Assertions.assertEquals(37, current_ray.ray_exit_edge);




    }

}