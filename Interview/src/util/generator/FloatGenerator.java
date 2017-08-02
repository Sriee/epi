package util.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FloatGenerator implements Iterable<Float>, Iterator<Float>, Generator<Float> {

    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;
    private int places = 4;
    private int size = 0;
    private int index = 0;
    private double scale = -1;
    private Random rand = null;

    public FloatGenerator(){
        this.min = 0;
        this.max = Integer.MAX_VALUE;
    }

    public FloatGenerator(int min, int max){
        this.min = min;
        this.max = max;
    }

    public FloatGenerator(int min, int max, int places){
        this.min = min;
        this.max = max;

        if(places <= 0)
            throw new IllegalArgumentException("Places should be > 0.");
        this.places = places;
    }

    public float[] generate(int size){
        if(size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        float result[] = new float[size];
        double scale = Math.pow(10, this.places);
        Random rand = new Random();

        for(int i = 0; i < size; i++){
            double value = Math.round((rand.nextFloat() * ((this.max + 1 - this.min) + this.min)) * scale) / scale;
            result[i] = (float) value;
        }

        return result;
    }

    @Override
    public List<Float> generateAsList(int size){
        if(size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        List<Float> resultList = new ArrayList<Float>();
        double scale = Math.pow(10, this.places);
        Random rand = new Random();

        for(int i = 0; i < size; i++){
            double value = Math.round((rand.nextFloat() * ((this.max + 1 - this.min) + this.min)) * scale) / scale;
            resultList.add((float) value);
        }
        return resultList;
    }

    public Iterator<Float> iterator(int size){

        // Initializing Iterator variables
        this.size = size;
        this.index = 0;
        this.scale = Math.pow(10, this.places);
        this.rand = new Random();
        return this;
    }

    @Override
    public Iterator<Float> iterator() { return this; }

    @Override
    public boolean hasNext() { return this.index < this.size; }

    @Override
    public Float next() {
        this.index++;
        return (float) (Math.round((this.rand.nextDouble() * ((this.max + 1 - this.min) + this.min)) * this.scale)
                / this.scale);
    }

    @Override
    public void remove() { throw new UnsupportedOperationException("Cannot Remove values while iterating."); }

    @Override
    public String toString() { return "FloatGenerator(min= " + this.min + ", max= " + this.max +")"; }

}
