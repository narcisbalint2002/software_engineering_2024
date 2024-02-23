// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {

        //Create new Frame

        JFrame frame = new JFrame("Game board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Note: Because I am using the method paintComponent that print
         * one element at a time, I have to print all the hexagons from our gameboard
         * at once, that means I have to calculate their position and store it in an array
         * list. For testing purposes, I will first try to print the first row made up
         * of 4 hexagon.*/



        frame.add(new gameBoard());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gameBoardFullScreen(frame);
    }

    public static void gameBoardFullScreen(JFrame aFrame)
    {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screen_size.width, screen_size.height);
        aFrame.setLocationRelativeTo(null);
    }
}