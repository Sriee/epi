package company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StringChains {

    private int longestChain(String[] words) {
        if (words == null || words.length == 0)
            return 0;
        int chainLength = 0;

        Arrays.sort(words, new Comparator<String>() {
            public int compare(String first, String second) {
                return first.length() - second.length();
            }
        });

        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (wordMap.containsKey(words[i]))
                continue;
            wordMap.put(words[i], 1);
            for (int j = 0; j < words[i].length(); j++) {
                String temp = words[i].substring(0, j) + words[i].substring(j + 1);
                if (wordMap.containsKey(temp) && wordMap.get(temp) + 1 > wordMap.get(words[i])) {
                    wordMap.put(words[i], wordMap.get(temp) + 1);
                }
            }

            if (wordMap.get(words[i]) > chainLength)
                chainLength = wordMap.get(words[i]);
        }

        return chainLength;
    }

    public static void main(String[] args) {
        StringChains sc = new StringChains();
        System.out.println(sc.longestChain(new String[] { "a", "b", "ba", "bca", "bda", "bdca" }));
        System.out.println(sc.longestChain(new String[] {}));
        System.out.println(sc.longestChain(null));
        System.out.println(sc.longestChain(new String[] { "bc", "abc" }));
    }

}
