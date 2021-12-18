package hash_table;

import util.PrintHypens;

public class _409_LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] table = new int[128];
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (table[ch] != 0) {
                count += 2;
                table[ch]--;
            } else {
                table[ch]++;
            }
        }

        for (int i = 0; i < 128; i++) {
            if (table[i] == 1) {
                count++;
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        _409_LongestPalindrome lp = new _409_LongestPalindrome();

        String[] input = {
                "abbjhhrrssS",
                "abbbhhejjssktaoop",
                "aagshsgh"
        };

        for (int i = 0; i < input.length; i++) {
            System.out.print("\n" + (i + 1) + ".\tInput = " + input[i]);
            System.out.println("\n\tLength of longest palindrome = " + lp.longestPalindrome(input[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
