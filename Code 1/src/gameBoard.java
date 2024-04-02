
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class gameBoard extends JPanel{

    public static class EndGame {
        private int x = 1000;
        private int y = 100;


        private static final int BUTTON_SIZE_Y = 75;

        private static final int BUTTON_SIZE_X = BUTTON_SIZE_Y * 3;

        public boolean isClicked(int clickX, int clickY) {
            // return true if button is clicked
            return ((clickX >= x) && (clickX <= x + BUTTON_SIZE_X)) && ((clickY >= y) && (clickY <= y + BUTTON_SIZE_Y));
        }

        public void draw(Graphics g) {
            // Draw the black hexagon
            g.setColor(Color.black); // Set fill color to black
            g.fillRoundRect(x, y, BUTTON_SIZE_X, BUTTON_SIZE_Y, 10, 10);
            g.setColor(Color.white);
            g.drawString("GUESS ATOMS", x + (BUTTON_SIZE_Y / 2), y + (BUTTON_SIZE_Y / 2));
        }
    }

    private static class NumberInfo {
        private int x;
        private int y;
        private int number;
        private static final int NUMBER_SIZE = 20;

        public NumberInfo(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public boolean isClicked(int clickX, int clickY) {
            // return true based on if the valid edge button coordinates are clicked
            return clickX >= x && clickX <= x + NUMBER_SIZE && clickY >= y && clickY <= y + NUMBER_SIZE;
        }

        public void draw(Graphics g) {
            g.drawString(Integer.toString(number), x, y + NUMBER_SIZE);
        }
    }


    private static List<NumberInfo> numbers;
    private static final Font FONT = new Font("Arial", Font.PLAIN, 20);

    ArrayList<hexagon> hexagons = new ArrayList<hexagon>();

    EndGame end_game_button = new EndGame();

    //CONSTRUCTOR
    public gameBoard(){

        /*When a new object of type gameBoard it's created, the hexagons are automatically initialised. Just to make clear, when I talk about "coordinates" I'm talking about their position on the Panel, I'm not talking
        about the coordinates used in the main class.
        */

        numbers = new ArrayList<>();

        initialiseHexagonsCoordinates(hexagons);

        // Add mouse click listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // case for if final guess button is clicked
                if (end_game_button.isClicked(e.getX(), e.getY())) {
                    // set as -2 (in GameLogic class this will trigger the final guess)
                    GameLogic.current_edge_num = -2;
                    repaint();
                    return;
                }
                // Check if the click occurred within the bounds of any number
                for (gameBoard.NumberInfo numberInfo : numbers) {
                    if (numberInfo.isClicked(e.getX(), e.getY())) {
                        // update static variable to the valid edge number
                        GameLogic.current_edge_num = numberInfo.getNumber();
                        repaint(); // Repaint to update the display
                        return; // Exit loop if a number is clicked
                    }
                }
            }
        });

    }

    private void initialiseHexagonsCoordinates(ArrayList<hexagon> list)
    {

        int counter_edges = 0;
        //Here we have to calculate the coordinates of the hexagons.

        /*I will need x, y to represent the 2 coordinates and I will also need 2 nested loops for rows and columns.*/
        int x = 400, y = 60;
        int n = 5, index_edges = 1; //Starts at 5 ends at 9
        int index_buttons = 0;
        int index_left = 1, index_right = 46, index_top = 54, index_bottom = 19;


        for(int i = 0; i < 9; i++)
        {

            // these are for valid coordinates of hexagons for the data in our board
            int row = i;
            int col = -4;

            for(int j = 0; j < n; j++)
            {
                // making sure right coordinates printed on hexagons
                while (!Utility.inRange(row, col)) {
                    col++;
                }

                if(j == 0)
                {
                    if(i < 4) {
                        numbers.add(new NumberInfo(x - 35, y - 60, index_left++));
                        numbers.add(new NumberInfo(x - 60, y - 20, index_left++));
                    }
                    else if (i > 4){
                        numbers.add(new NumberInfo(x - 90, y - 45, index_left++));
                        numbers.add(new NumberInfo(x - 70, y - 15, index_left++));
                    } else {    //If it's the middle row

                        numbers.add(new NumberInfo(x - 35, y - 60, index_left++));
                        numbers.add(new NumberInfo(x - 70, y - 15, index_left++));

                    }


                } else if ( j == n - 1) //In case these are the last hexagons of each row
            {
                if(i < 4)
                {
                    numbers.add(new NumberInfo(x + 25, y - 60, index_right--));
                    numbers.add(new NumberInfo(x + 45, y - 15, index_right--));

                } else if ( i > 4 )
                {
                    numbers.add(new NumberInfo(x + 65, y - 45, index_right--));
                    numbers.add(new NumberInfo(x + 45, y - 15, index_right--));
                } else { //If it's the middle row

                    numbers.add(new NumberInfo(x + 15, y - 60 , index_right--));
                    numbers.add(new NumberInfo(x + 45, y - 15, index_right--));

                }
            }

            if(i == 0 && j != 0) //top row
                {
                    numbers.add(new NumberInfo(x - 80, y - 60, index_top--));
                    numbers.add(new NumberInfo(x - 40, y - 60,index_top--));
                }

            if(i == 8)
            {
                numbers.add(new NumberInfo(x - 45, y + 30, index_bottom++));
                numbers.add(new NumberInfo(x + 22, y + 30, index_bottom++));
            }
                hexagons.add(new hexagon(x, y, row, col));
                x += 100;

                col++; // increment current column
            }

            if(i < 4) {
                x = x - (100 * n) - 50;
                n++;
            }

            else{
                x = x - (100 * n) + 50;
                n--;
            }

            y += 80;

        }
        //Here we have the exact number of hexagons in the array list.
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int index = 0;
        super.paintComponent(g);

        //Here I'm going through the whole list of hexagons and display them.

        for(int i = 0; i < hexagons.size(); i++)
        {
            drawSingleHexagon(g, hexagons.get(i).first, hexagons.get(i).second, hexagons.get(i).row, hexagons.get(i).col);
        }

        for(NumberInfo numberInfo : numbers){
            g.setColor(Color.black);
            g.setFont(FONT);
            numberInfo.draw(g);
        }

        // draws end game button (when clicked will end game)
        end_game_button.draw(g);

    }


    /*This function uses 6 points to draw the shape of a hexagon which is not included in any of the Java Swing libraries.
       The function has been implemented using information from the website https://profile.w3schools.com/log-in?redirect_url=https%3A%2F%2Fwww.w3schools.com%2Fjava%2Fdefault.asp
       */

    public void drawSingleHexagon(Graphics g, int x, int y, int row, int col)
    {
        //This is the length of the side of a hexagon.
        int sideLength = 50; // Adjust this value as needed

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        // Calculate the points of the hexagon6
        for (int i = 0; i < 6; i++) {
            double angleRadians = Math.toRadians(60 * i + 90); // 60 degrees for each point
            xPoints[i] = (int) (x + sideLength * Math.cos(angleRadians));
            yPoints[i] = (int) (y + sideLength * Math.sin(angleRadians));
        }

        // Draw the black hexagon
        g.setColor(Color.black); // Set fill color to black
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(Color.white);

        // draw coordinates onto atoms
        String coords = Integer.toString(row) + ", " + Integer.toString(col);
        g.drawString(coords, x + (sideLength / 10), y + (sideLength / 10));

    }
}


