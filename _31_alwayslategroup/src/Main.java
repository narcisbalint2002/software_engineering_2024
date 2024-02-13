//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        DoublyLinkedList board_data = new DoublyLinkedList();


        int max_width = 9;
        int max_height = 5;

        int current_height = 0;
        int current_width = max_width - max_height - 1;


        while (current_height < max_height) {
            HashMap<Integer, Boolean> new_map = new HashMap<Integer, Boolean>();

            int i = 0;
//            int j = 0;

            if (current_height <= (max_height / 2)) {
                i -= current_height;
            } else {
                i = i - (max_height / 2);
            }

//            if (current_height % 2 == 0) {
//                while (i < current_width) {
//
//                    i++;
//                }
//                current_width++;
//            } else {
//
//            }
            System.out.println();

            int count = 0;
//            System.out.println("WIDTH: " + current_width);
            while (count < current_width) {
                System.out.printf("%d ", i);
                count++;
                i++;
            }

            if (current_height >= (max_height / 2)) {
                current_width--;
            } else {
                current_width++;
            }

            current_height++;
        }



    }
}