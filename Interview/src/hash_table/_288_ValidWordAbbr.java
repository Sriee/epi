package hash_table;

import java.util.*;

class _288_ValidWordAbbr {

    // This problem has lot of down votes. The description wasn't clear as well. Skip this.
    Map<String, Set<String>> map;

    public _288_ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            String ab = abbreviate(s);
            if (!map.containsKey(ab)) {
                map.put(ab, new HashSet<>());
            }
            map.get(ab).add(s);
        }
    }

    public boolean isUnique(String word) {
        String ab = abbreviate(word);

        if (!map.containsKey(ab)) {
            map.put(ab, new HashSet<>());
            map.get(ab).add(word);
        } else {
            return map.get(ab).contains(word);
        }

        return true;
    }

    private String abbreviate(String s) {
        int len = s.length();
        if (len < 3)
            return s;

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0)).append(len - 2).append(s.charAt(len - 1));
        return sb.toString();
    }
}