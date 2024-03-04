import java.util.ArrayList;

public final class Utility {


    private Utility() {
        throw new AssertionError("Utility should not be instantiated");
    }


    public static boolean inRange(int x, int y){ //In Progress for Liam.

        //Still need to figure out the ranges for each row.
        //mybe we can add enum; when we make the board to hold row ranges.
        //mybe we can add the information to row nodes.
        //mybe a function that goes through the board structure, and collects the information.
        //Will do after new board implementation.

        return true;
    }

    public static boolean containsPair(ArrayList<int[]> coordinates, int firstNumber, int secondNumber) {
        int[][] list = new int[0][];
        for (int[] pair : list) {
            if (pair[0] == firstNumber && pair[1] == secondNumber) {
                return true;
            }
        }
        return false;
    }


}
