package top_sort;

import util.PrintHypens;

import java.util.*;

public class _1136_ParallelCourses {

    /* ===========================================================================================
     * Approach 1: Topological Sort - Khans Algorithm
     * ===========================================================================================
     */
    public int minimumSemesters(int n, int[][] relations) {
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

        int sem = 0, num = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            num += size;
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

        return num == n ? sem : -1;
    }

    public static void main(String[] args) {
        _1136_ParallelCourses pc = new _1136_ParallelCourses();
        int[] ns = {3, 3};
        int[][][] relations = {
                {{1, 3}, {2, 3}},
                {{1, 2}, {2, 3}, {3, 1}}
        };

        int res;
        for (int i = 0; i < ns.length; i++) {
            System.out.printf("%d.\tNumber of courses: %d, Relations: %s\n", (i + 1), ns[i], Arrays.deepToString(relations[i]));
            if (i == 0)
                res = pc.minimumSemesters(ns[i], relations[i]);
            else
                res = pc.minimumSemesters(ns[i], relations[i]);

            System.out.println("\tMinimum number of semesters needed to take all courses= " + res);
            System.out.println(PrintHypens.generate());
        }
    }
}
