package string;

/**
 * Implementation of KMP String Matching Algorithm
 *
 * TC: O(n + m) <-- Worst case time complexity.
 *      O(m) - Time needed to compute the
 * SC: O(m)
 *  where m = length of the pattern
 *        n = length of the text
 */
public class KMP {

    private int strStr(String text, String pattern) {
        if (pattern.length() == 0)
            return 0;

        if (text.length() < pattern.length())
            return -1;

        int n = text.length(), m = pattern.length(), i =0, j = 0;
        int[] lps = new int[m];

        computeLps(pattern, lps, m);

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++; j++;
            } else if (j != 0){
                j = lps[j - 1];
            } else {
                i++;
            }

            if (j == m) return i - m;
        }
        return -1;
    }

    private void computeLps(String pattern, int[] lps, int m) {
        int prevLps = 0, i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(prevLps)) {
                prevLps++;
                lps[i] = prevLps;
                i++;
            } else if (prevLps != 0) {
                prevLps = lps[prevLps - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();

        System.out.println(kmp.strStr("hello", "ll"));
        System.out.println(kmp.strStr("aaaaaaaaaaaa", "aaa"));
        System.out.println(kmp.strStr("atcaagttaccaata", "cca"));
        System.out.println(kmp.strStr("aaaxaaaa", "aaaa"));
    }
}
