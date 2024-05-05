import MATH.Coordinate;

public class Atom extends Coordinate {


    public Atom(int x, int y) {
        super(x, y);

        if(!Utility.inRange(x, y)){
            throw new IllegalArgumentException("Arguments out of range - Atoms Constructor");
        }
    }

    public static void main(String[] args) { //TESTING PURPOSES

        Atom atom = new Atom(7, -3);

        System.out.println(atom.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atom at ").append("(").append(this.getX()).append(", ").append(this.getY()).append(")").append("\n");
        return sb.toString();
    }

}
