package heaps;

import java.util.*;

public class _767_ReorganizeString {
    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue()));
        pq.addAll(map.entrySet());
        Map.Entry<Character, Integer> previous = null;
        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty() || previous != null) {
            if (previous != null && pq.isEmpty())
                return "";

            Map.Entry<Character, Integer> current = pq.poll();
            result.append(current.getKey());

            if (previous != null) {
                pq.offer(previous);
                previous = null;
            }

            int count = current.getValue() - 1;
            if (count != 0) {
                previous =  new AbstractMap.SimpleEntry<Character, Integer>(current.getKey(), count);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        _767_ReorganizeString rs = new _767_ReorganizeString();
        String[] inputs = {
                "programming",
                "hello",
                "fofjjb",
                "abbacdde",
                "aba",
                "awesome",
                "aaab",
                "aab"
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput string: \"" + inputs[i] + "\"");
            System.out.println("\tReorganized string: \"" + rs.reorganizeString(inputs[i]) + "\"");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
