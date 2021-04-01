package util;

public class Axis {

    private int x, y;

    public Axis() {
        this(0, 0);
    }

    public Axis(int a, int b) {
        this.x = a;
        this.y = b;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
