package generator.number;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class IntegerGenerator implements Iterable<Integer>, Iterator<Integer>, Generator {

    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;
    private int size = 0;
    private int index = 0;
    private Random rand = null;

    public IntegerGenerator() {
        this.min = 0;
        this.max = Integer.MAX_VALUE;
    }

    public IntegerGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int[] generate(int size) {

        if (size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        int result[] = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt((this.max + 1 - this.min) + this.min);
        }

        return result;
    }

    @Override
    public List<Integer> generateAsList(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        List<Integer> resultList = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            resultList.add(rand.nextInt((this.max - this.min) + 1) + this.min);
        }
        return resultList;
    }

    public Iterator<Integer> iterator(int size) {

        // Initializing Iterator variables
        this.size = size;
        this.index = 0;
        this.rand = new Random();
        return this;
    }

    @Override
    public Iterator<Integer> iterator() { return this; }

    @Override
    public boolean hasNext() { return this.index < this.size; }

    @Override
    public Integer next() {
        this.index++;
        return this.rand.nextInt((this.max + 1 - this.min) + this.min);
    }

    @Override
    public void remove() { }

    @Override
    public String toString() { return "IntegerGenerator(min= " + this.min + ", max= " + this.max + ")"; }

}
