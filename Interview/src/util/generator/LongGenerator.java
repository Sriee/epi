package util.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LongGenerator implements Iterable<Long>, Iterator<Long>, Generator<Long> {

    private long min = Long.MIN_VALUE;
    private long max = Long.MAX_VALUE;
    private long size = 0;
    private long index = 0;

    public LongGenerator() {
        this.min = 0;
        this.max = Integer.MAX_VALUE;
    }

    public LongGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public long[] generate(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");
        long result[] = new long[size];
        for (long i = 0; i < size; i++) {
            result[(int)i] = ThreadLocalRandom.current().nextLong(this.min, this.max);
        }
        return result;
    }

    @Override
    public List<Long> generateAsList(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");
        List<Long> resultList = new ArrayList<Long>();
        for (int i = 0; i < size; i++) {
            resultList.add(ThreadLocalRandom.current().nextLong(this.min, this.max));
        }
        return resultList;
    }

    public Iterator<Long> iterator(int size) {
        // Initializing Iterator variables
        this.size = size;
        this.index = 0;
        return this;
    }

    @Override
    public Iterator<Long> iterator() { return this; }

    @Override
    public boolean hasNext() { return this.index < this.size; }

    @Override
    public Long next() {
        this.index++;
        return ThreadLocalRandom.current().nextLong(this.min, this.max);
    }

    @Override
    public void remove() { throw new UnsupportedOperationException("Cannot Remove values while iterating."); }

    @Override
    public String toString() { return "IntegerGenerator(min= " + this.min + ", max= " + this.max + ")"; }

}
