
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class gameBoard extends JPanel{

    public static int dynamicScale(int value) {
        return (int) ((double) value * Main.scale);
    }



//    // scale user defines (can be changed via button)
//    public double scale = 1.0;
//
//
//    // ability to change scale using setter/getter methods
//    public double getScale() {
//        return scale;
//    }
//    public void setScale(double new_scale) {
//        scale = new_scale;
//    }

    public static class ScreenScale {
        private int x = dynamicScale(100);
        private int y = dynamicScale(100);


        private static int BUTTON_SIZE_Y = dynamicScale(75);

        private static int BUTTON_SIZE_X = dynamicScale(BUTTON_SIZE_Y * 2);

        public boolean isClicked(int clickX, int clickY) {
            int dynamic_clickX = dynamicScale(clickX);
            int dynamic_clickY = dynamicScale(clickY);
            // return true based on if the valid edge button coordinates are clicked
            return dynamic_clickX >= x && dynamic_clickX <= x + BUTTON_SIZE_X && dynamic_clickY >= y && dynamic_clickY <= y + BUTTON_SIZE_Y;
        }

        public void draw(Graphics g) {
            // Draw the black hexagon
            g.setColor(Color.black); // Set fill color to black
            g.fillRoundRect(x, y, BUTTON_SIZE_X, BUTTON_SIZE_Y, dynamicScale(10), dynamicScale(10));
            g.setColor(Color.white);
            g.drawString("SCALE", x + (BUTTON_SIZE_Y / 2), y + BUTTON_SIZE_Y / 2);
        }
    }

    public static class EndGame {
        private int x = dynamicScale(1000);
        private int y = dynamicScale(100);


        private static int BUTTON_SIZE_Y = dynamicScale(75);

        private static int BUTTON_SIZE_X = dynamicScale(BUTTON_SIZE_Y * 3);

        public boolean isClicked(int clickX, int clickY) {
            int dynamic_clickX = dynamicScale(clickX);
            int dynamic_clickY = dynamicScale(clickY);
            // return true based on if the valid edge button coordinates are clicked
            return dynamic_clickX >= x && dynamic_clickX <= x + BUTTON_SIZE_X && dynamic_clickY >= y && dynamic_clickY <= y + BUTTON_SIZE_Y;
        }

        public void draw(Graphics g) {
            // Draw the black hexagon
            g.setColor(Color.black); // Set fill color to black
            g.fillRoundRect(x, y, BUTTON_SIZE_X, BUTTON_SIZE_Y, dynamicScale(10), dynamicScale(10));
            g.setColor(Color.white);
            g.drawString("GUESS ATOMS", x + (BUTTON_SIZE_Y / 2), y + (BUTTON_SIZE_Y / 2));
        }
    }

    public static class NumberInfo {
        private int x;
        private int y;
        private int number;
        public int number_size = dynamicScale(20);

        /* set color to black by default indicating not clicked yet, these are ALL the states:
         *          !! IMPORTANT !! -- some of these MAY CHANGE due to visibility on white background
         *      black   -   default (clickable)
         *      blue    -   enters and exits different edges
         *      red     -   reflect back to same edge
         *      green   -   absorbed
         */
        private Color color = Color.BLACK;

        private Font font = FONT;




        public NumberInfo(int x, int y, int number) {
            this.x = dynamicScale(x);
            this.y = dynamicScale(y);
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
        public Color getColor() {
            return color;
        }
        public Font getFont() {
            return font;
        }
        public void setNumber(int new_num) {
            number = new_num;
        }
        public void setColor(Color new_color) {
            color = new_color;
        }
        public void setFont(Font new_font) {
            font = new_font;
        }

        public boolean isClicked(int clickX, int clickY) {
            int dynamic_clickX = dynamicScale(clickX);
            int dynamic_clickY = dynamicScale(clickY);
            // return true based on if the valid edge button coordinates are clicked
            return dynamic_clickX >= x && dynamic_clickX <= x + number_size && dynamic_clickY >= y && dynamic_clickY <= y + number_size;
        }

        public void draw(Graphics g) {
            g.drawString(Integer.toString(number), x, y + number_size);
        }
    }


    public static List<NumberInfo> numbers;
    private static final Font FONT = new Font("Arial", Font.PLAIN, dynamicScale(20));

    ArrayList<hexagon> hexagons = new ArrayList<hexagon>();


    ScreenScale screen_scale_button = new ScreenScale();
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
                    GameLogic.ongoing_input = -2;
                    repaint();
                    return;
                } else if (screen_scale_button.isClicked(e.getX(), e.getY())) {
                    // set as -3 (in GameLogic class this will trigger the final guess)
                    GameLogic.ongoing_input = -3;
                    repaint();
                    return;
                }
                // Check if the click occurred within the bounds of any number
                for (gameBoard.NumberInfo numberInfo : numbers) {
                    if (numberInfo.isClicked(e.getX(), e.getY())) {
                        // update static variable to the valid edge number
                        GameLogic.ongoing_input = numberInfo.getNumber();

                        // should all probably be done in GameLogic class
                        /* update edge number colour accordingly indicating ray state (absorbed, reflected, etc.)
                         *      AND
                         * making it unclickable by making its size 0 (if its 0 pixels wide cannot be clicked)
                         *
                         * ONLY changes number so NOT clickable
                         */
//                        numberInfo.number_size = 0;


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
        int x = dynamicScale(400), y = dynamicScale(60);
        int n = 5, index_edges = 1; //Starts at 5 ends at 9
        int index_buttons = 0;
        int index_left = dynamicScale(1), index_right = dynamicScale(46), index_top = dynamicScale(54), index_bottom = dynamicScale(19);


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
                        numbers.add(new NumberInfo(x - dynamicScale(35), y - dynamicScale(60), index_left++));
                        numbers.add(new NumberInfo(x - dynamicScale(60), y - dynamicScale(20), index_left++));
                    }
                    else if (i > 4){
                        numbers.add(new NumberInfo(x - dynamicScale(90), y - dynamicScale(45), index_left++));
                        numbers.add(new NumberInfo(x - dynamicScale(70), y - dynamicScale(15), index_left++));
                    } else {    //If it's the middle row

                        numbers.add(new NumberInfo(x - dynamicScale(35), y - dynamicScale(60), index_left++));
                        numbers.add(new NumberInfo(x - dynamicScale(70), y - dynamicScale(15), index_left++));

                    }


                } else if ( j == n - 1) //In case these are the last hexagons of each row
            {
                if(i < 4)
                {
                    numbers.add(new NumberInfo(x + dynamicScale(25), y - dynamicScale(60), index_right--));
                    numbers.add(new NumberInfo(x + dynamicScale(45), y - dynamicScale(15), index_right--));

                } else if ( i > 4 )
                {
                    numbers.add(new NumberInfo(x + dynamicScale(65), y - dynamicScale(45), index_right--));
                    numbers.add(new NumberInfo(x + dynamicScale(45), y - dynamicScale(15), index_right--));
                } else { //If it's the middle row

                    numbers.add(new NumberInfo(x + dynamicScale(15), y - dynamicScale(60) , index_right--));
                    numbers.add(new NumberInfo(x + dynamicScale(45), y - dynamicScale(15), index_right--));

                }
            }

            if(i == 0 && j != 0) //top row
                {
                    numbers.add(new NumberInfo(x - dynamicScale(80), y - dynamicScale(60), index_top--));
                    numbers.add(new NumberInfo(x - dynamicScale(40), y - dynamicScale(60), index_top--));
                }

            if(i == 8)
            {
                numbers.add(new NumberInfo(x - dynamicScale(45), y + dynamicScale(30), index_bottom++));
                numbers.add(new NumberInfo(x + dynamicScale(22), y + dynamicScale(30), index_bottom++));
            }
                hexagons.add(new hexagon(x, y, row, col));
                x += dynamicScale(100);

                col++; // increment current column
            }

            if(i < 4) {
                x = x - (dynamicScale(100) * n) - dynamicScale(50);
                n++;
            }

            else{
                x = x - (dynamicScale(100) * n) + dynamicScale(50);
                n--;
            }

            y += dynamicScale(80);

        }
        //Here we have the exact number of hexagons in the array list.
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int index = 0;
        super.paintComponent(g);



        // typecasts Graphics to Graphics2D, this is done so we can adjust the board according to user GUI scale
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(Main.width/2, Main.height/2);
        g2.scale(Main.scale, Main.scale);
        g2.translate(-(Main.width)/2, -(Main.height)/2);







        //Here I'm going through the whole list of hexagons and display them.

        for(int i = 0; i < hexagons.size(); i++)
        {
            drawSingleHexagon(g, hexagons.get(i).first, hexagons.get(i).second, hexagons.get(i).row, hexagons.get(i).col);
        }

        for(NumberInfo numberInfo : numbers){
            g.setColor(numberInfo.color);
            g.setFont(numberInfo.font);
            numberInfo.draw(g);
        }

        g.setColor(Color.black);

        // draws scale button (when clicked will cycle scale)
        screen_scale_button.draw(g);

        // draws end game button (when clicked will end game)
        end_game_button.draw(g);

    }


    /*This function uses 6 points to draw the shape of a hexagon which is not included in any of the Java Swing libraries.
       The function has been implemented using information from the website https://profile.w3schools.com/log-in?redirect_url=https%3A%2F%2Fwww.w3schools.com%2Fjava%2Fdefault.asp
       */

    public void drawSingleHexagon(Graphics g, int x, int y, int row, int col)
    {
        //This is the length of the side of a hexagon.
        int sideLength = dynamicScale(50); // Adjust this value as needed

        int dynamic_x = dynamicScale(x);
        int dynamic_y = dynamicScale(y);

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        // Calculate the points of the hexagon6
        for (int i = 0; i < 6; i++) {
            double angleRadians = Math.toRadians(60 * i + 90); // 60 degrees for each point
            xPoints[i] = (int) (dynamic_x + sideLength * Math.cos(angleRadians));
            yPoints[i] = (int) (dynamic_y + sideLength * Math.sin(angleRadians));
        }

        // Draw the black hexagon
        g.setColor(Color.black); // Set fill color to black
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(Color.white);

        // draw coordinates onto atoms
        String coords = Integer.toString(row) + ", " + Integer.toString(col);
        g.drawString(coords, dynamic_x + (sideLength / 10), dynamic_y + (sideLength / 10));

    }
}


