package company;

import java.util.*;

public class Microsoft {

    /**
     * Calculate digit sum Example: 42 -> 6
     * 
     * @param digit
     * @return digit sum
     */
    public int digitSum(int digit) {
        int ans = 0;
        digit = Math.abs(digit);
        while (digit != 0) {
            ans += digit % 10;
            digit /= 10;
        }

        return ans;
    }

    /**
     * Given an array containing integers, find two integers a, b such that sum of
     * digits of a and b is equal. Return maximum sum of a and b. Return -1 if no
     * such numbers exist. Example: A: [51, 71, 17, 42, 33, 44, 24, 62] { 6 => 24,
     * 33, 42, 51, 8 => 17, 44, 62, 71 } Maximum sum = 62 + 71 (whose digit sum is
     * 8)
     * 
     * @param A
     */
    public void equalDigitSum(int[] A) {
        Map<Integer, Integer> mem = new HashMap<>();
        int result = -1;

        for (int i : A) {
            int d = digitSum(i);

            if (mem.containsKey(d)) {
                result = Math.max(result, mem.get(d) + i);
                mem.put(d, Math.max(i, mem.get(d)));
            } else {
                mem.put(d, i);
            }

        }
        System.out.println(result);
    }

    /**
     * Given a string which contains only chars 'a' & 'b' find the number of moves
     * required to make the string valid. A string is said to be valid if it doesnot
     * have more than 2 contiguous chars The idea is to find 3 char which run
     * continuously. For every 3 continuous character we can change a single char.
     * Example: aabbaa -> Valid. Moves = 0 aaabb -> Invalid, Moves = 1 <- ababb
     * 
     * @param s Given string
     */
    public void icl(String s) {
        int moves = 0, i;

        for (i = 0; i < s.length(); i++) {
            int runner = 1;
            for (; i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1); i++)
                runner++;
            moves += runner / 3;
        }
        System.out.println(moves);
    }

    /**
     * Microsoft Onile Assessment (OA - Sep 2019) Given a string with multiple
     * repeating characters return a string which only has characters repeated at
     * most 2 times. Example: aaabbb -> aabb abcd -> abcd
     * 
     * @param S string with multiple repeated characters
     * @return string with at most 2 repeated individual characters
     */
    public String noTwoConsequitive(String S) {
        StringBuilder sb = new StringBuilder();

        if (S == null || S.length() == 0)
            return "";

        for (int i = 0; i < S.length(); i++) {
            int runner = 1;
            sb.append(S.charAt(i));
            for (; i + 1 < S.length() && S.charAt(i) == S.charAt(i + 1); i++) {
                runner++;

                if (runner < 3)
                    sb.append(S.charAt(i + 1));
            }
        }
        return sb.toString();
    }

    /**
     * Microsoft Onile Assessment (OA - Sep 2019) Given a string return
     * lexicographically smallest string by removing at most 1 character Example:
     * aabzc -> aabc; By removing 'z' 'aabc' will be lexicographically < 'aabzc'
     * 
     * @param S Given string
     * @return string which is lexicographically smaller
     */
    public String lexicographicallySmallest(String S) {
        StringBuilder sb = new StringBuilder();

        if (S == null || S.length() == 0)
            return "";

        for (int i = 0; i < S.length() - 1; i++) {
            if (S.charAt(i) > S.charAt(i + 1)) {
                for (int j = 0; j < S.length(); j++) {
                    if (i != j)
                        sb.append(S.charAt(j));
                }

                return sb.toString();
            }
        }

        // If we have reached here then the last character should be greater than its
        // previous
        // character so removing it
        return S.substring(0, S.length() - 2);
    }

    /**
     * Microsoft Onile Assessment (OA - Oct 2019) An infrastructure consisting of N
     * cities (1 to N) and M bi-directional roads between them is given. Roads won't
     * intersect. For each pair of cities directly connected by a road, let's define
     * their network rank as the total number of roads that are connected to either
     * of the two cities. In other words - Each city will have m ranks where "ranks"
     * are the number of roads it's cities are connected to. In this case find the
     * highest netwrk rank. Arrays A & B will be of the same length.
     * 
     * @param A
     * @param B
     * @param N
     * @return
     */
    public int networkRank(int[] A, int[] B, int N) {
        int rank = 0, m = A.length;

        // We maintain an array where edge[i] = gives us the number of cities connected
        // to i
        int[] edges = new int[N + 1];

        for (int i = 0; i < m; i++) {
            edges[A[i]]++;
            edges[B[i]]++;
        }

        for (int i = 0; i < m; i++)
            rank = Math.max(rank, edges[A[i]] + edges[B[i]] - 1);

        return rank;
    }

    /**
     * Microsoft Onile Assessment (OA - Sep 2019) Given a string with special
     * character '?' replace the special character with lower case english alphabets
     * provided that there are no contiguous character in the resultant string
     * Example: 'ab?c' -> 'abac' '??a?' -> 'zyax'
     * 
     * @param S Given String with '?' character
     * @return String with '?' replaced
     */
    public String replaceSpecialChar(String S) {
        if (S == null || S.length() == 0)
            return "";

        int[] table = new int[26];
        char[] res = S.toCharArray();

        for (int i = 0; i < res.length; i++) {
            if (res[i] != '?') {
                table[res[i] - 'a']++;

                if (i - 1 >= 0)
                    table[res[i - 1] - 'a']--;
            } else {
                if (i + 1 < res.length && res[i + 1] != '?')
                    table[res[i + 1] - 'a']++;

                for (int j = 0; j < 26; j++) {
                    if (table[j] == 0) {
                        res[i] = (char) (j + 97);
                        table[j]++;
                        break;
                    }
                }

                if (i + 1 < res.length && res[i + 1] != '?')
                    table[res[i + 1] - 'a']--;
            }
        }

        return new String(res);
    }

    /**
     * Microsoft Onile Assessment (OA - Oct 2019) Given a string s containing only a
     * and b, find longest substring of s such that s does not contain more than two
     * contiguous occurrences of a and b. Example: "aabbaaaaabb" -> "aabbaa"
     * 
     * @param s input string
     * @return longest substring containing
     */
    public String validLongestSubstring(String s) {
        if (s.length() < 3)
            return s;

        String result = "";
        int i = 1, count = 1, start = 0;

        for (i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1))
                count++;
            else
                count = 1;

            if (count > 2) {
                if (i - start > result.length())
                    result = s.substring(start, i);

                start = i - 1;
                count = 1;
            }
        }
        return (result.length() > i - start) ? result : s.substring(start, i);
    }

    /**
     * Microsoft Onile Assessment (OA - Dec 2019) Given a string s consisting of n
     * lowercase letters, you have to delete the minimum number of characters from s
     * so that every letter in s appears a unique number of times. We only care
     * about the occurrences of letters that appear at least once in result.
     * Example: example -> 4. Frequency table => {'a': 1, 'e' : 2, 'm': 1, 'p': 1,
     * 'l':1, 'x': 1} We can have characters with frequencies 1 & 2 other characters
     * with equal frequencies have to be eliminated.
     * 
     * @param s input string
     * @return number of deletions required to make the string to have letters with
     *         unique frequencies
     */
    public int uniqueFrequency(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int[] frequency = new int[26];
        int deletions = 0;
        for (char ch : s.toCharArray())
            frequency[ch - 'a']++;

        Arrays.sort(frequency);

        for (int i = 24; i >= 0; i--) {

            // Why can't we check for only equals as we have already sorted the array in
            // ascending order ?
            // Because we are assigning ith value, trace for input "aabbffddeaee"
            if (frequency[i] > 0 && frequency[i] >= frequency[i + 1]) {
                int tmp = Math.max(0, frequency[i + 1] - 1);
                deletions += frequency[i] - tmp;
                frequency[i] = tmp;
            }
        }

        return deletions;
    }

    public static void main(String[] args) {
        Microsoft ms = new Microsoft();
        /*
         * ms.equalDigitSum(new int[] {51, 71, 17, 42, 33, 44, 24, 62}); // 133
         * ms.equalDigitSum(new int[] {51, 71, 17, 42}); // 93 ms.equalDigitSum(new
         * int[] {51, 32, 43}); // -1 System.out.println(); ms.icl("baaaaa"); // 1
         * ms.icl("baaaaaa"); // 2 ms.icl("baaaab"); // 1 ms.icl("baaabbaabbba"); //2
         * ms.icl("baabab"); //0 ms.icl("bbaabbaabbabab"); //0 System.out.println();
         * System.out.println(ms.noTwoConsequitive("aaabbbaacdddcccxyz")); //
         * aabbaacddccxyz System.out.println(ms.noTwoConsequitive("abccccccccccccc"));
         * // abcc System.out.println();
         * System.out.println(ms.lexicographicallySmallest("aabzc")); // aabc
         * System.out.println(ms.lexicographicallySmallest("acdefghi")); // acdefgh
         * System.out.println(); System.out.println(ms.replaceSpecialChar("ab?a"));
         * System.out.println(ms.replaceSpecialChar("ab??"));
         * System.out.println(ms.replaceSpecialChar("????"));
         * System.out.println(ms.networkRank(new int[] {1, 2, 3, 3}, new int[] {2, 3, 1,
         * 4}, 4)); System.out.println(ms.validLongestSubstring("aaabbbaacdddcccxyz"));
         * // bbaacdd System.out.println(ms.validLongestSubstring("abccccccccccccc"));
         * // abcc System.out.println(ms.validLongestSubstring("aabbaaaaabb")); //
         * aabbaa System.out.println(ms.validLongestSubstring("aabbaabbaabbaa")); //
         * aabbaabbaabbaa
         * System.out.println(ms.validLongestSubstring("abbaabbaaabbaaa")); // abbaabbaa
         * System.out.println();
         */

        System.out.println(ms.uniqueFrequency("eeeeffff")); // 1
        System.out.println(ms.uniqueFrequency("abcd")); // 3
        System.out.println(ms.uniqueFrequency("xxxx")); // 0
        System.out.println(ms.uniqueFrequency("example")); // 4
    }
}