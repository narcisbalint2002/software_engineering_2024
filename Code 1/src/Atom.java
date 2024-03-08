import java.util.ArrayList;
import java.util.List;

public class Atom extends Coordinate {

    private List<Coordinate> circleOfInfluence;

    public Atom(int x, int y) {
        super(x, y);

        //Need to add checks here for the coordinate ranges and throw exceptions.
        if(!Utility.inRange(x, y)){
            throw new IllegalArgumentException("Arguments out of range - Atoms Contstructor");
        }

        this.circleOfInfluence = new ArrayList<>();
        addToCircleOfInfluence(new Coordinate(x, y + 1));
        addToCircleOfInfluence(new Coordinate(x, y - 1));
        addToCircleOfInfluence(new Coordinate(x - 1, y));
        addToCircleOfInfluence(new Coordinate(x - 1, y + 1));
        addToCircleOfInfluence(new Coordinate(x + 1, y));
        addToCircleOfInfluence(new Coordinate(x + 1, y - 1));
    }

    public static void main(String[] args) { //TESTING PURPOSES

        Atom atom = new Atom(7, -3);

        System.out.println(atom.toString());
    }


    public List<Coordinate> getCircleOfInfluence() {
        return circleOfInfluence;
    }

    public void addToCircleOfInfluence(Coordinate c) {

        if(Utility.inRange(c.getX(), c.getY())){
            //throw new IllegalArgumentException("Arguments out of range - Atoms addCOI");

            /*Since atoms near edge will lead to COI out of range.
              i need to still be able to continue programme.
              for now i will add -1000, -1000 to indicate c.o.i out of board.
             */
            Coordinate temp = new Coordinate(c.getX(), c.getY());
            circleOfInfluence.add(temp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atom at ").append("(").append(this.getX()).append(", ").append(this.getY()).append(")").append("\n");
        sb.append("Circle of Influence: [");
        for (int i = 0; i < circleOfInfluence.size(); i++) {
            Coordinate coordinate = circleOfInfluence.get(i);
            sb.append("(").append(coordinate.getX()).append(", ").append(coordinate.getY()).append(")");
            if (i < circleOfInfluence.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
