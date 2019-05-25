package java8;

public class Tuple {

    public int first;

    public  int second;

    public Tuple(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}
