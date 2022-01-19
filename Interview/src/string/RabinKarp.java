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
     * easily be extended to return index of all occurrences of pattern within string
     *
     * TC: O(n * (n - m + 1)) where n >>> m: O(nm) <-- Worst case time complexity.
     *      This is because of the check function. Matching strings guarantees matching hashes. However, the reverse
     *      is not always true: matching hashes does not guarantee matching strings. For this reason, we use the
     *      check function to iterate 'm' times. Test case 3 & 4 are good examples.
     *
     * SC: O(1)
     *
     * @param text The string to search
     * @param pattern The search pattern
     * @return index of first found pattern in text
     */
    public int search(String text, String pattern) {
        // Corner Cases
        if (text == null || pattern == null || text.length() < pattern.length())
            return -1;

        int patternLen = pattern.length(), textLen = text.length(), modulus = 13, base = 256;
        if (patternLen == 0)
            return 0;

        long windowHash = 0L, patternHash = 0L, pow = 1L;

        // Calculate hash value for pattern and text
        for (int i = 0; i < patternLen; i++) {
            patternHash = (base * patternHash + pattern.charAt(i)) % modulus;
            windowHash = (base * windowHash + text.charAt(i)) % modulus;
            if (i != patternLen - 1)
                pow = (pow * base) % modulus;
        }

        // Find the match
        for (int i = 0; i <= textLen - patternLen; i++) {
            if (patternHash == windowHash && check(text, pattern, i) == patternLen)
                return i;

            /*
             * Why are we using a modulus?
             * When the hash value of the pattern matches with the hash value of a window of the text but the window is
             * not the actual pattern then it is called a spurious hit.
             *
             * Spurious hit increases the time complexity of the algorithm. In order to minimize spurious hit,
             * we use modulus. It greatly reduces the spurious hit.
             */
            if (i < textLen - patternLen) {
                windowHash = (base * (windowHash - text.charAt(i) * pow) + text.charAt(i + patternLen))
                        % modulus;
                if (windowHash < 0)
                    windowHash += modulus;
            }
        }

        return -1;
    }

    private int check(String text, String pattern, int offset) {
        int i;
        for (i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != text.charAt(offset + i))
                break;
        }
        return i;
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
