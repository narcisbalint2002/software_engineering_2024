import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


public class gameBoard extends JPanel {


    ArrayList<hexagon> hexagons = new ArrayList<hexagon>();

    //CONSTRUCTOR
    public gameBoard(){

        /*When a new object of type gameBoard it's created, the hexagons are automatically initialised. Just to make clear, when I talk about "coordinates" I'm talking about their position on the Panel, I'm not talking
        about the coordinates used in the main class.
        */
        initialiseHexagonsCoordinates(hexagons);

    }

    private void initialiseHexagonsCoordinates(ArrayList<hexagon> list)
    {
        //Here we have to calculate the coordinates of the hexagons.

        /*I will need x, y to represent the 2 coordinates and I will also need 2 nested loops for rows and columns.*/

        //I will firstly print the first half of the game board
        int x = 400, y = 60;
        int n = 5; //Starts at 5 ends at 9

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < n; j++)
            {
                hexagons.add(new hexagon(x, y));
                x += 80;
            }

            x =  x - (80 * n) - 40 ;
            y += 65;
            n++;
        }

        n -= 2;

        //Here I'm printing the second half

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < n; j++)
            {
                x += 80;
                hexagons.add(new hexagon(x, y));
            }

            x = x - (80 * n) + 40;
            y += 65;
            n--;
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //Here I'm going through the whole list of hexagons and display them.

        for(int i = 0; i < hexagons.size(); i++)
        {
            drawSingleHexagon(g, hexagons.get(i).first, hexagons.get(i).second);
        }
    }


    /*This function uses 6 points to draw the shape of a hexagon which is not included in any of the Java Swing libraries.
       The function has been implemented using information from the website https://profile.w3schools.com/log-in?redirect_url=https%3A%2F%2Fwww.w3schools.com%2Fjava%2Fdefault.asp
       */

    public void drawSingleHexagon(Graphics g, int x, int y)
    {
        //This is the length of the side of a hexagon.
        int sideLength = 40; // Adjust this value as needed

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        // Calculate the points of the hexagon
        for (int i = 0; i < 6; i++) {
            double angleRadians = Math.toRadians(60 * i + 90); // 60 degrees for each point
            xPoints[i] = (int) (x + sideLength * Math.cos(angleRadians));
            yPoints[i] = (int) (y + sideLength * Math.sin(angleRadians));
        }

        // Draw the black hexagon
        g.setColor(Color.black); // Set fill color to black
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(Color.white);
        g.drawString("1, 2", x -170, y - 260);

    }

}


