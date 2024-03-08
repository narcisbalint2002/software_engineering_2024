import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AtomManager {
    private List<Atom> atoms;

    public AtomManager() {
        this.atoms = new ArrayList<>();
        setter_generatingAtoms();
    }

    public static void main(String[] args) {
        AtomManager manager = new AtomManager();
        System.out.println(manager);
    }


    public void createAtom(int x, int y) {
        Atom atom = new Atom(x, y);
        atoms.add(atom);
    }


    public void setter_generatingAtoms() {
        ArrayList<int[]> coordinates = new ArrayList<>();
        Random random = new Random(1);
        int count = 0;

        System.out.println("Generated pairs:");

        while (count < 6) {
            //Hard coded numbers; CHANGE after board implementation.
            int row = random.nextInt(9);
            int column = random.nextInt(9) - 4;

            //Check if coordinates are in appropiate range and not Duplicates.
            if (Utility.inRange(row, column) && !Utility.containsPair(coordinates, row, column)) {
                int[] pair = {row, column};
                createAtom(row, column);
                coordinates.add(pair);
                //System.out.println("(" + row + ", " + column + ")");
                count++;
            }
        }

    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atom Manager:\n");
        for (Atom atom : atoms) {
            sb.append(atom.toString()).append("\n");
        }
        return sb.toString();
    }
}
