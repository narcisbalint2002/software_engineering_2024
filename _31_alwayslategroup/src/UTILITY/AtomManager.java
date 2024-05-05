package UTILITY;

import OBJECTS.Atom;

import java.util.ArrayList;
import java.util.Random;

public class AtomManager {
    private ArrayList<Atom> atoms;


    // this is ONLY really for testing, so it generates empty board,
    // then we can specify atom coordinates and test if the ray path
    // is correct with those specified atoms (instead of random atoms,
    // to avoid the risk of seed producing different results if we
    // change the code further down the line)
    // also, ANY character can be passed in and this will trigger
    public AtomManager(char any_character) {
        this.atoms = new ArrayList<>();
    }

    // by default no arguments means will generate 6 atoms as normal
    public AtomManager() {
        this.atoms = new ArrayList<>();
        generateRandomAtoms();
    }

    public static void main(String[] args) {
        AtomManager manager = new AtomManager();
        System.out.println(manager);
    }


    public void createAtom(int x, int y) {
        Atom atom = new Atom(x, y);
        atoms.add(atom);
    }


    public void generateRandomAtoms() {
        ArrayList<int[]> coordinates = new ArrayList<>();
        Random random;

        // if seed is 0, use random seed, otherwise use assigned seed
        if (Main.SEED == 0) {
            random = new Random();
        } else {
            random = new Random(Main.SEED);
        }

        int count = 0;

        System.out.println("Generated pairs:");

        while (count < Main.NUM_ATOMS) {
            //Hard coded numbers; CHANGE after board implementation.
            int row = random.nextInt(9);
            int column = random.nextInt(9) - 4;

            //Check if coordinates are in appropriate range and not Duplicates.
            if (Utility.inRange(row, column) && !Utility.containsPair(coordinates, row, column)) {
                int[] pair = {row, column};
                createAtom(row, column);
                coordinates.add(pair);
                //System.out.println("(" + row + ", " + column + ")");
                count++;
            }
        }

    }

    public ArrayList<Atom> getAtoms() {
        return atoms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OBJECTS.Atom Manager:\n");
        for (Atom atom : atoms) {
            sb.append(atom.toString()).append("\n");
        }
        return sb.toString();
    }
}
