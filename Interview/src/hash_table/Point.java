package hash_table;

/**
 * Example hashable class
 */
public class Point {
    public int x, y;

    public Point() {}
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.x) * 13 + Integer.hashCode(this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Point))
            return false;

        Point other = (Point)o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "Point(" + this.x + ", " + this.y + ")";
    }
}
