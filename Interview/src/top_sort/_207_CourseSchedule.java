package top_sort;

import java.util.*;

public class _207_CourseSchedule {

    /**
     * Graph - BFS - Kahn's Algorithm implementation
     * TC: O(V + E) - Vertices and Edges
     * SC: O(V + E) - Vertices that were stores in graph and in-degree map
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

    /**
     * Graph - DFS traversal with Cycle detection
     *
     * V = number of courses
     * E = edges between the vertices formed by pre-requisites array
     *
     * TC: O(V + E) - Vertices and Edges
     * SC: O(V) - for tracking visited nodes
     *     O(V) - for tracking nodes that are being visited
     *     O(V) - Recursion call stack. Worst case it will be = number of Courses
     *     O(V + E) - For constructing the graph
     * Overall SC: O(V + E)
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] preq : prerequisites) {
            graph.get(preq[1]).add(preq[0]);
        }

        boolean[] visited = new boolean[numCourses], inStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, graph, visited, inStack))
                return false;
        }

        return true;
    }

    private boolean hasCycle(int node, Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] inStack) {
        // Cycle! If u -> v and v is in being visited state
        if (inStack[node])
            return true;

        if (visited[node])
            return false;

        inStack[node] = true;
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (hasCycle(neighbor, graph, visited, inStack))
                return true;
        }

        inStack[node] = false;
        return false;
    }

    public static void main(String[] args) {
        _207_CourseSchedule cs = new _207_CourseSchedule();

        int[][][] prereq = {
                {{1, 0}, {2, 1}},
                {{1, 0}, {0, 1}},
                {{1, 0}, {2, 1}, {3, 2}, {4, 3}},
                {{1, 0}, {2, 1}, {3, 2}, {4, 3}, {0, 4}},
                {{2, 0}, {2, 1}, {3, 2}, {4, 2}, {3, 1}}
        };
        int[] courses = {3, 2, 10, 5, 5};

        for(int i = 0; i < courses.length; i++){
            System.out.println((i + 1)+ ".\tNumber of courses: "+ courses[i]);
            System.out.println("\tNumber of pre-requisites: "+ Arrays.deepToString(prereq[i]));
            System.out.println("\tOutput: " + ((i & 1) == 0 ? cs.canFinish(courses[i], prereq[i]) : cs.canFinishDFS(courses[i], prereq[i])));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
