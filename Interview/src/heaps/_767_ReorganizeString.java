package heaps;

import java.util.*;

public class _767_ReorganizeString {
    public String reorganizeString(String s) {
        int[] table = new int[26];
        for (char ch : s.toCharArray()) {
            table[ch - 'a']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(table[b], table[a]));
        for (int i = 0; i < 26; i++) {
            if (table[i] > 0) pq.offer(i);
        }

        int previous = -1;
        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty() || previous != -1) {
            if (previous != -1 && pq.isEmpty())
                return "";

            int current = pq.poll();
            result.append((char)(current + 'a'));

            if (previous != -1) {
                pq.offer(previous);
                previous = -1;
            }

            if (--table[current] != 0) {
                previous =  current;
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
