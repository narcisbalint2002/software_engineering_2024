//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Main {

    /*The following data structure is an Array List of Array List of Integers. Each nested Array List represents on row of the game board, and each integer inside the list represents the column index. I.e:
         {0,1,2,3,4},        <--- row 0 (i.e. index 0)
        {-1,0,1,2,3,4},     <--- row 1 (i.e. index 1*/

//    public static ArrayList<ArrayList<Integer>> board_coordinates_list = new ArrayList<>();

    //The number of atoms to be placed.
    public static final int NUM_ATOMS = 6;

    public static final int NUM_PLAYERS = 1;



    public static int width = 0;
    public static int height = 0;
    public static double scale = 1.0;


//    //The following method implements the game board functionally, which means is storing in memory the coordinates of each of the hexagons.
//    public static void boardCreate(BoardStructure board_data) {
//
//        /*When setting the maximum width of the game board, we have to count the spaces in between hexagons as well as represented in the following figure:
//           O O O
//          O O O O
//           O O O
//          For this example, the game board has a maximum width of 7. The final board will have a maximum width of 15 as presented below.*/
//
//        int max_width = 15;
//        int max_height = 9;
//
//        int current_height = 0;
//        int current_width = max_width - max_height - 1;
//
//
//        /*The following loop will add one hexagon to the board until the maximum height has not been reached.*/
//
//        while (current_height < max_height) {
//
//            HashMap<Integer, Boolean> new_map = new HashMap<Integer, Boolean>();
//
//
//            /*This will create a temporary row (a list of integers) which will later be a*/
//            ArrayList<Integer> temp_row = new ArrayList<>();
//
//            int i = 0;
//
//
//            if (current_height <= (max_height / 2)) {
//                i -= current_height;
//            } else {
//                i = i - (max_height / 2);
//            }
//
//            System.out.println();
//
//            int count = 0;
//
//            while (count < current_width) {
//
//                temp_row.add(i);
//
//                //Set the new hexagon to false which indicates that it does not contain an atom.
//                new_map.put(i, false);
//                count++;
//                i++;
//            }
//
//            //Add the temporary row to the game board.
//            board_coordinates_list.add(temp_row);
//
//            //Add hashmap to row.
//            board_data.addLast(new_map);
//
//
//            //After reaching the middle of the board (maximum width), the width should start to decrease.
//            if (current_height >= (max_height / 2)) {
//                current_width--;
//            } else {
//                current_width++;
//            }
//
//            //Move to the next row
//            current_height++;
//        }
//    }
//
//   /*One of the functions of this method is to return the atoms that have been placed on the game board.*/
//public static ArrayList<ArrayList<Integer>> atomsInput(BoardStructure board, char user) {
//
//    ArrayList<ArrayList<Integer>> atoms = new ArrayList<>();
//
//    int row_index = -1;
//    int col_index = -1;
//
//
//    /*Here we are defining the ranges for the random numbers extraction. In case invalid coordinates will be extracted, the program is trained to re-extract random coordinates until they are valid.*/
//    int row_min = 0;
//    int row_max = board.size() - 1;
//    int col_min = (board.size() / 2) * -1;
//    int col_max = (board.size() / 2);
//
//
//    for (int i = 0; i < NUM_ATOMS; i++) {
//
//        /*The following loop will make sure that the coordinates inputted or extracted are valid.*/
//        boolean valid_input = false;
//
//        while (!valid_input) {
//
//            //A stands for A.I. which tells the code that the atoms have to be randomly placed by the A.I.
//            if (user == 'a') {
//
//                /*Random generation of pairs of coordinates.*/
//                row_index = (int) (Math.random() * (row_max - row_min + 1)) + row_min;
//                col_index = (int) (Math.random() * (col_max - col_min + 1)) + col_min;
//
//
//                //System.out.printf("\natom %d:  %d,%d\n", i + 1, row_index, col_index);
//            }
//                /*In case the developer wants to manually input the atoms, he can do so using the console. You just need to change the value of the variable "user" from "a" to "d"*/
//                else {
//
//                    boolean input_received = false;
//
//                /*The following loop checks if the inputted coordinates follow our template which is : x , y*/
//
//                    while (!input_received) {
//
//                        //Here we are asking the user for coordinates
//                        Scanner input = new Scanner(System.in);
//                        System.out.printf("\nEnter valid coordinates for atom %d, separated by comma:\n\te.g. 0,0\n", i+1);
//                        String atom_coordinates = input.nextLine();
//
//
//                        /*The way we store our coordinates is as follows:
//                        * A pair of coordinates would be 0, 1. If they are inputted by the developer/user from the console, firstly it's going to be stored as a string. The string is then going to be split using ',' as separator. Now we have
//                        * obtained 2 string the represent 2 digits. We convert the strings to integer values and store them.*/
//
//                        try {
//
//                            String[] num_arr = atom_coordinates.split(",");
//
//                            row_index = Integer.parseInt(num_arr[0]);
//                            col_index = Integer.parseInt(num_arr[1]);
//
//                            /*Once this point is reached means that the coordinates have been correctly inputted.*/
//                            input_received = true;
//                        }
//                        catch (NumberFormatException e) {
//                            System.out.println("Use correct format 0,0 or else...");
//                        }
//                    }
//                }
//
//
//                /*The setAtom method takes a pair of coordinates (a hexagon) and changes its boolean value from false to true, meaning that there is an atom.*/
//                /*The getAtom function simply checks if the provided coordinates are valid.*/
//
//                valid_input = board.getAtom(row_index, col_index, board.size());
//
//                if (!valid_input) {
//
//                    /*The following code has been commented, because the A.I is randomly choosing the atoms, so the player does not have to know if invalid coordinates have been extracted.*/
//                    //System.out.println("Invalid coordinates (not in board)");
//                } else {
//
//                    if (user == 'a') {
//                        valid_input = board.setAtom(row_index, col_index, board.size());
//                    }
//
//                    /*The feature of setting atoms manually from the console has been implemented only for testing purposes.*/
//
//                    else if (user == 'd') {
//                        valid_input = board.setAtom(row_index, col_index, board.size());
//                    }
//
//                    ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(row_index,col_index));
//
//
//                    /*If an atom has been previously placed on the same hexagon, we make sure the count for atoms placed stays the same and we let the user know as well. The program will then ask the user for new coordinates.*/
//                    if (atoms.contains(temp)) {
//                        //Same reason as previously described.
//                        // System.out.println("Atom already inputted");
//                        i--;
//                    }
//
//                    else {
//                        atoms.add(temp);
//                    }
//
//                    break;
//                }
//
//            }
//        }
//
//    /*Return and Array List that contains nested arrays containing the coordinates of the atoms. I.e.:
//    * {
//    *       {0,1}
//    *       {1,0}
//    * }
//    * and so forth.*/
//        return atoms;
//    }

    /*The following method will allow the graphic game board to be displayed in full screen.*/
    public static void gameBoardFullScreen(JFrame aFrame)
    {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        // set width to screen size
        width = screen_size.width;
        height = screen_size.height;
//        aFrame.setSize(gameBoard.dynamicScale2(width), gameBoard.dynamicScale2(height));
        aFrame.setSize(gameBoard.dynamicScale(width), gameBoard.dynamicScale(height));

        aFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {


//        // BELOW may NOT work for multiple players (because board is updated throughout,
//        // so maybe needs to be reconstructed after each player, this is simple as all
//        // that needs to be done is copy below code INTO GameLogic.gameLoop() method)
//
//        Scanner scanner = new Scanner(System.in);
//        //Create new Frame
//
//        JFrame frame = new JFrame("Game board");
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        gameBoard board = new gameBoard();
//        /*Add a new object of the class gameBoard to our frame.*/
//        frame.add(board);
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//        gameBoardFullScreen(frame);


//        /*This data structure will store our board. */
//        BoardStructure board = new BoardStructure();
//
//
//        boardCreate(board);

        /*Here we are printing the default board to make sure the rows and columns are properly indexed.*/

//        System.out.println("The following structure is the default board with no atoms set.");
//        System.out.println("\n" + board + "\n");


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
//        //    create nested arraylist (arraylists inside an arraylist)
//        ArrayList<ArrayList<Integer>> atoms_coordinates_list = atomsInput(board, 'a');


//        String  want = null;
//
//        System.out.println("\nDo you want to print the game board including the positions of the atoms? YES/NO:");
//        want = scanner.nextLine();
//
//        while(want.toUpperCase().compareTo("NO") != 0 && want.toUpperCase().compareTo("YES") != 0)
//        {
//                System.out.println("Please enter a valid value!");
//                want = scanner.nextLine();
//        }
//
//        if(want.toUpperCase().compareTo("NO") == 0)
//            System.exit(0);
//        else
//        /*Final print of the board including the positions of the atoms.*/ {
//
//        System.out.print("\nThe following coordinates represent the positions of the atoms:\n");
//
//        /*This is another piece of code used for testing.
//
//        for (int i = 0; i < board_coordinates_list.size(); i++) {
//                 System.out.println(board_coordinates_list.get(i));
//            }
//             */
//         /*Code used to test if the atoms have been correctly stored. I've commented the code because in the final version this feature will, obviously, not be available.*/
//
//            GameLogic.gameLoop();
//
////        for (int i = 0; i < atoms_coordinates_list.size(); i++) {
////            System.out.println(atoms_coordinates_list.get(i));
////        }
////
////            System.out.println("And the following structure is the game board including the position of the atoms:");
////            System.out.println("\n" + board);
//            System.exit(0);
//        }





            // all we need for game loop
            System.out.print("\nThe following coordinates represent the positions of the atoms:\n");

            GameLogic.gameLoop();

            System.exit(0);

    }
}