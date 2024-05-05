package GUI;

import java.util.ArrayList;
import java.util.Random;
import OBJECTS.*;
import UTILITY.*;
import MATH.*;

public class Board {


//    ArrayList<MATH.Coordinate> atom_coordinates = new ArrayList();

    private static class Node {

        private int row;
        private int column;
        private boolean atom;
        private Node next;

        public Node(int row, int column, boolean atom)
        {
            this.row = row;
            this.column = column;
            this.atom = atom; // Assign to instance variable
            this.next = null;
        }

        public int getRow()
        {
            return row;
        }

        public int getColumn()
        {
            return column;
        }

        public void setAtom()
        {
            atom = true;
        }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Node getNext()
        {
            return next;
        }

        public boolean getAtom()
        {
            return atom;
        }
    }

    public class List{

        private Node head;

        public List()
        {
            head = null;
        }

        public void add(int row, int column, boolean atom)
        {
            Node current = head;
            Node new_node = new Node(row, column, atom);

            if(current == null)
            {
                head = new_node;
            } else {
                while(current.getNext() != null)
                {
                    current = current.getNext();
                }
                current.setNext(new_node);
            }
        }
    }

    public static void printBoard(ArrayList<List> board)
    {
        int number_tabs = 5, x = -1;

        // Now we have to print our structure to see what we got
        for (List list : board) {
            Node current = list.head;

            //To print the gameboard in the shape of an GUI.hexagon

            for(int i = 0; i < number_tabs; i++)
            {
                System.out.print("\t");
            }

            while (current != null) {
                System.out.print("[" + current.getRow() + ", " + current.getColumn()+ ", " + current.getAtom() +"],\t");
                current = current.getNext();
            }

            System.out.println();
            System.out.println();

            number_tabs += x;

            if(number_tabs == 1)
                x = -x;
        }
    }

    public static void initialiseBoard(ArrayList<List> board)
    {
        int number_columns = 5, j, temp = 4;

        for(int i = 0; i < 9; i++)
        {
            board.add(new Board().new List()); // changed from board.add(new List()) BECAUSE no longer static

            if (number_columns < 10)
            {
                for (j = -i; j < number_columns - i; j++) {
                    board.get(i).add(i, j, false);
                }
                number_columns++;

            } else {
                for (j = -4; j < temp; j++) {
                    board.get(i).add(i, j, false);
                }
                temp--;
            }
        }
    }

    public static Coordinate generateAtom(ArrayList<List> board)
    {
        /*This is an auxiliary function to generate some random coordinates.*/

        /*I initialise the coordinates as an invalid pair so the loop can call the function validateNode()*/
        int x = -1, y = -1;

        Random rand = new Random();
        rand.setSeed(1);

        while(!validateNode(x, y, board)) {
            x = rand.nextInt(9); // row
            y = rand.nextInt(9) - 4; // if 8 then 8 - 4 = 0 if 0 then 0 - 4 = -4. So the interval is [-4, 4];
        }

        //When the compiler gets here, it means a valid pair of coordinates has been extracted.

        //Create duple containing the coordinates

//        // OLD WAY (does NOT use MATH.Coordinate class)
//        ArrayList<Integer> atom = new ArrayList<>();
//        atom.add(x);
//        atom.add(y);

        Coordinate atom = new Coordinate(x, y);

        System.out.println(x + ", " + y);

        return atom;
    }


    private static boolean validateNode(int x, int y, ArrayList<List> board)
    {
        for (List list:board)
        {
            Node current = list.head;

            while(current != null) //I'm going through every node for this row
            {
                if(current.getRow() == x && current.getColumn() == y && !current.getAtom())
                    return true;

                current = current.getNext();
            }
        }

        //If the compiler gets here it means there has been no match, therefore the node does not exist.
        return false;
    }



    public static void setAtoms(ArrayList<List> board){
        /*In this function I will generate random number for:
         * - row of an atom: 0 to 8
         * - column of an atom: -4 to 4
         *
         * Two extern functions are going to be used:
         * - validateCoordinates(): checks if the extracted coordinates exists on the game board
         * - checkForAtom(): checks if an atom has already been placed on that specific GUI.hexagon.*/

        //The number of atoms is 6

        final int number_of_atoms = Main.NUM_ATOMS;
        Coordinate atom_to_set; // uses x and y instead of arraylist 2 indexes

        for(int i = 0; i < number_of_atoms; i++)
        {
            atom_to_set = generateAtom(board);

            for(List list: board)
            {
                Node current = list.head;

                while(current != null)
                {
                    if(current.getRow() == atom_to_set.getX() && current.getColumn() == atom_to_set.getY()) //Match the position
                        current.setAtom();

                    current = current.getNext();
                }
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<List> board = new ArrayList<>();

        initialiseBoard(board);
        printBoard(board);

        setAtoms(board);
        printBoard(board);
    }
}
