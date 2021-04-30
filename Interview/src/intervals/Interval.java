package intervals;

/**
 * For Merge Intervals 2 representation of Intervals are used.
 * 1. This class
 * 2. 2D Array intervals[n][2] where
 * start = intervals[i][0]
 * end = intervals[i][1]
 */
public class Interval implements Comparable<Interval> {
    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", this.start, this.end);
    }

    @Override
    public int compareTo(Interval o) {
        return this.start == o.start ? this.end - o.end : this.start - o.start;
    }
}
