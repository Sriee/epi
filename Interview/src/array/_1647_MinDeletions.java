package array;

import java.util.*;

/**
 * Asked in Microsoft Interview - 2021
 */
public class _1647_MinDeletions {

    public int minDeletions(String s) {
        int minDel = 0;

        if(s == null || s.length() == 0)
            return minDel;

        int[] freq = new int[26];
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < s.length(); i++) freq[s.charAt(i) - 'a']++;

        for(int i = 0; i < 26; i++) {
            if(freq[i] == 0)
                continue;

            while(set.contains(freq[i])) {
                freq[i]--;

                if(freq[i] < 0) break;
                minDel++;
            }
            set.add(freq[i]);
        }

        return minDel;
    }

    public static void main(String[] args) {
        _1647_MinDeletions md = new _1647_MinDeletions();
        String[] s = {"z", "abc", "accdcdadddbaadbc", "aab", "ceabaacb"};
        int[] expected = {0, 2, 1, 0, 5};

        // To enable assertions in a java, add "-ea" flag to javac compiler
        for(int i = 0; i < s.length; i++)
            assert md.minDeletions(s[i]) == expected[i]: String.format("Assertion check failed for i = %d", i);
    }
}
