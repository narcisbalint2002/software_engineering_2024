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

    // seed (if 0 will used random seed in AtomManager.setter_generatingAtoms method, seed 1 was used heavily for testing)
    public static final int SEED = 0;



    public static int width = 0;
    public static int height = 0;
    public static double scale = 1.0;


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
            // all we need for game loop
            System.out.print("\nThe following coordinates represent the positions of the atoms:\n");

            GameLogic.gameLoop();

            System.exit(0);

    }
}