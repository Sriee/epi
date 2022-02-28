package graph;

import util.PrintHypens;

import java.util.*;

public class _1494_ParallelCourse2 {

    /*
     * Topological sort approach will not work
     */
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] relation : relations) {
            inDegree[relation[1]]++;
            graph.get(relation[0]).add(relation[1]);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int sem = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > k)
                size = k;

            sem++;
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                for (int neighbor : graph.get(node)) {
                    if (--inDegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return sem;
    }

    public static void main(String[] args) {
        _1494_ParallelCourse2 pc2 = new _1494_ParallelCourse2();
        int[] ns = {12, 8, 4, 5};
        int[] ks = {3, 3, 2, 2};
        int[][][] prerequisites = {
                {{11, 10}, {6, 3}, {2, 5}, {9, 2}, {4, 12}, {8, 7}, {9, 5}, {6, 2}, {7, 2}, {7, 4}, {9, 3}, {11, 1}, {4, 3}},
                {{2, 7}, {1, 6}, {2, 8}, {8, 7}, {6, 7}, {5, 4}, {1, 7}, {1, 2}, {1, 4}, {2, 6}},
                {{2, 1}, {3, 1}, {1, 4}},
                {{2,1},{3,1},{4,1},{1,5}}
        };

        for (int i = 0; i < ns.length; i++) {
            System.out.printf("%d.\tn=%d, k=%d, prerequisites=%s\n", (i + 1), ns[i], ks[i], Arrays.deepToString(prerequisites[i]));
            System.out.println("\tMinimum semesters to take all courses= " + pc2.minNumberOfSemesters(ns[i], prerequisites[i], ks[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
