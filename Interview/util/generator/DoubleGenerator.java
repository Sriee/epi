package generator.number;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DoubleGenerator implements Iterable<Double>, Iterator<Double>, Generator {

    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;
    private int places = 4;
    private int size = 0;
    private int index = 0;
    private double scale = -1;
    private Random rand = null;

    public DoubleGenerator(){
        this.min = 0;
        this.max = Integer.MAX_VALUE;
    }

    public DoubleGenerator(int min, int max){
        this.min = min;
        this.max = max;
    }

    public DoubleGenerator(int min, int max, int places){
        this.min = min;
        this.max = max;

        if(places <= 0)
            throw new IllegalArgumentException("Places should be > 0.");
        this.places = places;
    }

    @Override
    public double[] generate(int size){

        if(size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        double result[] = new double[size];
        double scale = Math.pow(10, this.places);
        Random rand = new Random();

        for(int i = 0; i < size; i++){
            result[i] = Math.round((rand.nextDouble() * ((this.max + 1 - this.min) + this.min)) * scale) / scale;
        }

        return result;
    }

    @Override
    public List<Double> generateAsList(int size){
        if(size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        List<Double> resultList = new ArrayList<Double>();
        double scale = Math.pow(10, this.places);
        Random rand = new Random();

        for(int i = 0; i < size; i++){
            resultList.add(Math.round((rand.nextDouble() * ((this.max + 1 - this.min) + this.min)) * scale) / scale);
        }
        return resultList;
    }

    public Iterator<Double> iterator(int size){

        // Initializing Iterator variables
        this.size = size;
        this.index = 0;
        this.scale = Math.pow(10, this.places);
        this.rand = new Random();
        return this;
    }

    @Override
    public Iterator<Double> iterator() { return this; }

    @Override
    public boolean hasNext() { return this.index < this.size; }

    @Override
    public Double next() {
        this.index++;
        return Math.round((this.rand.nextDouble() * ((this.max + 1 - this.min) + this.min)) * this.scale) / this.scale;
    }

    @Override
    public void remove() { }

    @Override
    public String toString() { return "DoubleGenerator(min= " + this.min + ", max= " + this.max +")"; }

}
