package hash_table;

import java.util.*;

public class _49_GroupAnagrams {

    /*
     * Design custom Key Technique.
     *
     * Improved run time by 1 ms. Removing additional List<List<String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            String key = computeKey(s);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }

        List<List<String>> groups = new ArrayList<>();
        for (String k : map.keySet())
            groups.add(map.get(k));

        return groups;
    }

    /*
     * Since the String contains all lower case English letters we create count for each letter from a-z
     *
     * TC: O(n)
     * SC: O(1)
     */
    private String computeKey(String s) {
        StringBuilder sb = new StringBuilder();
        int[] table = new int[26];
        for (char ch : s.toCharArray()) {
            table[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            sb.append(table[i]).append(",");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        _49_GroupAnagrams ga = new _49_GroupAnagrams();
        String[] st = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(ga.groupAnagrams(st));
    }
}
