package leet;

import java.util.Map;
import java.util.LinkedHashMap;

public class FirstUnique {

    private static int checkTreeMap(String inp) {
        int idx = -1;
        if (inp == null)
            return idx;

        inp = inp.trim();

        if (inp.isEmpty())
            return idx;

        Map<Character, Integer> tm = new LinkedHashMap<>();
        for (int i = 0; i < inp.length(); i++) {
            char temp = inp.charAt(i);
            if (tm.containsKey(temp)) {
                tm.put(temp, tm.get(temp) + 1);
            } else {
                tm.put(temp, 1);
            }
        }

        for (Character ch : tm.keySet()) {
            if (tm.get(ch) > 1)
                continue;

            idx = inp.indexOf(ch);
            break;
        }

        return idx;
    }

    public static void main(String[] args) {
        System.out.println(checkTreeMap("leetcode"));
        System.out.println(checkTreeMap("   "));
        System.out.println(checkTreeMap("aaaaa"));
        System.out.println(checkTreeMap("lahcdl"));
        System.out.println(checkTreeMap(" hasdgfahs"));
        System.out.println(checkTreeMap(null));
    }
}
