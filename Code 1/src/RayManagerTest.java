import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayManagerTest {

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

}