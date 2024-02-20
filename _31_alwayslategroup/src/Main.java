//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

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
//                    basically just adding an array list with coordinates to an array list of array lists
//                    e.g. current list of coordinates:
//                              {0,0}
//                    which we are adding to main list
//                    e.g. main list:
//                          {
//                              {1,0},
//                              {0,0}    <--- the array list we just added
//                          }


//                    if function is run for AI/computer, then this must be start of game,
//                    so it is currently setter's turn to set atoms, so we SET the atoms,
//                    meaning we actually update the board, NOT just check it
//                      for this, we use setBoolean instead of getBoolean
                    if (user == 'a') {
                        valid_input = board.setBoolean(row_index, col_index);
                    }

//                    this is JUST an option for debugging, will set atoms using input,
//                    for DEVELOPERS ONLY, would never actually be done by player
                    else if (user == 'd') {
                        valid_input = board.setBoolean(row_index, col_index);
                    }

//                    temporary list, this is the current coordinate list to be added to
//                    main array list of coordinates
                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(row_index,col_index));


//                    if current coordinates are already in list, decrement i (this will ensure
//                    stays on current number of atoms in the for loop, so it will basically do
//                    same loop iteration until an atom that isnt already in the board is added)
                    if (atoms.contains(temp)) {
                        System.out.println("Atom already inputted");
                        i--;
                    }
//                    otherwise atom not in board, so add to our list of coordinates
                    else {
                        atoms.add(temp);
                    }

//                    ensures break out of loop (even though condition should break anyway)
                    break;
                }

            }
        }
//        return "nested" array with atom coordinates
//        (i.e. arraylist of all atom coordinates arraylist)
        return atoms;
    }

    public static void main(String[] args) {

//        entire linked list of board
        DoublyLinkedList board = new DoublyLinkedList();

//        creates board in variable board_data
        boardCreate(board);


////        to check if board first row is empty
////        (could be used in tests in future)
//        System.out.println(board.first().isEmpty());



////        sets specific row and column to be true, meaning atom will be there
////        (could be used in tests in future)
//        board.setBoolean(1,2);


//        print board
        System.out.println("\n\n" + board);


//        returns an arraylist of arraylists of coordinates, so basically:
//          {
//              {1,0},
//              {0,0}
//          }
//        Functionality:
//        - 'a' means AI/computer, so in this case it would also set atoms in the board,
//          then return the location of those items
//        - 'p' means player, so this could be used for getting final input of atom guesses
//          then return the atom coordinates guessed by player
//        - 'd' means developer, so basically is exactly like player BUT also sets atoms,
//          this will just be used for testing (normally only AI sets, player just checks if
//          inputted coordinates are in the board)
        atoms_coordinates = atomsInput(board, 'd');


//        this is just for showcasing the arraylist of all coordinates
        for (int i = 0; i < atoms_coordinates.size(); i++) {
            System.out.println(atoms_coordinates.get(i));
        }


//        print full board again
        System.out.println("\n\n" + board);


    }
}