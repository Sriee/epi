package primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PrimeGen {

    /**
     * The method for generating primes are based on sieve of eratosthene
     * 
     * @param n
     * @return list of primes
     */
    public String generate(long n) {
        int len = -1, size = new Double(Math.floor(0.5 * (n - 3)) + 1).intValue();
        List<Integer> primes = new ArrayList<>();
        int value;

        if (n < 2)
            return null;

        if (n == 2)
            return "2";

        // Initialize boolean array
        boolean[] isPrime = new boolean[size];
        Arrays.fill(isPrime, true);

        primes.add(2);
        for (long i = 0; i < size; i++) {
            if (isPrime[(int) i]) {
                value = (int) ((i << 1) + 3); // 2i + 3
                primes.add(value);

                /**
                 * We need to know the index of square of the number 'num' Example - For 3, 9 is
                 * at index 3, For 5, 25 is at index 11 We need to know i for p^2 p = (2i + 3)
                 * p^2 = 4i^2 + 12i + 9 i to p mapping is i = (p - 3) / 2 Substitute p^2 in the
                 * place of p in the above equation i = (p^2 - 3) / 2 i = (4i^2 + 12i + 9 - 3) /
                 * 2 i = (2i^2 + 6i + 3)
                 */
                for (long p = ((i * i) << 1) + 6 * i + 3; p < size; p += value) {
                    isPrime[(int) p] = false;
                }
            }
        }
        len = primes.toString().length();
        return primes.toString().substring(1, len - 1);
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 10 ms. Above average run time A
     * simpler version of using seive of eratosthene. Could use this for generating
     * primes as well.
     * 
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;

        if (n <= 3)
            return 1;

        int count = 0;
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;

        for (int i = 2; i < Math.sqrt(n); i++) {
            if (!notPrime[i]) {
                for (int j = 2; j * i < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (!notPrime[i])
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        PrimeGen pg = new PrimeGen();
        int a = pg.countPrimes(15);
        System.out.println(a);
    }
}
