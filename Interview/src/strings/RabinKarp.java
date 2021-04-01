package strings;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin Karp String searching algorithm. Reference: Introduction to Algorithms
 * CLRS - Chapter 32 Section 2
 * https://algs4.cs.princeton.edu/53substring/RabinKarp.java.html
 * 
 * @author sriee
 */
public class RabinKarp {

    private long modulus; // prime
    private int radix; // base 10 for decimals, 26 for lowercase alphabets

    public RabinKarp() {
        this.modulus = this.randomLongPrime();
        this.radix = 256;
    }

    public RabinKarp(long prime, int radix) {
        this.modulus = prime;
        this.radix = radix;
    }

    /**
     * Searches for pattern in the given text This method is similar to Java's
     * indexOf() method. Note that it could easily extended to return index of all
     * occurrences of pattern within string
     * 
     * @param text
     * @param pattern
     * @return index of first found pattern in text
     */
    public int search(String text, String pattern) {
        // Corner Cases
        if (text.length() == 0 || text.length() < pattern.length())
            return 0;

        int m = pattern.length();
        int n = text.length();
        long patHash = this.hash(pattern, m);
        long txtHash = this.hash(text, m);
        long rm = 1; // 256 * m

        // Precompute rm which is used for calculating rolling hash in O(1)
        for (int i = 1; i < m; i++)
            rm = (rm * radix) % modulus;

        // Check at offset: 0
        if (txtHash == patHash && this.check(pattern, text, 0))
            return 0;

        for (int i = 1; i < n - m + 1; i++) {
            // Remove trailing digit.
            txtHash = (txtHash + modulus - text.charAt(i - 1) * rm % modulus) % modulus;

            // Adding trailing digit
            txtHash = (txtHash * radix + text.charAt(i + m - 1)) % modulus;

            // If hash matches check if text[s+1..s+m] == pattern[1..m]
            if (txtHash == patHash && this.check(pattern, text, i))
                return i;
        }
        return -1;
    }

    /**
     * Compute hash of the string of length m
     * 
     * @param key
     * @param m
     * @return hash for string: key
     */
    private long hash(String key, int m) {
        long h = 0L;

        // We are taking modulus at each step to avoid overflow
        for (int i = 0; i < m; i++)
            h = (h * radix + key.charAt(i)) % modulus;
        return h;
    }

    /**
     * Generate long prime for performing modulus
     * 
     * @return prime value
     */
    private long randomLongPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /**
     * Check if str1 == str2
     * 
     * @param pattern
     * @param text
     * @param offset  starting point of comparison of text
     * @return true if str1 == str2 false otherwise
     */
    private boolean check(String pattern, String text, int offset) {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != text.charAt(offset + i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        RabinKarp rk = new RabinKarp();

        System.out.println(rk.search("hello", "ll"));
        System.out.println(rk.search("aaaaaaaaaaaa", "aaa"));
        System.out.println(rk.search("atcaagttaccaata", "cca"));
        System.out.println(rk.search("2359023141526739921", "31415"));
        System.out.println(rk.search("235902673992131415", "31415"));
    }

}
