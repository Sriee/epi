package util;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Range implements Iterable<Integer>, Iterator<Integer> {

    private int index = 0;
    private int current = Integer.MIN_VALUE;
    private static int start = -1;
    private static int stop = -1;
    private static int step = -1;

    public Range() {
    }

    private static List<Integer> generateRange() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = start; i < stop; i += step) {
            list.add(i);
        }
        return list;
    }

    public static List<Integer> range(int stop) {
        Range.start = 0;
        Range.stop = stop;
        Range.step = 1;
        return generateRange();
    }

    public static List<Integer> range(int start, int stop) {
        Range.start = start;
        Range.stop = stop;
        Range.step = 1;
        return generateRange();
    }

    public static List<Integer> range(int start, int stop, int step) {
        if (step < 1)
            throw new IllegalArgumentException("Step shoule be >= 1");

        Range.start = start;
        Range.stop = stop;
        Range.step = step;

        return generateRange();
    }

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return this.index < Range.stop;
    }

    @Override
    public Integer next() {
        if (this.current == Integer.MIN_VALUE) {
            this.current = Range.start;
        } else {
            this.current += Range.step;
        }

        this.index++;
        return this.current;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot Remove values while iterating.");
    }
}
