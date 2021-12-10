package top_sort;

import java.util.*;

public class _269_AlienDictionary {

    /**
     * Refer the editorial for time complexity analysis.
     *
     * Let U be number of unique characters in the dictionary
     *     C be the count of all characters in the dictionary
     *     N be the number of words in the dictionary
     *
     * Time Complexity
     * ===============
     *  1. Forming the graph = O(C)
     *  2. Khans Algorithm - BFS of graph = O(V + E) = O(U + min(U^2, N))
     *  TC = O(C + U + min(U^2, N))
     *
     * Space Complexity
     * ================
     * SC: O(U + min(U^2, N))
     */
    public String alienOrder(String[] words) {
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, Set<Character>> graph = new HashMap<>();
        Deque<Character> queue = new ArrayDeque<>();

        // Initialize Graph
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (inDegree.containsKey(ch))
                    continue;

                inDegree.put(ch, 0);
                graph.put(ch, new HashSet<>());
            }
        }

        // Build graph and in-degree map
        for (int i = 1; i < words.length; i++) {
            if (words[i - 1].length() > words[i].length() && words[i - 1].startsWith(words[i])) {
                return "";
            }

            int len = Math.min(words[i - 1].length(), words[i].length());
            for (int j = 0; j < len; j++) {
                char first = words[i - 1].charAt(j);
                char second = words[i].charAt(j);

                if (first == second)
                    continue;

                if(!graph.get(first).contains(second)){
                    inDegree.put(second, inDegree.get(second) + 1);
                    graph.get(first).add(second);
                }
                break;
            }
        }

        // Khan's Algorithm
        for (Character key : inDegree.keySet()) {
            // System.out.println(key + ": " + inDegree.get(key));
            if (inDegree.get(key) == 0)
                queue.offer(key);
        }

        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            Character node = queue.poll();
            res.append(node);

            for (Character neighbor : graph.get(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // System.out.println(res);
        return res.length() == inDegree.size() ? res.toString() : "";
    }

    public static void main(String[] args) {
        _269_AlienDictionary ad = new _269_AlienDictionary();

        String[][] input = new String[][]{
                {"mzosr", "mqov", "xxsvq", "xazv", "xazau", "xaqu", "suvzu", "suvxq", "suam", "suax", "rom", "rwx", "rwv"},
                {"vanilla", "alpine", "algor", "port", "norm", "nylon", "ophellia", "hidden"},
                {"passengers", "to", "the", "unknown"},
                {"ac", "ab", "zc", "zb"},
                {"wr", "wr", "wr"}
        };

        for (int i = 0; i < input.length; i++) {
            System.out.println("\n" + i + 1 + ".\twords = " + Arrays.toString(input[i]));
            System.out.println("\tDictionary = \"" + ad.alienOrder(input[i]) + "\"");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}