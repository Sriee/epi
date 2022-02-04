package graph;

import util.PrintHypens;
import java.util.*;

public class _1202_SmallestSwapString {

    int[] root, rank;

    /**
     * E = edges
     * V = vertices
     *
     * TC: O((E + V) + V log V)
     *      O(E + V) - constructing union find
     *      O(V + log V) - Sorting the character list
     * SC: O(V + log V) = O(V)
     *      O(V) - rank, root, result, indexToRootMap
     *      O(log V) - Collections.sort() quick sort implementation space
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s.length() <= 1 || pairs == null || pairs.isEmpty())
            return s;

        int len = s.length();
        root = new int[len];
        rank = new int[len];

        for (int i = 0; i < len; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }

        Map<Integer, List<Integer>> indexToRootMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = find(i);

            indexToRootMap.putIfAbsent(root, new ArrayList<>());
            indexToRootMap.get(root).add(i);
        }

        char[] result = new char[len];
        for (List<Integer> list : indexToRootMap.values()) {
            List<Character> charList = new ArrayList<>();

            for (int vertex : list) {
                charList.add(s.charAt(vertex));
            }
            Collections.sort(charList);

            for (int i = 0; i < list.size(); i++) {
                result[list.get(i)] = charList.get(i);
            }
        }

        return String.valueOf(result);
    }

    private int find(int x) {
        if (x == root[x]) {
            return x;
        }

        return root[x] = find(root[x]);
    }

    private void union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) {
        _1202_SmallestSwapString ss = new _1202_SmallestSwapString();

        String[] inputs = { "dcab", "dcab", "cba", "a" };
        List<List<List<Integer>>> inputPairs = new ArrayList<>();

        inputPairs.add(List.of(Arrays.asList(0, 3), Arrays.asList(1, 2)));
        inputPairs.add(List.of(Arrays.asList(0, 3), Arrays.asList(1, 2), Arrays.asList(0, 2)));
        inputPairs.add(List.of(Arrays.asList(0, 1), Arrays.asList(1, 2)));
        inputPairs.add(List.of(Arrays.asList(0, 0)));

        for (int i = 0; i < inputs.length; i++) {
            System.out.printf("\n%d.\tString = \"%s\"\n", (i + 1), inputs[i]);
            System.out.printf("\tSmallest pair strings = \"%s\"\n", ss.smallestStringWithSwaps(inputs[i], inputPairs.get(i)));
            System.out.println(PrintHypens.generate());
        }
    }
}
