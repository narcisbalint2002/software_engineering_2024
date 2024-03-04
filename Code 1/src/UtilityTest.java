import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    // test for corner coordinates that should be inside board, this means the top 2, middle 2 edge, and bottom 2
    // corners of the hexagon, should pass because these are very edge ones but still inside board
    @Test
    public void CornerInRangeTest() {
        Assertions.assertTrue(Utility.inRange(0,0));
        Assertions.assertTrue(Utility.inRange(0,4));
        Assertions.assertTrue(Utility.inRange(4,-4));
        Assertions.assertTrue(Utility.inRange(4,4));
        Assertions.assertTrue(Utility.inRange(8,-4));
        Assertions.assertTrue(Utility.inRange(8,0));
    }
    // test for corner coordinates that should be outside board, this means the top 2, middle 2 edge, and bottom 2
    // corners of the hexagon, just with one column outside the range so should fail
    @Test
    public void CornerOutOfRangeTest() {
        Assertions.assertFalse(Utility.inRange(0,-1));
        Assertions.assertFalse(Utility.inRange(0,5));
        Assertions.assertFalse(Utility.inRange(4,-5));
        Assertions.assertFalse(Utility.inRange(4,5));
        Assertions.assertFalse(Utility.inRange(8,-5));
        Assertions.assertFalse(Utility.inRange(8,1));
    }
    // test for the row before and the row after the middle, should pass because columns are the very edge ones,
    // still on the board but the ones on the edge
    @Test
    public void HalfwayInRangeTest() {
        Assertions.assertTrue(Utility.inRange(3,-3));
        Assertions.assertTrue(Utility.inRange(3,4));
        Assertions.assertTrue(Utility.inRange(5,-4));
        Assertions.assertTrue(Utility.inRange(5,3));
    }
    // test for the row before and the row after the middle, should fail because is testing columns that
    // should be out of their range
    @Test
    public void HalfwayOutOfRangeTest() {
        Assertions.assertFalse(Utility.inRange(3,-4));
        Assertions.assertFalse(Utility.inRange(3,5));
        Assertions.assertFalse(Utility.inRange(5,-5));
        Assertions.assertFalse(Utility.inRange(5,4));
    }
    // test for valid rows
    @Test
    public void RowOutOfRangeTest() {
        Assertions.assertFalse(Utility.inRange(-1,0));
        Assertions.assertFalse(Utility.inRange(9,0));
    }
    // test for columns that are outside max ranges (more than row with most columns)
    @Test
    public void ColumnOutOfRangeTest() {
        Assertions.assertFalse(Utility.inRange(4,-5));
        Assertions.assertFalse(Utility.inRange(4,5));
    }
}