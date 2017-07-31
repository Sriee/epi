package generator.number;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class BooleanGenerator implements Iterable<Boolean>, Iterator<Boolean>, Generator {

    private int size = 0;
    private int index = 0;
    private Random rand = null;

    public BooleanGenerator(){ }

    public BooleanGenerator(int size){
        this.size = size;
    }

    @Override
    public boolean[] generate(int size){

        if(size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        boolean result[] = new boolean[size];
        Random rand = new Random();

        for(int i = 0; i < size; i++){
            result[i] = rand.nextInt(2) == 1;
        }

        return result;
    }

    @Override
    public List<Boolean> generateAsList(int size){
        if(size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        List<Boolean> resultList = new ArrayList<Boolean>();
        Random rand = new Random();

        for(int i = 0; i < size; i++){
            resultList.add(rand.nextInt(2) == 1);
        }
        return resultList;
    }

    public Iterator<Boolean> iterator(int size){

        // Initializing Iterator variables
        this.size = size;
        this.index = 0;
        this.rand = new Random();
        return this;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.size;
    }

    @Override
    public Boolean next() {
        this.index++;
        return this.rand.nextInt(2) == 1;
    }

    @Override
    public void remove() { }

    @Override
    public String toString() {
        return "BooleanGenerator(size= " + this.size + ")";
    }

}
