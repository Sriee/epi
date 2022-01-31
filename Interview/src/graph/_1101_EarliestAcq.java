package graph;

import util.PrintHypens;

import java.util.Arrays;
import java.util.Comparator;

public class _1101_EarliestAcq {
    int[] root, rank;

    public int earliestAcq(int[][] logs, int n) {
        int count = n;
        root = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        Arrays.sort(logs, Comparator.comparingInt(log -> log[0]));
        for (int[] log : logs) {
            if (union(log[1], log[2])) {
                count--;
            }

            if (count == 1)
                return log[0];
        }

        return -1;
    }

    private boolean union(int x, int y) {
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

            return true;
        }

        return false;
    }

    private int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    public static void main(String[] args) {
        _1101_EarliestAcq eacq = new _1101_EarliestAcq();

        int[][][] inputs = {
                {{20190101, 0, 1}, {20190104, 3, 4}, {20190107, 2, 3}, {20190211, 1, 5}, {20190224, 2, 4}, {20190301, 0, 3}, {20190312, 1, 2}, {20190322, 4, 5}},
                {{0, 2, 0}, {1, 0, 1}, {3, 0, 3}, {4, 1, 2}, {7, 3, 1}},
                {{9, 3, 0}, {0, 2, 1}, {8, 0, 1}, {1, 3, 2}, {2, 2, 0}, {3, 3, 1}}
        };
        int[] ns = new int[]{6, 4, 4};

        for (int i = 0; i < inputs.length; i++) {
            System.out.printf("\n%d.\tEdges = %s\n", (i + 1), Arrays.deepToString(inputs[i]));
            System.out.println("\tEarliest acquainted time stamp = " + eacq.earliestAcq(inputs[i], ns[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
