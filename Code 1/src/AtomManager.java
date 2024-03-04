import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AtomManager {
    private List<Atom> atoms;

    public AtomManager() {
        this.atoms = new ArrayList<>();
    }


        public static void main(String[] args) {//Testing Purposes

            AtomManager atomManager = new AtomManager();

            atomManager.createAtom(1, 2);
            atomManager.createAtom(3, 4);

            List<Atom> allAtoms = atomManager.getAtoms();

            for (Atom atom : allAtoms) {
                System.out.println(atom.toString());
            }


            //setter_generatingAtoms();
        }

    public void createAtom(int x, int y) {
        Atom atom = new Atom(x, y);
        atoms.add(atom);
    }

    public static void setter_generatingAtoms() { //In Progress - Abdullah
        ArrayList<int[]> coordinates = new ArrayList<>();
        Random random = new Random();
        int count = 0;

        System.out.println("Generated pairs:");

        // Continue generating pairs until we have 6 unique pairs
        while (count < 6) {
            //Hard coded numbers; CHANGE after board implementation.
            int firstNumber = random.nextInt(9);
            int secondNumber = random.nextInt(10); //secondNumber would be the columns, and they can be negative too.

            //Check if coordinates are in appropiate range and not Duplicates.
            if (Utility.inRange(firstNumber, secondNumber) && !Utility.containsPair(coordinates, firstNumber, secondNumber)) {
                int[] pair = {firstNumber, secondNumber};
                coordinates.add(pair);
                System.out.println("(" + firstNumber + ", " + secondNumber + ")");
                count++;
            }
        }

    }

    public List<Atom> getAtoms() {
        return atoms;
    }
}
