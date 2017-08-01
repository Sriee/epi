package util.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class CharacterGenerator implements Iterable<Character>, Iterator<Character>, Generator {

    private int size = 0;
    private int index = 0;
    private Random rand = null;
    private Case letterCase = Case.LOWER_CASE;
    private final char[] LOWER_CASE_ALPHABETS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] UPPER_CASE_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] MIXED_CASE_ALPHABETS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public CharacterGenerator() { }

    public CharacterGenerator(Case letterCase) { this.letterCase = letterCase; }

    @Override
    public char[] generate(int size) {

        if (size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        char result[] = new char[size];
        this.rand = new Random();
        for (int i = 0; i < size; i++) {
            result[i] = this.getChar();
        }

        return result;
    }

    @Override
    public List<Character> generateAsList(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Size should not be <= 0");

        List<Character> resultList = new ArrayList<Character>();
        this.rand = new Random();
        for (int i = 0; i < size; i++) {
            resultList.add(this.getChar());
        }
        return resultList;
    }

    private char getChar(){
        char ch = ' ';

        if(this.letterCase == Case.LOWER_CASE){
            ch = LOWER_CASE_ALPHABETS[rand.nextInt(26)];
        } else if (this.letterCase == Case.UPPER_CASE) {
            ch = UPPER_CASE_ALPHABETS[rand.nextInt(26)];
        } else {
            ch = MIXED_CASE_ALPHABETS[rand.nextInt(52)];
        }

        return ch;
    }

    public Iterator<Character> iterator(int size) {

        // Initializing Iterator variables
        this.size = size;
        this.index = 0;
        this.rand = new Random();
        return this;
    }

    @Override
    public Iterator<Character> iterator() { return this; }

    @Override
    public boolean hasNext() { return this.index < this.size; }

    @Override
    public Character next() {
        this.index++;
        return this.getChar();
    }

    @Override
    public void remove() { throw new UnsupportedOperationException("Cannot Remove values while iterating."); }

    @Override
    public String toString() { return "CharacterGenerator(size= " + this.size + ")"; }

}
