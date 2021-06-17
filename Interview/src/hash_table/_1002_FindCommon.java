package hash_table;

import java.util.*;

public class _1002_FindCommon {
    public List<String> commonChars(String[] words) {
        int[] first = new int[26], second = new int[26];
        populate(words[0], first);

        for (String w : words) {
            populate(w, second);
            first = intersect(first, second);
            Arrays.fill(second, 0);
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] > 0) {
                String s = String.valueOf((char) (i + 'a'));
                for (int j = 0; j < first[i]; j++)
                    res.add(s);
            }
        }

        return res;
    }

    private void populate(String s, int[] table) {
        for (char ch : s.toCharArray())
            table[ch - 'a']++;
    }

    private int[] intersect(int[] first, int[] second) {
        int[] common = new int[26];

        for (int i = 0; i < 26; i++) {
            if (first[i] > 0 && second[i] > 0)
                common[i] = Math.min(first[i], second[i]);
        }

        return common;
    }

    public static void main(String[] args) {
        _1002_FindCommon fc = new _1002_FindCommon();
        String[][] inputs = {
                {"bella", "label", "roller"},
                {"cool", "lock", "cook"},
                {"adg", "fj", "dfdf", "lghoi"}
        };

        for (String[] words : inputs)
            System.out.println(fc.commonChars(words));
    }
}
