package string;

public class _28_StrStr {

    /**
     * Naive Solution:
     *
     * For test cases 3 and 4, the time complexity degrades to O(m * (n - m + 1)). This solution is not the optimal
     * and should not be used.
     */
    public int strStr(String text, String pattern) {
        if (pattern.length() == 0)
            return 0;

        if (text.length() < pattern.length())
            return -1;

        int n = text.length(), m = pattern.length();

        for (int i = 0; i < n - m + 1; i++) {
            if (text.substring(i, i + m).equals(pattern))
                return i;
        }

        return -1;
    }

    /**
     * Rabin Karp algorithm implementation. This is concise and should be used to solve leetcode problems.
     *
     * TC: Best Case O(m + n).
     *     Worst Case: O(m * (n -m + 1)) This is because of the check function. Matching strings guarantees matching
     *     hashes. However, the reverse is not always true: matching hashes does not guarantee matching strings.
     *     For this reason, we use the check function to iterate 'm' times. Test case 3 & 4 are good examples.
     * SC: O(1)
     */
    private int strStrRK(String text, String pattern) {
        if (text.length() < pattern.length())
            return -1;

        int base = 128, modulus = (1 << 31) - 1, n = text.length(), m = pattern.length();
        long pow = 1L, patternHash = 0L, windowHash = 0L;

        // Pre-compute pattern hash, window hash, and highest power
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * base + pattern.charAt(i)) % modulus;
            windowHash = (windowHash * base + text.charAt(i)) % modulus;

            if (i != m - 1)
                pow = (pow * base) % modulus;
        }

        if (windowHash == patternHash && check(text, pattern, m, 0))
            return 0;

        for (int i = 1; i < n - m + 1; i++) {
            // Remove left most character
            windowHash = (windowHash + modulus - text.charAt(i - 1) * pow % modulus) % modulus;

            // Adding character at i + m - 1
            windowHash = (windowHash * base + text.charAt(i + m - 1)) % modulus;
            if (windowHash == patternHash && check(text, pattern, m, i))
                return i;
        }

        return -1;
    }

    private boolean check(String text, String pat, int m, int offset) {
        for (int i = 0; i < m; i++) {
            if (text.charAt(i + offset) != pat.charAt(i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        _28_StrStr st = new _28_StrStr();
        String[] texts = {"sabbutsad", "leetcode", "AAAAAAAAAAAAA", "AAAABAAAAB"};
        String[] patterns = {"sad", "leeto", "AAAA", "AAAB"};

        for (int i = 0; i < texts.length; i++) {
            System.out.println(st.strStrRK(texts[i], patterns[i]));
        }
    }
}
