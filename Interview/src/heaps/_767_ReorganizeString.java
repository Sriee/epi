package heaps;

import java.util.*;

public class _767_ReorganizeString {

    /**
     * Let n be the number of characters in the string
     * and c be the number of distinct characters in the string
     *
     * TC -
     * O(n) - To populate the frequency table
     *      - Worst case, we might be pushing the distinct character and each push takes (log c) time
     * Total - O(n log c). But the characters will be lowercase letter = 26 so log c could be considered a constant
     * so TC - O(n)
     *
     * SC - O(c)
     */
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
