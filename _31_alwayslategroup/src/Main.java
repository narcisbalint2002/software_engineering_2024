//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

//        entire linked list of board
        DoublyLinkedList board_data = new DoublyLinkedList();


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
                System.out.printf("%d ", i);

//                for current column in node row, set it to not have atom
                new_map.put(i, false);
                count++;
                i++;
            }

//            add hashmap to row
            board_data.addLast(new_map);
            System.out.println(new_map);

//            this is to make sure width starts to decrease after reaching middle
            if (current_height >= (max_height / 2)) {
                current_width--;
            } else {
                current_width++;
            }

//            move to next row down
            current_height++;
        }


        System.out.println("\n\n" + board_data);

        System.out.println(board_data.first().isEmpty());



    }
}