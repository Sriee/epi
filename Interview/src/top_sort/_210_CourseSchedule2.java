package top_sort;

import java.util.*;

public class _210_CourseSchedule2 {

    /**
     * TC: O(V + E)
     * SC: O(V + E)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] inDegree = new int[numCourses];

        // Using List<List<T>> is faster than using Map<T, List<T>>
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] preq : prerequisites) {
            inDegree[preq[0]]++;
            graph.get(preq[1]).add(preq[0]);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res[idx++] = node;

            for (int neighbor : graph.get(node)) {
                if (--inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return idx == numCourses ? res : new int[]{};
    }

    public static void main(String[] args) {
        _210_CourseSchedule2 cs = new _210_CourseSchedule2();

        int[] n = {4, 5, 2, 4, 7};
        int[][][] prerequisites = {
                {{1, 0}, {2, 0}, {3, 1}, {3, 2}},
                {{1, 0}, {2, 0}, {3, 1}, {4, 3}},
                {{1, 0}}, {{1, 0}, {2, 0}, {3, 1}, {3, 2}},
                {{1, 0}, {0, 3}, {0, 2}, {3, 2}, {2, 5}, {4, 5}, {5, 6}, {2, 4}}};

        for (int i = 0; i < n.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tPrerequisites: " + Arrays.deepToString(prerequisites[i]) + "\n\tTotal number of courses, n = " + n[i]);
            int[] result = cs.findOrder(n[i], prerequisites[i]);
            System.out.println("\tValid courses order: " + Arrays.toString(result));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
