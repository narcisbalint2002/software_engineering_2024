import java.util.ArrayList;
import java.util.List;

public class Atom {
    private int x, y;
    private List<List<Integer>> circleOfInfluence;

    public Atom(int x, int y) {
        this.x = x;
        this.y = y;
        this.circleOfInfluence = new ArrayList<>();

        this.addToCircleOfInfluence(x, y+1);
        this.addToCircleOfInfluence(x, y-1);
        this.addToCircleOfInfluence(x-1, y);
        this.addToCircleOfInfluence(x-1, y+1);
        this.addToCircleOfInfluence(x+1, y);
        this.addToCircleOfInfluence(x+1, y-1);
    }

    public static void main(String[] args) { //TESTING PURPOSES

        Atom atom = new Atom(7, -3);

        System.out.println(atom.toString());
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<List<Integer>> getCircleOfInfluence() {
        return circleOfInfluence;
    }

    public void addToCircleOfInfluence(int x, int y) {
        List<Integer> coordinate = new ArrayList<>();
        coordinate.add(x);
        coordinate.add(y);
        circleOfInfluence.add(coordinate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atom at ").append("(").append(x).append(", ").append(y).append(")").append("\n");
        sb.append("Circle of Influence: [");
        for (int i = 0; i < circleOfInfluence.size(); i++) {
            List<Integer> coordinate = circleOfInfluence.get(i);
            sb.append("(").append(coordinate.get(0)).append(", ").append(coordinate.get(1)).append(")");
            if (i < circleOfInfluence.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
