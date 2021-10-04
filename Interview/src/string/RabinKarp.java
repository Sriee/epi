package string;

/**
 * Rabin Karp String searching algorithm. Reference: Introduction to Algorithms
 * CLRS - Chapter 32 Section 2
 * https://algs4.cs.princeton.edu/53substring/RabinKarp.java.html
 *
 */
public class RabinKarp {

    /**
     * Searches for pattern in the given text This method is similar to Java's indexOf() method. Note that it could
     * easily extended to return index of all occurrences of pattern within string
     *
     * TC: O(n * (n - m + 1)) where n >>> m: O(nm) <-- Worst case time complexity.
     *      This is because of the check function. Matching strings guarantees matching hashes. However, the reverse
     *      is not always true: matching hashes does not guarantee matching strings. For this reason, we use the
     *      check function to iterate 'm' times. Test case 3 & 4 are good examples.
     *
     * SC: O(1)
     *
     * @param text
     * @param pattern
     * @return index of first found pattern in text
     */
    public int search(String text, String pattern) {
        // Corner Cases
        if (pattern.length() == 0)
            return 0;

        if (text.length() < pattern.length())
            return -1;

        int n = text.length(), m = pattern.length(), base = 256, modulus = (1 << 31) - 1;
        long windowHash = 0L, patternHash = 0L, pow = 1L;

        for (int i = 0; i < m; i++) {
            windowHash = (windowHash * base + text.charAt(i)) % modulus;
            patternHash = (patternHash * base + pattern.charAt(i)) % modulus;
            if (i != m - 1)
                pow = (pow * base) % modulus;
        }

        if (windowHash == patternHash && check(text, pattern, m, 0))
            return 0;

        for (int i = 1; i < n - m + 1; i++) {
            // Remove left most character
            windowHash = (windowHash + modulus - text.charAt(i - 1) * pow % modulus) % modulus;

            // Add right most character
            windowHash = (windowHash * base + text.charAt(i + m - 1)) % modulus;

            if (windowHash == patternHash && check(text, pattern, m, i))
                return i;
        }

        return -1;
    }

    /**
     * Check if str1 == str2
     * 
     * @param pattern
     * @param text
     * @param offset  starting point of comparison of text
     * @return true if str1 == str2 false otherwise
     */
    private boolean check(String text, String pattern, int m, int offset) {
        for (int i = 0; i < m; i++) {
            if (pattern.charAt(i) != text.charAt(offset + i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        RabinKarp rk = new RabinKarp();

        System.out.println(rk.search("hello", "ll"));
        System.out.println(rk.search("aaaaaaaaaaaa", "aaa"));
        System.out.println(rk.search("aaaaaaaaaaaad", "aaad"));
        System.out.println(rk.search("onionionspl", "onions"));
        System.out.println(rk.search("atcaagttaccaata", "cca"));
        System.out.println(rk.search("2359023141526739921", "31415"));
        System.out.println(rk.search("235902673992131415", "31415"));
    }

}
