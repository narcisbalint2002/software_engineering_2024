public class Play {
    public static void main(String[] args) {


        //First Construct the Board and Display.

        //Generate Atoms using AtomManager and Set them on the board using Narcis function.

        //Generate Edge List using EdgeManager. (this will create each ray respective of edge number).

        //Request user edge number as input.

        //Generate all final trajectories using TrajectoryManager.

        //Find ray with the edge number and launch it using calculate_trajectory function.

        //Record ray input edge number and output edge number in RayManager ?

        //End of sprint 2.


        /* The reason as to why I prioritised this over the recursive calculate_trajectory function.

            Because we have a better idea on what the function parameters are needed and ACCESSIBLE.

         */

        AtomManager atomManager = new AtomManager();
        atomManager.getAtoms().add(new Atom(2, -2));
        System.out.println(atomManager+"\n");
        int input = 4;

        TrajectoryManager trajectoryManager = new TrajectoryManager();


        Coordinate temp = new Coordinate(2, -2);

        Ray ray = new Ray(temp, 4, trajectoryManager.getTrajectory(4));
        System.out.println(ray+"\n");

        System.out.println(trajectoryManager.getTrajectory(4));

        Utility.calculate_Tracjectory(ray, atomManager.getAtoms(), trajectoryManager.getTrajectoriesList());

    }
}
