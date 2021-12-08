package top_sort;

import java.util.*;

public class _207_CourseSchedule {

    /**
     * Graph - BFS - Kahn's Algorithm implementation
     * TC: O(V + E) - Vertices and Edges
     * SC: O(V) - Vertices that were stores in graph and in-degree map
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0)
            return true;

        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        /*
          Use Deque instead of Linked List for queue operations because
          {@link https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/ArrayDeque.html Javadoc} states
          that Deque implementation is faster for stacks and queues.
          Queue<Integer> queue = new LinkedList<>();
         */
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            visited++;

            for (int neighbor : graph.get(vertex)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        return numCourses == visited;
    }

    public static void main(String[] args) {
        _207_CourseSchedule cs = new _207_CourseSchedule();

        System.out.println(cs.canFinish(1, new int[][] { }));
        System.out.println(cs.canFinish(5, new int[][]{
                {1, 4}, {2, 4}, {3, 1}, {3, 2}
        }));
    }
}
