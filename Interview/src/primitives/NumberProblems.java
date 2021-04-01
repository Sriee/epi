package primitives;

import java.util.*;

public class NumberProblems {

    /**
     * Leet code. Solution -> Accepted Run Time: 0 ms. Optimal solution The Hamming
     * distance between two integers is the number of positions at which the
     * corresponding bits are different. Given two integers x and y, calculate the
     * Hamming distance. Ex: 1 - 0 0 0 1 4 - 0 1 0 0 | | Dist 2
     * 
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int count = 0;
        while (x != 0 || y != 0) {
            count = count + ((x & 1) ^ (y & 1));
            x >>>= 1;
            y >>>= 1;
        }
        return count;
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 1 ms. Almost Optimal solution Given
     * an unsigned integer, compute the number of 1's in the number. This is known
     * as Hamming Weight
     * 
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        long i = 1;

        for (int j = 0; j < 32; j++) {
            if ((n & i) != 0)
                count++;
            i <<= 1;
        }

        /**
         * while(n != 0) { count = count + (n & 1); // This will lead to an infinite
         * loop. While right shifting // Java appends '1' to the left most bit. n >> =
         * 1; // Instead use right shift operator which fills '0' to its // left most
         * bit n >>> = 1; }
         */
        return count;
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 0ms. Optimal solution. Given a row
     * number generate pascal triangle upto that row
     * 
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<>();

        if (numRows == 0)
            return result;

        List<Integer> previous = new LinkedList<>(), current = null;
        previous.add(1);
        result.add(previous);

        for (int i = 1; i < numRows; i++) {
            current = new LinkedList<>();
            current.add(1);

            for (int j = 0; j + 1 < previous.size(); j++)
                current.add(previous.get(j) + previous.get(j + 1));

            current.add(1);

            result.add(current);
            previous = current;
        }

        return result;
    }

    public static void main(String[] args) {
        NumberProblems num = new NumberProblems();
        num.hammingWeight(-3);
    }

}
