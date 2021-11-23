package string;

import java.util.*;

public class _1773_CountMatches {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        char ch = ruleKey.charAt(0);
        int matches = 0;

        int idx = ch == 't' ? 0 : ch == 'c' ? 1 : 2;

        for (List<String> item : items) {
            if (item.get(idx).equals(ruleValue))
                matches++;
        }
        return matches;
    }

    public static void main(String[] args) {
        _1773_CountMatches cm = new _1773_CountMatches();
        List<List<String>> lst = new ArrayList<>();

        lst.add(Arrays.asList("phone", "blue", "pixel"));
        lst.add(Arrays.asList("computer", "silver", "lenovo"));
        lst.add(Arrays.asList("phone", "gold", "iphone"));

        System.out.println(cm.countMatches(lst, "color", "silver"));
    }
}
