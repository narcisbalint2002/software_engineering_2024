package UTILITY;

import OBJECTS.Atom;

import java.util.ArrayList;
import java.util.Random;

/**
 * The AtomManager class manages atoms on the board.
 * It provides functionality for creating atoms with specified (x, y) coordinates or generating random atoms.

 * Atoms can be created by either passing a char parameter, which is used as a seed for generating atoms
 * for test case purposes, or with no parameters, allowing for the generation of random atoms within the board.

 * This class also provides methods to retrieve the list of atoms created.
 */
public class AtomManager {
    private ArrayList<Atom> atoms;

    /**
     * Constructs an AtomManager with an empty list of atoms.
     * This constructor is primarily used for testing purposes,
     * allowing the creation of an empty board for custom atom placement.
     *
     * @param any_character Any character value triggers the generation of an empty atoms Arraylist.
     */
    public AtomManager(char any_character) {
        this.atoms = new ArrayList<>();
    }

    /**
     * Constructs an AtomManager with a set of atoms.
     * This is the main constructor used in the game,
     * allowing the creation of randomly generated atoms.
     */
    public AtomManager() {
        this.atoms = new ArrayList<>();
        generateRandomAtoms();
    }

    public static void main(String[] args) {
        AtomManager manager = new AtomManager();
        System.out.println(manager);
    }


    /**
     * Constructs atoms with specified coordinates and adds it to the list of atoms.
     *
     * @param x The x-coordinate for the atom.
     * @param y The y-coordinate for the atom.
     */
    public void createAtom(int x, int y) {
        Atom atom = new Atom(x, y);
        atoms.add(atom);
    }


    /**
     * Generates a set of non-duplicate atoms within the specified range of the board.
     * It completes the list of atoms with the predefined number of atoms in Main.NUM_ATOMS.
     *
     * Atoms are randomly placed within the board's boundaries, ensuring that there are no
     * duplicate coordinates.
     *
     * If the seed value is indicated in Main.SEED !=0, initializes the random number generator with that seed
     * to ensure predictability of atom placement for testing purposes.
     *
     * Note: The implementation uses hard coded values for the board dimensions and atom
     * generation.
     */
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
        sb.append("Atom Manager:\n");
        for (Atom atom : atoms) {
            sb.append(atom.toString()).append("\n");
        }
        return sb.toString();
    }
}
