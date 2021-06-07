package hash_table;

import java.util.*;

class _249_GroupShifts {

    /**
     * Design Custom Key Technique
     * <p>
     * We know that we need to use this technique to group elements together based on a criteria. (Remember the idea of
     * grouping all anagrams?). Here the criteria to design a key is the difference between each character.
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            String key = computeKey(s);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }

        List<List<String>> groups = new ArrayList<>();
        for (String k : map.keySet())
            groups.add(map.get(k));

        return groups;
    }

    /**
     * In this problem the order of the elements in the string is important. We form a key based on differences
     * between them.
     * <p>
     * Consider acf and pru. Now notice the difference between each characters.
     * acf = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case, you can simply use integers
     * ASCII difference to form key.
     * <p>
     * Now consider corner case, az and ba, where az = 0->25 and ba = 0->-1. When it goes negative, just make it
     * positive (rotate or mod equivalent) by adding 26. So it becomes, ba = 0->25, which forms same group.
     */
    private String computeKey(String s) {
        if (s.length() == 1)
            return "0";

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int diff = s.charAt(i) - s.charAt(i - 1);
            sb.append(diff < 0 ? diff + 26 : diff).append(",");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        _249_GroupShifts gs = new _249_GroupShifts();
        String[] input = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println(gs.groupStrings(input));
    }
}