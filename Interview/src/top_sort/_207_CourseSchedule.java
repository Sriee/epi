package top_sort;

import java.util.*;
public class _207_CourseSchedule {

    /**
     * Graph - BFS - Kahn's Algorithm implementation
     * TC: O(V + E) - Vertices and Edges
     * SC: O(V) - Vertices that were stores in graph and in-degree map
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0)
            return true;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Deque<Integer> queue = new ArrayDeque<>();

        // Initialize in-degree map
        for (int[] preq : prerequisites) {
            if (!inDegree.containsKey(preq[1]))
                inDegree.put(preq[1], 0);

            inDegree.put(preq[0], inDegree.getOrDefault(preq[0], 0) + 1);
            List<Integer> neighbor = graph.getOrDefault(preq[1], new ArrayList<>());
            neighbor.add(preq[0]);
            graph.put(preq[1], neighbor);
        }

        // Add sources to the queue
        Iterator<Integer> iter = inDegree.keySet().iterator();
        while(iter.hasNext()) {
            Integer key = iter.next();
            if (inDegree.get(key) == 0) {
                queue.offerLast(key);
                iter.remove();
            }
        }

        // Find Topological order
        while (!queue.isEmpty()) {
            Integer node = queue.removeFirst();
            if (!graph.containsKey(node))
                continue;

            for (Integer neighbor : graph.get(node)) {
                Integer degrees = inDegree.get(neighbor);
                if (degrees == 1) {
                    queue.offerLast(neighbor);
                    inDegree.remove(neighbor);
                } else {
                    inDegree.put(neighbor, degrees - 1);
                }
            }
        }

        return inDegree.isEmpty();
    }

    public static void main(String[] args) {
        _207_CourseSchedule cs = new _207_CourseSchedule();

//        System.out.println(cs.canFinish(1, new int[][] { }));
        System.out.println(cs.canFinish(5, new int[][] {
                {1, 4}, {2, 4}, {3, 1}, {3, 2}
        }));
    }
}
