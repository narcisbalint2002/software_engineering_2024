//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

//////                    IGNORE - was from when this was hashmap
//    public static HashMap<Integer, Integer> atoms_coordinates = new HashMap<>();

//    create nested arraylist (arraylists inside an arraylist)
    public static ArrayList<ArrayList<Integer>> atoms_coordinates = new ArrayList<>();

//    total number of atoms in board
    public static int num_atoms = 3;

//    creates board
    public static void boardCreate(DoublyLinkedList board_data) {
        //        set the max width to the total number of hexagons (including those in between)
//        e.g.
//                  O O O
//                 O O O O
//                  O O O
//        this hexagon has max_width = 7, this is because we see middle row has 4, but the hexagon pattern actually
//        allows a space in between, like we see width 3 first row, so if you were to count continuously it is width 7
//
//        set max height to anything less than or equal to max width (but probably something that makes the overall
//        shape of board look hexagonal)
        int max_width = 9;
        int max_height = 5;

        int current_height = 0;
        int current_width = max_width - max_height - 1;


//        code to create board, loops while max height of board not reached
        while (current_height < max_height) {
//            temporary new hashmap to be added for current row
            HashMap<Integer, Boolean> new_map = new HashMap<Integer, Boolean>();

//            used for loops
            int i = 0;

            if (current_height <= (max_height / 2)) {
                i -= current_height;
            } else {
                i = i - (max_height / 2);
            }

            System.out.println();

//            this makes sure correct width for loop below is printed
            int count = 0;
//            System.out.println("WIDTH: " + current_width);
            while (count < current_width) {
//                System.out.printf("%d ", i);

//                for current column in node row, set it to not have atom
                new_map.put(i, false);
                count++;
                i++;
            }

//            add hashmap to row
            board_data.addLast(new_map);
//            System.out.println(new_map);

//            this is to make sure width starts to decrease after reaching middle
            if (current_height >= (max_height / 2)) {
                current_width--;
            } else {
                current_width++;
            }

//            move to next row down
            current_height++;
        }
    }

//    will return an array list containing arrays with coordinates, these are the coordinates of the atoms that are
//    in the board, NOT the ones the user guesses
//    e.g.
//          {
//              {0,0},
//              {1,0},
//              {2,0}
//          }
    public static ArrayList<ArrayList<Integer>> atomsInput(DoublyLinkedList board, char user) {
//////                    IGNORE - was from when this was hashmap
////        create a hashmap that will have placement of atoms in format:
////          {row1=col1, row2=col1, row3=col1}
//        HashMap<Integer, Integer> atoms = new HashMap<>();

        ArrayList<ArrayList<Integer>> atoms = new ArrayList<>();

        int row_index = -1;
        int col_index = -1;

//        currently set to place x atoms
        for (int i = 0; i < num_atoms; i++) {
//            condition for while loop reset to ensure runs every time, this will run while input not valid,
//            i.e. while coordinates inputted by user cannot be found in the board, meaning they dont exist
            boolean valid_input = false;

//            while loop to run while current coordinates NOT valid
            while (!valid_input) {
                if (user == 'a') {
//                    put code to generate random numbers here (instead of two zeros)
                    row_index = 0;
                    col_index = 0;
                }
//                if not ai/computer, then HAS to be player
                else {
                    Scanner input = new Scanner(System.in);
                    System.out.printf("\nEnter valid coordinates for atom %d, separated by comma:\n\te.g. 0,0\n", i+1);
                    String atom_coordinates = input.nextLine();

//                    split user input 0,0 into {"0","0"}
                    String[] num_arr = atom_coordinates.split(",");

//                    converting each number string in array to actual integers
//                    like {"0","0"} to:
//                      row_index = 0;
//                      col_index = 0;
                    row_index = Integer.parseInt(num_arr[0]);
                    col_index = Integer.parseInt(num_arr[1]);
                }


//                need function to edit item Node at index i of linked list, then within it can check if second integer
//                is in array of columns for that row
//                  so setBoolean does this, it goes to specified row and column, setting false to true for that
//                  coordinate, meaning an atom is there
//                getBoolean ONLY checks IF the coordinate is valid, exact same as setBoolean but doesnt edit it
                valid_input = board.getBoolean(row_index, col_index);

                if (!valid_input) {
//                    mostly done to alert user that coordinates they typed were invalid
                    System.out.println("Invalid coordinates (not in board)");
                } else {
//////                    IGNORE - was from when this was hashmap
//                    atoms.put(row, col);

//                    basically just adding an array list with coordinates
//                    e.g.
//                              {0,0}
//                    to main list
//                    e.g. (once added)
//                          {
//                              {0,0},
//                              {1,0}
//                          }


//                    HashMap<Integer, Boolean> row = board.get(row_index);
//                    row.put(col_index, true);


                    if (user == 'p') {
                        valid_input = board.setBoolean(row_index, col_index);
                    }

                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(row_index,col_index));


                    if (atoms.contains(temp)) {
                        System.out.println("Atom already inputted");
                        i--;
                    } else {
                        atoms.add(temp);
                    }



//                    ensures break out of loop (even though condition should break anyway)
                    break;
                }

            }
        }
//        return "nested" array with atom coordinates
        return atoms;
    }

    public static void main(String[] args) {

//        entire linked list of board
        DoublyLinkedList board = new DoublyLinkedList();

//        creates board in variable board_data
        boardCreate(board);



//        System.out.println(board_data.first().isEmpty());



////        pay attention to row and column to see if they have been updated to true, unlike rest which are still false
//        board_data.setBoolean(1,2);
        System.out.println("\n\n" + board);


        atoms_coordinates = atomsInput(board, 'p');

        for (int i = 0; i < atoms_coordinates.size(); i++) {
            System.out.println(atoms_coordinates.get(i));
        }


        System.out.println("\n\n" + board);


    }
}