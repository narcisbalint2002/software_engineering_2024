import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


public class gameBoard extends JPanel {

    static class hexagon{
        protected  int first;
        protected  int second;

        public hexagon(int first, int second)
        {
            this.first = first;
            this.second = second;
        }
    }

    ArrayList<hexagon> hexagons = new ArrayList<hexagon>();

    //CONSTRUCTOR
    public gameBoard(){

        //I decided to create the list of coordinate in the constructor because we don't have
        //any use for it in the main function
        //List with hexagons coordinates

        initialiseHexagonsCoordinates(hexagons);
    }

    private void initialiseHexagonsCoordinates(ArrayList<hexagon> list)
    {
        //Here we have to calculate the coordinates of the hexagons.

        /*The gameboard has the following mount of hexagons on each row
         * 5
         * 6
         * 7
         * 8
         * 9
         * 8
         * 7
         * 6
         * 5*/

        /*I will need x, y to reppresent the 2 coordinates and I will also need 2 nested loop for rows and columns.*/

        //I will firstly print the first half of the gameboard
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

        for(int i = 0; i < hexagons.size(); i++)
        {
            drawSingleHexagon(g, hexagons.get(i).first, hexagons.get(i).second);
        }
    }

    public void drawSingleHexagon(Graphics g, int x, int y)
    {
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
        g.setColor(Color.BLACK); // Set fill color to black
        g.fillPolygon(xPoints, yPoints, 6);

    }

}


